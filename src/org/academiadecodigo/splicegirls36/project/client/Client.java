/** package org.academiadecodigo.splicegirls36.project.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.splicegirls36.project.utils.LogMessages;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**public class Client {

    public static final Logger logger = Logger.getLogger(Client.class.getName());
    public static final String CHARSET = "UTF8";

    private final Socket serverConnection;
    private final BufferedReader inputFromServer;
    private final BufferedWriter outputToServer;
    private final Prompt prompt;

    public Client (InetAddress address, int port) {

        Socket connection = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Prompt prompt = null;

        /**try {

            connection = new Socket(address, port);

        }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHARSET));
            out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), CHARSET));
            prompt = new Prompt(System.in, System.out);

        } catch (IOException exception) {

            logger.log(Level.SEVERE, LogMessages.FAILED_CONNECTION);

        }

        serverConnection = connection;
        inputFromServer = in;
        outputToServer = out;
        this.prompt = prompt;

    }*/

    /**public void start () {


        BufferedReader inputFromServer = null;




        try {

            inputFromServer = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));

        } catch (IOException exception) {

            logger.log(Level.SEVERE, exception.getMessage());

        }

    }

}*/
