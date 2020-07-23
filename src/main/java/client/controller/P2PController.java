package client.controller;

import client.network.ClientSocket;
import client.network.MethodStringer;

public class P2PController {

    private static P2PController instance;

    public static P2PController getInstance() {
        return instance;
    }

    private P2PController() {
    }

    public void receiveFile(String fileInfoForServer) {
        String p2pIP = ClientSocket.getInstance().getP2pSocket().getIP();
        int p2pPort = ClientSocket.getInstance().getP2pSocket().getPORT();
        if (tellSellerSendFile(fileInfoForServer, p2pIP, p2pPort)) {
            // TODO : file is being sent for me, what to do? (option : wait and notify me by P2PSocket)
        } else {
            // TODO : couldn't connect to seller
        }
    }

    private boolean tellSellerSendFile(String fileInfoForServer, String p2pIP, int p2pPort) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "tellSellerSendFile", fileInfoForServer, p2pIP, p2pPort);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }
}
