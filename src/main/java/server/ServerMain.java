package server;

import server.controller.DatabaseController;
import server.controller.ServerManager;
import server.model.Market;
import server.network.BankSocket;
import server.network.ServerEntranceSocket;

import java.io.IOException;
import java.util.Scanner;

public class ServerMain {

    public static void main(String[] args) {
        Market.getInstance().initialize();
        ServerManager.getInstance().start();
        try {
            BankSocket.ConnectToBankServer();
            Market.getInstance().initializeBankVariables();
        } catch (IOException exception) {
            System.err.println("error in running socket");
            exception.printStackTrace();
        }
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
//        BankSocket.exit();
        // TODO : nedaeai : save to database
        DatabaseController.getInstance().writeToDatabase();
    }
}
