package org.academiadecodigo.splicegirls36.project.server;

import org.academiadecodigo.splicegirls36.project.domain.Question;
import org.academiadecodigo.splicegirls36.project.store.QuestionDatabase;
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

    private final ExecutorService workers;
    private final List<ServerWorker> workerList;
    private Question question;

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

            // Game Lobby Stage

            // Receive client connection and take socket streams
            Socket clientSocket = serverSocket.accept();
            logger.log(Level.INFO, LogMessages.ACCEPTED_CONNECTION + " " + clientSocket);

            playerCounter++;
            ServerWorker newWorker = new ServerWorker(clientSocket);
            workerList.add(newWorker);
            Thread thread = new Thread(newWorker);
            thread.start();

            // After game start Stage


            // Validate answer and return to server worker

            for (ServerWorker worker : workerList) {

                String workerAnswer = worker.getAnswer();

            }

            for (ServerWorker worker : workerList) {

                synchronized (worker) {
                    worker.wait();
                }

            }

            logger.log(Level.INFO, String.valueOf(rightAnswersCounter));

        } catch (IOException | InterruptedException e) {

            logger.log(Level.WARNING, e.getMessage());

        }

    }

    public Question getQuestion() {
        return question;
    }

    private final class ServerWorker implements Runnable {

        private Socket clientSocket;

        private String answer;

        private BufferedWriter out;

        private List<Question> questions;
        private QuestionDatabase questionDatabase;
        private int questionIndex = 0;

        QuestionChooser questionChooser = new SequentialQuestionChooser();

        ServerWorker(Socket clientSocket) {

            this.clientSocket = clientSocket;
            this.questionDatabase = new QuestionDatabase();

        }

        @Override
        public void run() {

            try {
                questionDatabase.buildList();
                questions = questionDatabase.getqAList();

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                while (true) {
                    // Choose a question
                    question = chooseQuestion();


                    // Send chosen question
                    out.write(question.getText());
                    out.newLine();
                    out.flush();

                    // Print answer
                    answer = in.readLine();
                    System.out.println(answer);

                    if (question.getCorrectAnswer().equals(answer)) {

                        logger.log(Level.INFO, "Right answer");
                        sendFeedback(Strings.RIGHT_ANSWER);

                    } else {

                        logger.log(Level.INFO, "Wrong answer");
                        sendFeedback(Strings.WRONG_ANSWER);

                    }
                    questionIndex++;

                }

            } catch (IOException exception) {
                logger.log(Level.SEVERE, exception.getMessage());
            }

        }

        private void sendFeedback(String feedback) {

            try {

                System.out.println(feedback);
                out.write(feedback);
                out.newLine();
                out.flush();

            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }

        String getAnswer() {

            return answer;

        }

        public Question chooseQuestion() {
            Question question = questions.get(questionIndex);
            return question;
        }
    }
}
