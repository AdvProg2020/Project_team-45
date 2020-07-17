package server.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ClientHandler extends Thread {

    private final int token;
    DataInputStream clientInputStream;
    DataOutputStream clientOutputStream;

    public ClientHandler(int token, DataInputStream clientInputStream, DataOutputStream clientOutputStream) {
        this.token = token;
        this.clientInputStream = clientInputStream;
        this.clientOutputStream = clientOutputStream;
    }

    @Override
    public void run() {

    }
}
