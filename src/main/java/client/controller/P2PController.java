package client.controller;

import client.network.ClientSocket;
import client.network.MethodStringer;

public class P2PController {
    private static final P2PController instance = new P2PController();

    public static P2PController getInstance() {
        return instance;
    }

    private P2PController() {
    }

    public void receiveFile(String fileInfoForServer) {
        String p2pIP = ClientSocket.getInstance().getP2pSocket().getIP();
        int p2pPort = ClientSocket.getInstance().getP2pSocket().getPORT();
        if (tellSellerSendFile(fileInfoForServer, p2pIP, p2pPort)) {
            System.out.println("connected to seller successfully");
        } else {
            System.out.println("could not connect to seller");
        }
    }

    public boolean tellSellerSendFile(String fileId, String p2pIP, Integer p2pPort) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "tellSellerSendFile", fileId, p2pIP, p2pPort);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }
}
