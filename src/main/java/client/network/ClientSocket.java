package client.network;

import java.io.*;
import java.net.Socket;

public class ClientSocket extends Thread {

    // server info
//    public static final int PORT = 15154;
//    public static final String IP = "0.tcp.ngrok.io";

    // in pc
    public static final int PORT = 6666;
    public static final String IP = "127.0.0.1";
    private int messageCounter;

    private final P2PSocket p2pSocket = new P2PSocket();
    private static final ClientSocket instance = new ClientSocket();

    private int token;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean isConnected;
    private boolean isRunning;

    private final ClientSecurityGate securityGate;
    private final Object inputLock = new Object();
    private String lastMessage;

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
            synchronized (inputLock) {
                inputLock.wait();
                Json = getInputReady(lastMessage);
            }
            // check if json is an exception
            // TODO
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return Json;
    }

    private String getInputReady(String readUTF) {
        return readUTF;
    }

    private String getOutPutReady(String action) {
        return "T" + token + "T" + action;
    }

    public void connectToServer() throws IOException {
        Socket socket = new Socket(IP, PORT);
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

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

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            try {
                String serverMessage = inputStream.readUTF();
                if (serverMessage.startsWith("%%update%%")) {
                    // TODO : bagheri go on
                } else {
                    synchronized (inputLock) {
                        lastMessage = serverMessage;
                        inputLock.notify();
                    }
                }
            } catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }

    public void closeSocket() throws IOException {
        isRunning = false;
        try {
            inputStream.close();
        } catch (IOException ignored) {
        }
        p2pSocket.closeSocket();
    }
}
