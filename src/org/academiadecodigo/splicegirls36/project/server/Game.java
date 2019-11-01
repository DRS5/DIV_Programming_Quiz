package org.academiadecodigo.splicegirls36.project.server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    public static final Logger logger = Logger.getLogger(Game.class.getName());

    private ServerSocket serverSocket;

    public Game (ServerSocket serverSocket) {

        this.serverSocket = serverSocket;

    }

    public void setup() {

        Socket clientSocket = null;
        BufferedReader inputFromClient = null;
        BufferedWriter outputToClient = null;

        try {

            //while (serverSocket.isBound()) {

                clientSocket = serverSocket.accept();
                inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outputToClient = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                inputFromClient.readLine();

                outputToClient.write("Hello");
                outputToClient.newLine();
                outputToClient.flush();

            //}

        } catch (IOException exception) {

            logger.log(Level.SEVERE, exception.getMessage());

        }





    }

    public void start() {



    }
}
