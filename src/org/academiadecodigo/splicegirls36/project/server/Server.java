package org.academiadecodigo.splicegirls36.project.server;

import org.academiadecodigo.splicegirls36.project.domain.Question;
import org.academiadecodigo.splicegirls36.project.utils.LogMessages;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    public static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket clientSocket;
            final QuestionChooser questionChooser = new RandomQuestionChooser();
            String line = null;
            String answer;
            Question question;

            clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            logger.log(Level.INFO, LogMessages.CLIENT_CONNECTED);

            while (true) {

                question = questionChooser.chooseQuestion();

                line = question.getText();

                //while (!line.isEmpty()) {

                out.write(question.getText());
                out.newLine();

                //}

                out.flush();

                /** answer = in.readLine();

                System.out.println(answer); */

            }

        } catch (IOException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        }
    }
}