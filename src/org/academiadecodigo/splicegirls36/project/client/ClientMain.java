package org.academiadecodigo.splicegirls36.project.client;


import org.academiadecodigo.splicegirls36.project.utils.LogMessages;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;

public class ClientMain {

    public static void main(String[] args) {



        //InetAddress address = InetAddress.getByName();
        int port = 8080;
        Client client = null;

        try {

            client = new Client(InetAddress.getLocalHost(), 8080);
            client.start();

        } catch (IOException exception) {

            Client.logger.log(Level.WARNING, LogMessages.UNKNOWN_HOST + " " + exception.getMessage());

        }


    }

}
