package server.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEntranceSocket extends Thread {

    private static final ServerEntranceSocket instance = new ServerEntranceSocket();

    // 6666
    public static final int PORT = 6667;
    public static final String IP = "127.0.0.1";

    private static boolean serverRunning;

    public static ServerEntranceSocket getInstance() {
        return instance;
    }

    private ServerSocket serverSocket;
    private int lastTokenGiven;

    private ServerEntranceSocket() {
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.lastTokenGiven = 1000;
        serverRunning = true;
    }

    public int getNextToken() {
        return lastTokenGiven++;
    }

    @Override
    public void run() {
        System.out.println("server is running...");
        while (serverRunning) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("a client connected.");
                DataInputStream clientInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream clientOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                new ClientHandler(getNextToken(), clientInputStream, clientOutputStream).start();
                // connection successful message
//                clientOutputStream.writeUTF("done");
//                System.out.println("a client connected.");
//                clientOutputStream.flush();
                //
            }catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }
        System.out.println("entrance socket stopped");
    }

    public void stopSocket(boolean serverRunning) {
        try {
            serverSocket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        ServerEntranceSocket.serverRunning = serverRunning;
    }
}
