package org.chat.server.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TestClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean connected=false;

    public void startConnection(String ip, int port) throws IOException {
        try{
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.connected=true;
        }catch (IOException ex){
            System.err.println(ex.getMessage());
            throw ex;
        }
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
//        String resp = in.readLine();
//        return resp;
        return msg;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public boolean isConnected() {
        return connected;
    }
}
