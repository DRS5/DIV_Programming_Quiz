package org.academiadecodigo.splicegirls36.project.server;

import org.academiadecodigo.splicegirls36.project.store.QuestionDatabase;
import org.academiadecodigo.splicegirls36.project.utils.Constants;
import org.academiadecodigo.splicegirls36.project.utils.LogMessages;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    public static final Logger logger = Logger.getLogger(Server.class.getName());

    private volatile int totalPoints = 0;
    private volatile int workerCount = 0;
    private List<ServerWorker> workerList = new ArrayList<>();

    public static void main(String[] args) {

        int port = Constants.DEFAULT_PORT; // default value of local port

        // Validate first argument being the local port the server should bind to
        if (args.length > 0) {

            port = Integer.parseInt(args[0]);

        }

        Server server = new Server();
        server.start(port);

    }

    private void start(int port) {

        ExecutorService workers = Executors.newFixedThreadPool(Constants.MAX_PLAYERS);
        QuestionDatabase questionDatabase = new QuestionDatabase();
        List<String> questions;
        int playerCounter = 0;

        Thread.currentThread().setName("ServerMain");

        try {

            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = null;

            /** Game Lobby Stage */
            logger.log(Level.INFO, LogMessages.LISTENING_CONNECTIONS);

            // Receive client connection and pass the corresponding socket to the server worker

            while (playerCounter < Constants.MIN_PLAYERS) {

                clientSocket = serverSocket.accept();
                logger.log(Level.INFO, LogMessages.ACCEPTED_CONNECTION + " " + clientSocket);

                ServerWorker newWorker = new ServerWorker(clientSocket);
                workerList.add(newWorker);

                playerCounter++;
            }

            serverSocket.setSoTimeout(Constants.MAX_TIME);
            logger.log(Level.INFO, LogMessages.LC_EXTRA_TIME);
            while (playerCounter < Constants.MAX_PLAYERS) {
                try {

                    clientSocket = serverSocket.accept();
                    logger.log(Level.INFO, LogMessages.ACCEPTED_CONNECTION + " " + clientSocket);

                    ServerWorker newWorker = new ServerWorker(clientSocket);
                    workerList.add(newWorker);
                    playerCounter++;

                } catch (SocketTimeoutException timeoutException) {

                    logger.log(Level.WARNING, LogMessages.CLIENT_CONNECTION_TIMED_OUT);
                    break;

                }
            }

            // After Lobby Stage start Game

            logger.log(Level.INFO, LogMessages.STARTING_GAME);

            // Send all questions to all players - Each worker has a list of questions to send and will do that, when it starts to run
            logger.log(Level.INFO, LogMessages.SENDING_QUESTIONS);
            for (ServerWorker worker : workerList) {

                workers.submit(worker);

            }

            sendTotalPointsAll(getTotalPoints());

            serverSocket.close();

        } catch (IOException e) {

            logger.log(Level.WARNING, e.getMessage());

        }

    }

    private void sendTotalPointsAll (int totalPoints) {

        for (ServerWorker worker : workerList) {

            worker.sendTotalPoints(totalPoints);

        }

    }

    private synchronized void addPoints(int points) {

        if (workerCount == this.workerList.size()) {
            notifyAll();
            return;
        }
        this.totalPoints += points;
        this.workerCount++;

    }

    private synchronized int getTotalPoints() {

        try {
            wait();
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        return this.totalPoints;

    }

    private final class ServerWorker implements Runnable {

        private final Socket clientSocket;

        private final BufferedReader in;
        private final BufferedWriter out;

        private List<String> questions;
        private QuestionDatabase questionDatabase;


        ServerWorker(Socket clientSocket) {

            BufferedReader in = null;
            BufferedWriter out = null;

            this.clientSocket = clientSocket;
            this.questionDatabase = new QuestionDatabase();
            try {

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            } catch (IOException e) {

                logger.log(Level.WARNING, e.getMessage());
            }

            this.in = in;
            this.out = out;
        }

        @Override
        public void run() {

            int counter = 0;
            String[] question_split;
            int totalPoints;

            try {
                questionDatabase.buildList();
                questions = questionDatabase.getqAList();


                while (clientSocket.isBound()) {

                    // Send chosen questions

                    while (counter < Constants.MAX_ROUNDS) {

                        for (String question : questions) {

                            question_split = question.split("\n");
                            System.out.println(question_split);
                            for (int i = 0; i < question_split.length; i++) {

                                out.write(question_split[i]);
                                out.newLine();
                                out.flush();

                            }
                        }
                        counter++;

                    }

                    // Print answer
                    totalPoints = Integer.parseInt(in.readLine());
                    Server.this.addPoints(totalPoints);
                    logger.log(Level.INFO, "Total points " + totalPoints);

                }

            } catch (IOException exception) {
                logger.log(Level.SEVERE, exception.getMessage());
            }

        }

        private void sendTotalPoints(int totalPoints) {

            try {
                out.write(totalPoints);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }

        }
    }
}
