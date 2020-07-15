package network;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerEntranceSocket {

    public static final int PORT = 8888;
    public static final String IP = "127.0.0.1";

    private ServerSocket serverSocket;

    public ServerSocket createServerSocket() throws IOException {
        ServerSocket socket = new ServerSocket(PORT);
        return socket;
    }

    public void waitForClient() {

    }

}
