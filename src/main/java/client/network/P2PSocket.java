package client.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class P2PSocket extends Thread {
    public final int PORT;
    public final String IP = "127.0.0.1";

    private ServerSocket serverSocket;
    private final Object receiveFileLock = new Object();

    public P2PSocket() {
        try {
            serverSocket = new ServerSocket(0);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        PORT = serverSocket.getLocalPort();
    }

    public int getPORT() {
        return PORT;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                DataInputStream dataInputStream =
                        new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream dataOutputStream =
                        new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                String clientMessage = dataInputStream.readUTF();

                if (clientMessage.startsWith("server:")) {
                    try {
                        handleServerMessage(clientMessage);
                        dataOutputStream.writeUTF("done");
                    } catch (IOException e) {
                        dataOutputStream.writeUTF("fail");
                    }
                    dataOutputStream.flush();
                } else if (clientMessage.startsWith("seller:")) {
                    receiveFile(clientSocket);
                }

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void handleServerMessage(String serverMessage) throws IOException {
        // for seller : send file for client
        String[] messageParted = serverMessage.split(":", 1)[1].split("&", 2);
        String buyerIP = messageParted[0];
        int buyerPort = Integer.parseInt(messageParted[1]);

        Socket buyerSocket = new Socket(buyerIP, buyerPort);
        String fileInfo = messageParted[2];
        connectToBuyer(buyerSocket, fileInfo);
    }

    private void connectToBuyer(Socket buyerSocket, String fileInfo) throws IOException {
        DataOutputStream dataOutputStream =
                new DataOutputStream(new BufferedOutputStream(buyerSocket.getOutputStream()));
        dataOutputStream.writeUTF("seller: hi");
        dataOutputStream.flush();
        // TODO : nedaeai : get response from buyer if needed

//        DataInputStream dataInputStream =
//                new DataInputStream(new BufferedInputStream(buyerSocket.getInputStream()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendFileForBuyer(buyerSocket, fileInfo);
            }
        }).start();
    }

    private void receiveFile(Socket sellerSocket) {
        // TODO : nedaeai , handle receiving file
    }

    private void sendFileForBuyer(Socket buyerSocket, String fileInfo) {
        // TODO : nedaeai
    }

    public String getIP() {
        return IP;
    }

}
