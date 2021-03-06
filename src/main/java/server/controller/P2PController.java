package server.controller;

import server.model.Market;
import server.network.ClientHandler;
import server.newModel.nedaei.FileProduct;

import java.io.*;
import java.net.Socket;

public class P2PController {

    private static P2PController instance = new P2PController();

    public static P2PController getInstance() {
        return instance;
    }

    private P2PController() {
    }

    public boolean tellSellerSendFile(String fileId, String p2pIP, Integer p2pPort) {
        FileProduct fileProduct = (FileProduct) Market.getInstance().getProductById(fileId);
        String sellerUserName = fileProduct.getSellerUserName(); // TODO nedaeai : get sellerUsername from fileInfoForServer
        String message = fileProduct.getPathInClient(); // TODO nedaeai : make message for seller P2PSocket to send file // not contain "%, &"
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
            System.out.println("////////////" + sellerResponse + "/////////");
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
