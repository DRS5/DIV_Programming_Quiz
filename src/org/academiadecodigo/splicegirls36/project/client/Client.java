/** package org.academiadecodigo.splicegirls36.project.client;

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
            logger.log(Level.INFO, LogMessages.CLIENT_CONNECTED + " " + connection);

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

        String test = "Testing 1, 2, 3";
        String line;

        try {

            //line = inputFromServer.readLine();

            while (serverConnection.isBound()) {

                outputToServer.write(test);
                outputToServer.newLine();
                outputToServer.flush();

                line = inputFromServer.readLine();
                System.out.println(line);

            }

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
