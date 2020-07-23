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
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class P2PSocket extends Thread {
    public final int PORT;
    public final String IP = "127.0.0.1";

    private ServerSocket serverSocket;
    private final Object receiveFileLock = new Object();

    private PublicKey publicKey;
    private PrivateKey privateKey;
    private SecretKey secretKey;
    private String initializationVector;

    public P2PSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
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
                    connectToSeller(clientSocket);
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

    private void connectToBuyer(Socket buyerSocket, String fileInfo) {
        // TODO : nedaei
        try {
            DataOutputStream dataOutputStream =
                    new DataOutputStream(new BufferedOutputStream(buyerSocket.getOutputStream()));
            DataInputStream dataInputStream =
                    new DataInputStream(new BufferedInputStream(buyerSocket.getInputStream()));

            // 1
            dataOutputStream.writeUTF("seller: give me your public key");
            dataOutputStream.flush();

            // 3
            String[] receivedMessage = dataInputStream.readUTF().split("::");
            publicKey = (new Gson()).fromJson(receivedMessage[1], (Type) Class.forName(receivedMessage[0]));

            secretKey = SymmetricEncryption.getInstance().generateSecretKey();
            initializationVector = SymmetricEncryption.getInstance().generateInitializationVector();

            dataOutputStream.writeUTF(AsymmetricEncryption.getInstance()
                    .encrypt(secretKey.getClass().getName() + "::" + (new Gson()).toJson(secretKey) + "::" +
                            initializationVector, publicKey));
            dataOutputStream.flush();

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

    private void connectToSeller(Socket sellerSocket) {
        // TODO : nedaei
        try {
            DataInputStream dataInputStream =
                    new DataInputStream(new BufferedInputStream(sellerSocket.getInputStream()));
            DataOutputStream dataOutputStream =
                    new DataOutputStream(new BufferedOutputStream(sellerSocket.getOutputStream()));

            if (dataInputStream.readUTF().equals("seller: give me your public key")) {
                // 2
                KeyPair keyPair = AsymmetricEncryption.getInstance().generateKeyPair();
                privateKey = keyPair.getPrivate();
                publicKey = keyPair.getPublic();

                dataOutputStream.writeUTF(publicKey.getClass().getName() + "::" + (new Gson()).toJson(publicKey));
                dataOutputStream.flush();

                // 4
                String[] receivedMessage = dataInputStream.readUTF().split("::");
                secretKey = (new Gson()).fromJson(receivedMessage[1], (Type) Class.forName(receivedMessage[0]));
                initializationVector = receivedMessage[2];

                //
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        receiveFileFromSeller(sellerSocket);
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendFileForBuyer(Socket buyerSocket, String fileInfo) {
        // todo : nedaei, check this
        // fileInfo is the file path in client
        try {
            BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(new File(fileInfo)));
            byte[] buffer = new byte[1024];
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

    private void receiveFileFromSeller(Socket sellerSocket) {
        try {
            // todo : nedaei, get the file format & check this
            BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(new File("src/main/java/client/test.txt")));
            String receivedMessage;
            int off = 0;
            DataOutputStream dataOutputStream =
                    new DataOutputStream(new BufferedOutputStream(sellerSocket.getOutputStream()));
            DataInputStream dataInputStream =
                    new DataInputStream(new BufferedInputStream(sellerSocket.getInputStream()));

            while (!(receivedMessage = dataInputStream.readUTF()).equals("done")) {
                String[] messageParts = AsymmetricEncryption.getInstance().decrypt(
                        SymmetricEncryption.getInstance().decrypt(
                                receivedMessage, secretKey, initializationVector), privateKey).split("::");

                byte[] buffer = DatatypeConverter.parseHexBinary(messageParts[1]);
                int bytesRead = Integer.parseInt(messageParts[0]);
                fileOutputStream.write(buffer, off, bytesRead);
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

}
