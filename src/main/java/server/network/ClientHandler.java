package server.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientHandler extends Thread {

    private final int token;
    private final DataInputStream clientInputStream;
    private final DataOutputStream clientOutputStream;
    private final boolean isConnected;

    public ClientHandler(int token, DataInputStream clientInputStream, DataOutputStream clientOutputStream) {
        this.token = token;
        this.clientInputStream = clientInputStream;
        this.clientOutputStream = clientOutputStream;
        isConnected = true;
    }

    @Override
    public void run() {
        String clientMessage;
        String serverAnswer;
        while (true) {
            try {
                clientMessage = clientInputStream.readUTF();
                serverAnswer = MethodStringer.runMethodReturnJson(clientMessage);
                clientOutputStream.writeUTF(serverAnswer);
                clientOutputStream.flush();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
