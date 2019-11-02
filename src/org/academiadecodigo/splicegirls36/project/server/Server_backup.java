/** package org.academiadecodigo.splicegirls36.project.server;

import org.academiadecodigo.splicegirls36.project.utils.LogMessages;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

public class Server_backup {

    public static void main(String[] args) {

        int port = 8080;
        ServerSocket serverSocket = null;
        Game game;

        if (args.length == 1) {

            port = Integer.parseInt(args[0]);

        } else {
            throw new IllegalArgumentException("Usage: server <port>");
        }

        try {

            serverSocket = new ServerSocket(port);

        } catch (IOException e) {

            Game.logger.log(Level.SEVERE, LogMessages.BIND_FAILED + port);
            System.exit(1);

        }

        game = new Game(serverSocket);
        game.setup();
    }

} */
