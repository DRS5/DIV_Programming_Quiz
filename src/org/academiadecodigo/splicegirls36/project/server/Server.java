package org.academiadecodigo.splicegirls36.project.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                System.out.println("New Client: " + clientSocket);
                /*out.write("test");
                out.newLine();
                out.flush();*/
                System.out.println(in.readLine());

                clientSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}