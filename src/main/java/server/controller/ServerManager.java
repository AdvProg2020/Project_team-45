package server.controller;

import server.controller.userControllers.UserController;
import server.network.ClientHandler;
import server.network.MethodStringer;

import java.util.ArrayList;

public class ServerManager extends Thread {
    private static final ServerManager instance = new ServerManager();

    public static ServerManager getInstance() {
        return instance;
    }

    private ServerManager() {
    }

    private final Object lock = new Object();
    private final ArrayList<ClientHandler> clientsQueue = new ArrayList<>();

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (clientsQueue.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                fetchCommand();
            }
        }

    }

    private void fetchCommand() {
        ClientHandler client = clientsQueue.remove(0);
        String command = client.getLastCommand();
        putClientOnServer(client);
        String answer = MethodStringer.runMethodReturnJson(command);
        putServerOnClient(client);
        client.sendAnswer(answer);
    }

    private void putServerOnClient(ClientHandler client) {
        client.setLoggedInUsername(UserController.getActiveUserUsername());
        // TODO : activeProduct , ...
    }

    private void putClientOnServer(ClientHandler client) {
        UserController.setActiveUserByUsername(client.getLoggedInUsername());
        UserController.setLoggedIn(client.getLoggedInUsername() != null);
        // TODO : activeProduct , ...
    }

    public void addClient(ClientHandler client) {
        synchronized (lock) {
            clientsQueue.add(client);
            lock.notify();
        }
    }
}
