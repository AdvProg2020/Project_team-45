package client.network;

import java.io.*;
import java.net.Socket;

public class ClientSocket {
    public static final int PORT = 8891;
    public static final String IP = "127.0.0.1";

    private final P2PSocket p2pSocket = new P2PSocket();
    private static final ClientSocket instance = new ClientSocket();

    private int token;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean isConnected;

    private final ClientSecurityGate securityGate;

    private ClientSocket() {

        securityGate = new ClientSecurityGate();

    }

    public static ClientSocket getInstance() {
        return instance;
    }

    public String sendAction(String action) {
        String Json = null;
        try {
            // action -> Server
            String messageToServer = getOutPutReady(action);
            outputStream.writeUTF(messageToServer);
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

    private String getOutPutReady(String action) {
        return "T" + token + "T" + action;
    }

    public void connectToServer() throws IOException {
        Socket socket = new Socket(IP, PORT);
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        securityGate.exchangeKeys(inputStream, outputStream);

        token = Integer.parseInt(inputStream.readUTF());

        outputStream.writeUTF(p2pSocket.IP);
        outputStream.flush();
        outputStream.writeUTF(String.valueOf(p2pSocket.PORT));
        outputStream.flush();

        p2pSocket.start();
    }

    public P2PSocket getP2pSocket() {
        return p2pSocket;
    }
}
