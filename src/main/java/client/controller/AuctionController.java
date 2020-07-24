package client.controller;


import client.network.MethodStringer;

import java.util.ArrayList;
import java.util.HashMap;

public class AuctionController {
    private static final AuctionController instance = new AuctionController();

    private AuctionController() {
    }

    public static AuctionController getInstance() {
        return instance;
    }

    public void setActiveAuctionById(String auctionId) {
        try {
            MethodStringer.sampleMethod(getClass(), "setActiveAuctionById", auctionId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, String>> getAuctionInfosList() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(),
                    "getAuctionInfosList");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public boolean hasActiveUserParticipatedInActiveAuction() {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(),
                    "hasActiveUserParticipatedInActiveAuction");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public void recordProposedPrice(String proposedPrice) throws Exception {
        try {
            MethodStringer.sampleMethod(getClass(), "recordProposedPrice", proposedPrice);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public HashMap<String, String> getActiveAuctionInfos() {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(),
                    "getActiveAuctionInfos");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String getActiveUserProposedPrice() {
        try {
            return (String) MethodStringer.sampleMethod(getClass(),
                    "getActiveUserProposedPrice");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getActiveAuctionAllMassages() {
        try {
            return (ArrayList<String>) MethodStringer.sampleMethod(getClass(),
                    "getActiveAuctionAllMassages");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void addMassageToActiveAuction(String newMassage) {
        try {
            MethodStringer.sampleMethod(getClass(), "addMassageToActiveAuction", newMassage);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void createAuction(HashMap<String, String> auctionInfo) {
        try {
            MethodStringer.sampleMethod(getClass(), "createAuction", auctionInfo);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}