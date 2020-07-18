package client.network;

import java.io.*;
import java.net.Socket;

public class ClientSocket {
    public static final int PORT = 8890;
    public static final String IP = "127.0.0.1";

    private static ClientSocket instance ;

    private int token;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean isConnected;

    private ClientSocket() {
        try {
            connectToServer();
        } catch (IOException exception) {
            isConnected = false;
            System.err.println("server not found...");
        }
    }

    public static ClientSocket getInstance() {
        if (instance == null)
            return new ClientSocket();
        return instance;
    }

    public String sendAction(String action) {
        String Json = null;
        try {
            // action -> Server
            outputStream.writeUTF(action);
            outputStream.flush();

            // Server -> Json
            Json = inputStream.readUTF();
            // check if json is an exception
            // TODO
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return Json;
    }

    public void connectToServer() throws IOException {
        Socket socket = new Socket(IP, PORT);
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

}
