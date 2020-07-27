package server.network;

import server.controller.ServerManager;
import server.model.CodedDiscount;
import server.model.Off;
import server.model.category.Category;
import server.model.product.Product;
import server.model.product.ProductSellInfo;
import server.newModel.bagheri.Auction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientHandler extends Thread {

    private final int token;
    private final DataInputStream clientInputStream;
    private final DataOutputStream clientOutputStream;
    private final boolean isConnected;
    private final Object lock = new Object();
    private int messageCounter;

    private String p2pIP;
    private int p2pPORT;

    private String lastCommand;
    private String loggedInUsername;
    private Auction activeAuction;
    private Category activeCategory;
    private boolean isOffMenu;
    private CodedDiscount currentDiscount;
    private Off currentOff;
    private Product activeProduct;
    private ProductSellInfo activeProductSelInfo;
    private String activeSort;

    public ClientHandler(int token, DataInputStream clientInputStream, DataOutputStream clientOutputStream) {
        this.token = token;
        this.clientInputStream = clientInputStream;
        this.clientOutputStream = clientOutputStream;
        isConnected = true;
        messageCounter = 0;

        try {
            clientOutputStream.writeUTF(String.valueOf(token));
            clientOutputStream.flush();

            p2pIP = clientInputStream.readUTF();
            p2pPORT = Integer.parseInt(clientInputStream.readUTF());
            ServerManager.getInstance().addClient(this);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        String clientMessage;
        while (true) {
            synchronized (lock) {
                try {
                    clientMessage = clientInputStream.readUTF();
                    lastCommand = getInputReady(clientMessage);


                    ////////////////////////////////////////////////////
                    ServerManager.getInstance().addClientRequest(this);
                    lock.wait();
                } catch (SocketException | EOFException socketException) {
                    System.out.println("client disconnected.");
                    ServerManager.getInstance().removeClient(this);
                    break;
                } catch (IOException | InterruptedException exception) {
                    exception.printStackTrace();
                } catch (WrongTokenException e) {
                    System.err.println("token mismatch. clientHandler with token : " + token);
                }
            }
        }
        // TODO : end while when connection died
    }

    public void sendAnswer(String answer) {
        synchronized (lock) {
            answer = makeOutputReady(answer);
            try {
                clientOutputStream.writeUTF(answer);
                clientOutputStream.flush();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            lock.notify();
        }
    }

    private String getInputReady(String clientMessage) throws WrongTokenException {
        Matcher tokenMatcher = Pattern.compile("^(\\d+)T(\\d+)T").matcher(clientMessage);
        if (!tokenMatcher.find()) {
            throw new WrongTokenException();
        }
        int receivedToken = Integer.parseInt(tokenMatcher.group(2));
        int receivedMC = Integer.parseInt(tokenMatcher.group(1));
        if (this.token != receivedToken || receivedMC != messageCounter) {
            throw new WrongTokenException();
        }
        messageCounter++;
        return clientMessage.substring(tokenMatcher.end());
    }

    private String makeOutputReady(String serverAnswer) {
        return serverAnswer;
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public String getLoggedInUsername() {
        return loggedInUsername;
    }

    public void setLoggedInUsername(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
    }

    public int getP2pPORT() {
        return p2pPORT;
    }

    public String getP2pIP() {
        return p2pIP;
    }

    //    public void sendP2PConnectionInfo () {
//        try {
//            Socket clientP2PSocket = new Socket(p2pIP, p2pPORT);
//
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }


    public Auction getActiveAuction() {
        return activeAuction;
    }

    public void setActiveAuction(Auction activeAuction) {
        this.activeAuction = activeAuction;
    }

    public Category getActiveCategory() {
        return activeCategory;
    }

    public void setActiveCategory(Category activeCategory) {
        this.activeCategory = activeCategory;
    }

    public boolean isOffMenu() {
        return isOffMenu;
    }

    public void setOffMenu(boolean offMenu) {
        isOffMenu = offMenu;
    }

    public CodedDiscount getCurrentDiscount() {
        return currentDiscount;
    }

    public void setCurrentDiscount(CodedDiscount currentDiscount) {
        this.currentDiscount = currentDiscount;
    }

    public Off getCurrentOff() {
        return currentOff;
    }

    public void setCurrentOff(Off currentOff) {
        this.currentOff = currentOff;
    }

    public Product getActiveProduct() {
        return activeProduct;
    }

    public void setActiveProduct(Product activeProduct) {
        this.activeProduct = activeProduct;
    }

    public ProductSellInfo getActiveProductSelInfo() {
        return activeProductSelInfo;
    }

    public void setActiveProductSelInfo(ProductSellInfo activeProductSelInfo) {
        this.activeProductSelInfo = activeProductSelInfo;
    }

    public String getActiveSort() {
        return activeSort;
    }

    public void setActiveSort(String activeSort) {
        this.activeSort = activeSort;
    }
}

class WrongTokenException extends Exception {

}
