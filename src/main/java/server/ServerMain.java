package server;

import server.model.Market;
import server.network.ServerEntranceSocket;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) {
        Market.getInstance().initialize();
        // connect to bank ...
        try {
            new ServerEntranceSocket().run();
        } catch (IOException exception) {
            System.err.println("error in running socket");
        }
    }
}
