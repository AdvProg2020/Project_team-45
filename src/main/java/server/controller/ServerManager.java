package server.controller;

import server.controller.userControllers.UserController;
import server.network.ClientHandler;
import server.network.MethodStringer;

import java.util.ArrayList;

public class ServerManager extends Thread {
    private static final ServerManager instance = new ServerManager();

    private boolean isRunning;

    public static ServerManager getInstance() {
        return instance;
    }

    private final ArrayList<ClientHandler> activeClients = new ArrayList<>();

    private ServerManager() {
    }

    private final Object lock = new Object();
    private final ArrayList<ClientHandler> clientsQueue = new ArrayList<>();

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            synchronized (lock) {
                if (clientsQueue.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                if (!clientsQueue.isEmpty())
                    fetchCommand();
            }
        }
        System.out.println("manager done");
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
        client.setActiveAuction(AuctionController.getInstance().getActiveAuction());
        client.setActiveCategory(CategoryController.getInstance().getActiveCategory());
        client.setOffMenu(CategoryController.getInstance().isOffMenu());
        client.setCurrentDiscount(CodedDiscountController.getInstance().getCurrentDiscount());
        client.setCurrentOff(OffController.getInstance().getCurrentOff());
        client.setActiveProduct(ProductController.getInstance().getActiveProduct());
        client.setActiveProductSelInfo(ProductController.getInstance().getActiveProductSellInfo());
        client.setActiveSort(SortingController.getInstance().getActiveSort());
        // TODO : activeProduct , ...
    }

    private void putClientOnServer(ClientHandler client) {
        UserController.setActiveUserByUsername(client.getLoggedInUsername());
        UserController.setLoggedIn(client.getLoggedInUsername() != null);
        AuctionController.getInstance().setActiveAuction(client.getActiveAuction());
        CategoryController.getInstance().setActiveCategory(client.getActiveCategory());
        CategoryController.getInstance().setOffMenu(client.isOffMenu());
        CodedDiscountController.getInstance().setCurrentDiscount(client.getCurrentDiscount());
        OffController.getInstance().setCurrentOff(client.getCurrentOff());
        ProductController.getInstance().setActiveProduct(client.getActiveProduct());
        ProductController.getInstance().setActiveProductSellInfo(client.getActiveProductSelInfo());
        SortingController.getInstance().setActiveSort(client.getActiveSort());
        // TODO : activeProduct , ...
    }

    public void addClientRequest(ClientHandler requester) {
        synchronized (lock) {
            clientsQueue.add(requester);
            lock.notify();
        }
    }

    public void addClient(ClientHandler client) {
        activeClients.add(client);
    }

    public void removeClient(ClientHandler client) {
        activeClients.remove(client);
    }

    public ClientHandler findUserClientHandlerByUsername(String username) {
        for (ClientHandler activeClient : activeClients) {
            if (activeClient.getLoggedInUsername().equals(username))
                return activeClient;
        }
        return null;
    }

    public ArrayList<String> getOnlineUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        for (ClientHandler activeClient : activeClients) {
            String username = activeClient.getLoggedInUsername();
            if (username != null && !usernames.contains(username))
                usernames.add(activeClient.getLoggedInUsername());
        }
        return usernames;
    }

    public void setRunning(boolean running) {
        isRunning = running;
        synchronized (lock) {
            lock.notify();
        }
    }
}
