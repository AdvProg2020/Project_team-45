package server.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEntranceSocket implements Runnable {

    public static final int PORT = 8890;
    public static final String IP = "127.0.0.1";


    private final ServerSocket serverSocket;
    private int lastTokenGiven;

    public ServerEntranceSocket() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.lastTokenGiven = 1000;
    }

    public int getNextToken() {
        return lastTokenGiven++;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                DataInputStream clientInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream clientOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                new ClientHandler(getNextToken(), clientInputStream, clientOutputStream).start();

                // connection successful message
                clientOutputStream.writeUTF("done");
                System.out.println("a client connected.");
                clientOutputStream.flush();
                //
            }catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }
}
