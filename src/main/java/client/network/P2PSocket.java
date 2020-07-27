package client.network;

import client.network.encryption.AsymmetricEncryption;
import client.network.encryption.SymmetricEncryption;
import com.google.gson.Gson;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

public class P2PSocket extends Thread {
    public final int PORT = 19378;
    public final String IP = "0.tcp.ngrok.io";

    public final int localPort = 8888;

    private ServerSocket serverSocket;
    private final Object receiveFileLock = new Object();

    private PublicKey publicKey;
    private PrivateKey privateKey;
    private SecretKey secretKey;
    private String initializationVector;
    private boolean isRunning;

    public P2PSocket() {
        try {
            serverSocket = new ServerSocket(localPort);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public int getPORT() {
        return PORT;
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("//////////////////////");

                DataInputStream dataInputStream =
                        new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream dataOutputStream =
                        new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                String clientMessage = dataInputStream.readUTF();

                System.out.println("p2p : a peer connected : " + clientMessage);
                if (clientMessage.startsWith("server:")) {
                    try {
                        handleServerMessage(clientMessage);
                        dataOutputStream.writeUTF("done");
                    } catch (IOException e) {
                        dataOutputStream.writeUTF("fail");
                    }
                    dataOutputStream.flush();
                } else if (clientMessage.startsWith("seller:")) {
                    connectToSeller(clientSocket, dataInputStream, dataOutputStream);
                }

            } catch (SocketException exception) {
                System.out.println("p2pSocket Died");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void handleServerMessage(String serverMessage) throws IOException {
        // for seller : send file for client
        String[] messageParted = serverMessage.split(":", 2)[1].split("&", 3);
        String buyerIP = messageParted[0];
        int buyerPort = Integer.parseInt(messageParted[1]);

        Socket buyerSocket = new Socket(buyerIP, buyerPort);
        String fileInfo = messageParted[2];
        connectToBuyer(buyerSocket, fileInfo);
    }

    private void connectToBuyer(Socket buyerSocket, String fileInfo) {
        try {
            DataOutputStream dataOutputStream =
                    new DataOutputStream(new BufferedOutputStream(buyerSocket.getOutputStream()));
            DataInputStream dataInputStream =
                    new DataInputStream(new BufferedInputStream(buyerSocket.getInputStream()));

            // 1
            dataOutputStream.writeUTF("seller: give me your public key");
            dataOutputStream.flush();
            dataOutputStream.writeUTF("seller: give me your public key");
            dataOutputStream.flush();

            // 3
            String response = dataInputStream.readUTF();
            System.out.println(response);
            String[] receivedMessage = response.split("::");
            System.out.println("receivedMessage " + response);
            publicKey = (new Gson()).fromJson(receivedMessage[1], (Type) Class.forName(receivedMessage[0]));

            secretKey = SymmetricEncryption.getInstance().generateSecretKey();
            initializationVector = SymmetricEncryption.getInstance().generateInitializationVector();

            dataOutputStream.writeUTF(AsymmetricEncryption.getInstance()
                    .encrypt(secretKey.getClass().getName() + "::" + (new Gson()).toJson(secretKey) + "::" +
                            initializationVector, publicKey));
            dataOutputStream.flush();

            // 5
            if (dataInputStream.readUTF().equals("buyer: give me format")) {
                dataOutputStream.writeUTF(fileInfo.split("\\.")[1]);
                dataOutputStream.flush();
            }

            //
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendFileForBuyer(buyerSocket, fileInfo);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connectToSeller(Socket sellerSocket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        try {
//            DataInputStream dataInputStream =
//                    new DataInputStream(new BufferedInputStream(sellerSocket.getInputStream()));
//            DataOutputStream dataOutputStream =
//                    new DataOutputStream(new BufferedOutputStream(sellerSocket.getOutputStream()));

            String p2 = dataInputStream.readUTF();
            System.out.println("p2 : " + p2);
            if (p2.equals("seller: give me your public key")) {
                // 2
                KeyPair keyPair = AsymmetricEncryption.getInstance().generateKeyPair();
                privateKey = keyPair.getPrivate();
                publicKey = keyPair.getPublic();

                dataOutputStream.writeUTF(publicKey.getClass().getName() + "::" + (new Gson()).toJson(publicKey));
                dataOutputStream.flush();

                // 4
                String p4 = AsymmetricEncryption.getInstance().decrypt(dataInputStream.readUTF(), privateKey);
                System.out.println("p4 : " + p4);
                String[] receivedMessage = p4.split("::");
                secretKey = (new Gson()).fromJson(receivedMessage[1], (Type) Class.forName(receivedMessage[0]));
                initializationVector = receivedMessage[2];

                dataOutputStream.writeUTF("buyer: give me format");
                dataOutputStream.flush();

                // 6
                String format = dataInputStream.readUTF();

                //
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        receiveFileFromSeller(format, sellerSocket);
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendFileForBuyer(Socket buyerSocket, String fileInfo) {
        // fileInfo is the file path in client
        try {
            BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(new File(fileInfo)));
            byte[] buffer = new byte[10];
            int bytesRead;
            DataOutputStream dataOutputStream =
                    new DataOutputStream(new BufferedOutputStream(buyerSocket.getOutputStream()));
            DataInputStream dataInputStream =
                    new DataInputStream(new BufferedInputStream(buyerSocket.getInputStream()));

            while ((bytesRead = fileInputStream.read(buffer)) > 0) {
                String bufferString = DatatypeConverter.printHexBinary(buffer);
                String symmetricCipherText = SymmetricEncryption.getInstance().encrypt(
                        bytesRead + "::" + bufferString, secretKey, initializationVector);
                String asymmetricCipherText = AsymmetricEncryption.getInstance().encrypt(symmetricCipherText, publicKey);
                dataOutputStream.writeUTF(asymmetricCipherText);
                dataOutputStream.flush();

                dataInputStream.readUTF();
            }
            dataOutputStream.writeUTF("done");
            dataOutputStream.flush();

            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void receiveFileFromSeller(String format, Socket sellerSocket) {
        try {
            BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(new File("src/main/java/client/test." + format)));
            String receivedMessage;
            int off = 0;
            DataOutputStream dataOutputStream =
                    new DataOutputStream(new BufferedOutputStream(sellerSocket.getOutputStream()));
            DataInputStream dataInputStream =
                    new DataInputStream(new BufferedInputStream(sellerSocket.getInputStream()));

            while (!(receivedMessage = dataInputStream.readUTF()).equals("done")) {

                String[] messageParts = SymmetricEncryption.getInstance().decrypt(AsymmetricEncryption.getInstance().decrypt(
                        receivedMessage, privateKey), secretKey, initializationVector).split("::");

                System.out.println(messageParts[0] + "//////////" + messageParts[1]);
                byte[] buffer = DatatypeConverter.parseHexBinary(messageParts[1]);
                int bytesRead = Integer.parseInt(messageParts[0]);
                fileOutputStream.write(buffer, 0, bytesRead);
                off += bytesRead;

                dataOutputStream.writeUTF("go on");
                dataOutputStream.flush();
            }

            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIP() {
        return IP;
    }

    public void closeSocket() throws IOException {
        isRunning = false;
        serverSocket.close();
    }
}
