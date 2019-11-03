package org.academiadecodigo.splicegirls36.project.client;

import org.academiadecodigo.splicegirls36.project.terminal.TerminalPrompt;
import org.academiadecodigo.splicegirls36.project.utils.Constants;
import org.academiadecodigo.splicegirls36.project.utils.LogMessages;

import javax.sound.midi.Soundbank;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static final Logger logger = Logger.getLogger(Client.class.getName());
    public static final String CHARSET = "UTF8";

    private final Socket serverConnection;
    private final BufferedReader inputFromServer;
    private final BufferedWriter outputToServer;

    public Client (InetAddress address, int port) {

        Socket connection = null;
        BufferedReader in = null;
        BufferedWriter out = null;

        try {

            connection = new Socket(address, port);
            logger.log(Level.INFO, LogMessages.CLIENT_CONNECTED + " " + connection.toString());

            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHARSET));
            out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), CHARSET));

        } catch (IOException exception) {

            logger.log(Level.SEVERE, LogMessages.FAILED_CONNECTION);
            System.exit(1);

        }

        serverConnection = connection;
        inputFromServer = in;
        outputToServer = out;

    }

    public void start () {

        String answer;
        StringBuilder question = new StringBuilder();
        TerminalPrompt terminal = new TerminalPrompt();
        String quizQuestion;
        String answerA;
        String answerB;
        String answerC;
        String answerD;
        String explanation;
        String correctAnswer;
        int counter = 0;
        List<String> questionStrings = new ArrayList<>();
        List<String> explanations = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();
        int points = 0;

        try {

                // Get chosen questions from server
                while (counter < Constants.MAX_ROUNDS) {
                    quizQuestion = inputFromServer.readLine();
                    answerA = inputFromServer.readLine();
                    answerB = inputFromServer.readLine();
                    answerC = inputFromServer.readLine();
                    answerD = inputFromServer.readLine();
                    explanation = inputFromServer.readLine();
                    correctAnswer = inputFromServer.readLine();
                    question.append(quizQuestion);
                    question.append("\n");
                    question.append(answerA);
                    question.append("\n");
                    question.append(answerB);
                    question.append("\n");
                    question.append(answerC);
                    question.append("\n");
                    question.append(answerD);
                    question.append("\n");
                    questionStrings.add(question.toString());
                    explanations.add(explanation);
                    correctAnswers.add(correctAnswer);
                    question.delete(0, question.length() - 1);
                    counter++;

                }

                for (int i = 0; i < Constants.MAX_ROUNDS; i++) {

                    // Ask player for chosen answer
                    System.out.println("Round " + i);
                    answer = terminal.askQuestion(questionStrings.get(i));

                    if (answer.equals(correctAnswers.get(i))){
                        System.out.println("Right Answer");
                        points++;
                    } else {
                        System.out.println("Wrong Answer");
                    }
                    System.out.println(explanations.get(i));
                    System.out.println("\n\n");
                }
                outputToServer.write(points);
                outputToServer.newLine();
                outputToServer.flush();

                System.out.println("You finished with " + points + " POOOOOOOOOINTS!!");
                close();



                // Get feedback from server
                /*line = inputFromServer.readLine();
                System.out.println(line);*/

        } catch (IOException exception) {
            logger.log(Level.SEVERE, exception.getMessage());

        }

    }

    private void close () {

        try {

            if (serverConnection != null) {
                serverConnection.close();
            }

        } catch (IOException exception) {
            logger.log(Level.WARNING, exception.getMessage());

        }
    }
}
