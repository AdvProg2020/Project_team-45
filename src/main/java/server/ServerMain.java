package server;

import server.controller.ServerManager;
import server.model.Market;
import server.network.ServerEntranceSocket;

import java.util.Scanner;

public class ServerMain {

    public static void main(String[] args) {
        Market.getInstance().initialize();
        ServerManager.getInstance().start();
        // connect to bank ...
        ServerEntranceSocket.getInstance().start();

        waitForExit();
    }

    private static void waitForExit() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                endServer();
                break;
            }
        }
    }

    private static void endServer() {
        ServerManager.getInstance().setRunning(false);
        ServerEntranceSocket.getInstance().stopSocket(false);
        // TODO : nedaeai : save to database

    }
}
