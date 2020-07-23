package server.network;

import server.controller.ServerManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientHandler extends Thread {

    private final int token;
    private final DataInputStream clientInputStream;
    private final DataOutputStream clientOutputStream;
    private final boolean isConnected;
    private final Object lock = new Object();

    private String p2pIP;
    private int p2pPORT;


    private String lastCommand;
    private String loggedInUsername;

    private final ServerSecurityGate securityGate;

    public ClientHandler(int token, DataInputStream clientInputStream, DataOutputStream clientOutputStream) {
        this.token = token;
        this.clientInputStream = clientInputStream;
        this.clientOutputStream = clientOutputStream;
        isConnected = true;

        securityGate = new ServerSecurityGate();
        securityGate.exchangeKeys(clientInputStream, clientOutputStream);

        try {
            clientOutputStream.writeUTF(String.valueOf(token));
            clientOutputStream.flush();

            p2pIP = clientInputStream.readUTF();
            p2pPORT = Integer.parseInt(clientInputStream.readUTF());
            ServerManager.getInstance().addClient(this);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        String clientMessage;
        while (true) {
            synchronized (lock) {
                try {
                    clientMessage = clientInputStream.readUTF();
                    lastCommand = getInputReady(clientMessage);
                    ////////////////////////////////////////////////////
                    ServerManager.getInstance().addClientRequest(this);
                    lock.wait();
                } catch (SocketException socketException) {
                    System.out.println("client disconnected.");
                    ServerManager.getInstance().removeClient(this);
                    break;
                } catch (IOException | InterruptedException exception) {
                    exception.printStackTrace();
                } catch (WrongTokenException e) {
                    System.err.println("token mismatch. clientHandler with token : " + token);
                }
            }
        }
        // TODO : end while when connection died
    }

    public void sendAnswer(String answer) {
        synchronized (lock) {
            answer = makeOutputReady(answer);
            try {
                clientOutputStream.writeUTF(answer);
                clientOutputStream.flush();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            lock.notify();
        }
    }

    private String getInputReady(String clientMessage) throws WrongTokenException {
        Matcher tokenMatcher = Pattern.compile("^T(\\d+)T").matcher(clientMessage);
        if (!tokenMatcher.find()) {
            throw new WrongTokenException();
        }
        int receivedToken = Integer.parseInt(tokenMatcher.group(1));
        if (this.token != receivedToken) {
            throw new WrongTokenException();
        }
        return clientMessage.substring(tokenMatcher.end());
    }

    private String makeOutputReady(String serverAnswer) {
        return serverAnswer;
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public String getLoggedInUsername() {
        return loggedInUsername;
    }

    public void setLoggedInUsername(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
    }

    public int getP2pPORT() {
        return p2pPORT;
    }

    public String getP2pIP() {
        return p2pIP;
    }

    //    public void sendP2PConnectionInfo () {
//        try {
//            Socket clientP2PSocket = new Socket(p2pIP, p2pPORT);
//
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
}

class WrongTokenException extends Exception {

}
