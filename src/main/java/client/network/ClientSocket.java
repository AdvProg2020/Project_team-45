package client.network;

import java.io.*;
import java.net.Socket;

public class ClientSocket {
    public static final int PORT = 8891;
    public static final String IP = "127.0.0.1";

    private static final ClientSocket instance = new ClientSocket();

    private int token;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean isConnected;

    private ClientSocket() {
    }

    public static ClientSocket getInstance() {
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
//        System.out.println("hi");
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

}
