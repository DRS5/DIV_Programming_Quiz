package org.academiadecodigo.splicegirls36.project.client;

import org.academiadecodigo.splicegirls36.project.terminal.TerminalPrompt;
import org.academiadecodigo.splicegirls36.project.utils.LogMessages;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
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

        }

        serverConnection = connection;
        inputFromServer = in;
        outputToServer = out;

    }

    public void start () {

        String line;
        String answer;
        StringBuilder question = new StringBuilder();
        TerminalPrompt terminal = new TerminalPrompt();

        try {

            while (serverConnection.isBound()) {

                // Get chosen question from server
                line = inputFromServer.readLine();
                while (!line.isEmpty()) {
                    question.append(line);
                    question.append("\n");
                    line = inputFromServer.readLine();
                }



                // Ask player for chosen answer
                answer = terminal.askQuestion(question.toString());
                System.out.println(answer);

                // Send answer to server
                outputToServer.write(answer);
                outputToServer.newLine();
                outputToServer.flush();

                // Get feedback from server
                line = inputFromServer.readLine();
                System.out.println(line);

            }

        } catch (IOException exception) {

            logger.log(Level.SEVERE, exception.getMessage());

        }

        close();

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
