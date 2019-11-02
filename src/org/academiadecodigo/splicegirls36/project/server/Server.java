package org.academiadecodigo.splicegirls36.project.server;

import org.academiadecodigo.splicegirls36.project.domain.Question;
import org.academiadecodigo.splicegirls36.project.terminal.Strings;
import org.academiadecodigo.splicegirls36.project.utils.LogMessages;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    public static final Logger logger = Logger.getLogger(Server.class.getName());
    public static final int MAX_PLAYERS = 15;

    private ExecutorService workers;

    private List<ServerWorker> workerList;

    public static void main(String[] args) {

        Server server = new Server();
        server.start();

    }

    public Server() {

        this.workers = Executors.newFixedThreadPool(MAX_PLAYERS);
        this.workerList = new ArrayList<>();

    }

    private void start() {

        int rightAnswersCounter = 0;
        int playerCounter = 0;

        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            QuestionChooser questionChooser = new SequentialQuestionChooser();

            // Game Lobby Stage

            // Receive client connection and take socket streams
            Socket clientSocket = serverSocket.accept();
            logger.log(Level.INFO, LogMessages.ACCEPTED_CONNECTION + " " + clientSocket);

            ServerWorker newWorker = new ServerWorker(clientSocket);
            workerList.add(newWorker);
            playerCounter++;

            // After game start Stage

            // Choose a question
            Question question = questionChooser.chooseQuestion();

            // Send question to all workers

            for (ServerWorker worker : workerList) {

                worker.setQuestion(question);
                workers.submit(newWorker);

            }


            // Validate answer and return to server worker

            for (ServerWorker worker : workerList) {

                String workerAnswer = worker.getAnswer();
                if (question.getCorrectAnswer().equals(workerAnswer)) {

                    rightAnswersCounter++;
                    logger.log(Level.INFO, "Right answer");
                    worker.sendFeedback(Strings.RIGHT_ANSWER);

                } else {

                    logger.log(Level.INFO, "Wrong answer");
                    worker.sendFeedback(Strings.WRONG_ANSWER);

                }

                logger.log(Level.INFO, String.valueOf(rightAnswersCounter));
            }

        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    private final class ServerWorker implements Runnable {

        private Socket clientSocket;
        private Question question;

        private BufferedReader in;
        private BufferedWriter out;

        private String answer;

        ServerWorker(Socket clientSocket) {

            this.clientSocket = clientSocket;

        }

        @Override
        public void run() {;

            try {

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                while (true) {

                    // Send chosen question
                    out.write(question.getText());
                    out.newLine();
                    out.flush();

                    // Print answer
                    answer = in.readLine();
                    System.out.println(answer);


                }

            } catch (IOException exception) {
                logger.log(Level.SEVERE, exception.getMessage());
            }

        }

        private void sendFeedback (String feedback) {

            try {

                System.out.println(feedback);
                out.write(feedback);
                out.newLine();
                out.flush();

            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }

        String getAnswer () {

            return answer;

        }

        void setQuestion (Question question) {

            this.question = question;

        }
    }
}