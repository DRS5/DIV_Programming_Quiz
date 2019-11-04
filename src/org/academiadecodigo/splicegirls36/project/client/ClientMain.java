package org.academiadecodigo.splicegirls36.project.client;


import org.academiadecodigo.splicegirls36.project.utils.LogMessages;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;

public class ClientMain {

    public static void main(String[] args) {

        //InetAddress address = InetAddress.getByName();
        int port;
        Client client;

        try {

            if (args.length != 2){
                throw new IllegalArgumentException("Usage: java clientmain <host> <port>");
            }
            String server = args[0];
            port = Integer.parseInt(args[1]);
            client = new Client(InetAddress.getByName(server), port);
            client.start();

        } catch (IOException exception) {

            Client.logger.log(Level.WARNING, LogMessages.UNKNOWN_HOST + " " + exception.getMessage());

        }

    }

}
