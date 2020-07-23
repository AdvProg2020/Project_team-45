package client.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AuctionController {
    private static final AuctionController instance = new AuctionController();

    private AuctionController() {
    }

    public static AuctionController getInstance() {
        return instance;
    }

    public void setActiveAuctionById(String AuctionId) {

    }

    public ArrayList<HashMap<String, String>> getAuctionInfosList() {
        return null;
    }

    public boolean hasActiveUserParticipatedInActiveAuction() {
        return false;
    }

    public void recordProposedPrice(String proposedPrice) throws IOException {

    }

    public HashMap<String, String> getActiveAuctionInfos() {
        return null;
    }

    public String getActiveUserProposedPrice() {
        return null;
    }

    public ArrayList<String> getActiveAuctionAllMassages() {
        return null;
    }

    public void addMassageToActiveAuction(String newMassage) {

    }
}