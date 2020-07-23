package server.controller;

import server.network.ClientHandler;

import java.io.*;
import java.net.Socket;

public class P2PController {

    private static P2PController instance;

    public static P2PController getInstance() {
        return instance;
    }

    private P2PController() {
    }

    private boolean tellSellerSendFile(String fileInfoForServer, String p2pIP, int p2pPort) {
        String sellerUserName = "";
        // TODO nedaeai : get sellerUsername from fileInfoForServer
        String message = "";
        // TODO nedaeai : make message for seller P2PSocket to send file // not contain "%, &"
        ClientHandler sellerHandler = ServerManager.getInstance().findUserClientHandlerByUsername(sellerUserName);
        if (sellerHandler == null) {
            return false;
        }
        try {
            Socket sellerSocket = new Socket(sellerHandler.getP2pIP(), sellerHandler.getP2pPORT());
            DataOutputStream dataOutputStream =
                    new DataOutputStream(new BufferedOutputStream(sellerSocket.getOutputStream()));
            DataInputStream dataInputStream =
                    new DataInputStream(new BufferedInputStream(sellerSocket.getInputStream()));
            message = "server:" + p2pIP + "&" + p2pPort + "&" + message;
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
            String sellerResponse = dataInputStream.readUTF();
            if (sellerResponse.equals("fail")) {
                return false;
            } else if (sellerResponse.equals("done")) {
                return true;
            }
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
