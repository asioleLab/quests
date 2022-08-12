package org.chat.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private ServerSocket serverSocket;
    private List<PrintWriter> allUsers;
    private boolean started = false;

    public ChatServer() {
    }

    public void start() throws IOException {
        try {
            serverSocket = new ServerSocket(10000);
            allUsers = new ArrayList<>();
            this.started = true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            throw ex;
        }
    }

    public boolean isStarted() {
        return started;
    }

    public void stop() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
                this.started = false;
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void runMe() throws IOException {
        while (started) {
            try {
                System.out.println("Listening mode.. >>");
                new Thread(new ChatThread(serverSocket.accept(), allUsers)).start();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                stop();
                throw ex;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
        chatServer.runMe();
    }

}
