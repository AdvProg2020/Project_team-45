package server;

import server.controller.ServerManager;
import server.model.Market;
import server.network.ServerEntranceSocket;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) {
        Market.getInstance().initialize();
        ServerManager.getInstance().start();
        // connect to bank ...
        try {
            new ServerEntranceSocket().run();
        } catch (IOException exception) {
            System.err.println("error in running socket");
        }
    }
}
