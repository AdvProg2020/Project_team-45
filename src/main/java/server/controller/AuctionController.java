package server.controller;

import server.controller.userControllers.UserController;
import server.model.Market;
import server.model.user.Buyer;
import server.model.user.User;
import server.newModel.bagheri.Auction;
import server.newModel.bagheri.Massage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AuctionController {
    private static final AuctionController instance = new AuctionController();
    private final UserController userController = UserController.getInstance();
    private final Market market;
    private Auction activeAuction;

    private AuctionController() {
        market = Market.getInstance();
    }

    public static AuctionController getInstance() {
        return instance;
    }

    public void setActiveAuctionById(String auctionId) {
        activeAuction = market.getAuctionById(auctionId);
    }

    public ArrayList<HashMap<String, String>> getAuctionInfosList() {
        ArrayList<HashMap<String, String>> auctionInfosList = new ArrayList<>();
        for (Auction auction : market.getAllAuction()) {
            if (auction.isAvailable())
                auctionInfosList.add(auction.getAuctionInfo());
        }
        return auctionInfosList;
    }

    public boolean hasActiveUserParticipatedInActiveAuction() {
        return activeAuction.getSuggestedPrices().get(UserController.getActiveUser()) != null;
    }

    public void recordProposedPrice(String proposedPriceText) throws IOException {
        Buyer activeBuyer = (Buyer) UserController.getActiveUser();
        int proposedPrice = Integer.parseInt(proposedPriceText);
        int usableBalance = activeBuyer.getWallet().getUsableBalance();
        Integer previousPrice = activeAuction.getSuggestedPrices().get(activeBuyer);
        if ((previousPrice == null && proposedPrice > usableBalance) ||
                (previousPrice != null && proposedPrice - previousPrice > usableBalance))
            throw new IOException("The wallet balance is not good!");
        if (proposedPrice < activeAuction.getBasePrice())
            throw new IOException("The bid price is lower than the base price!");
        if (previousPrice != null && proposedPrice < previousPrice)
            throw new IOException("The bid price is lower than the previous bid price!");
        activeAuction.addSuggestedPrice(activeBuyer, proposedPrice);
        activeBuyer.addAuction(activeAuction, proposedPrice);
    }

    public HashMap<String, String> getActiveAuctionInfos() {
        return activeAuction.getAuctionInfo();
    }

    public String getActiveUserProposedPrice() {
        return "" + activeAuction.getSuggestedPrices().get(UserController.getActiveUser());
    }

    public ArrayList<String> getActiveAuctionAllMassages() {
        ArrayList<String> allMassages = new ArrayList<>();
        for (Massage massage : activeAuction.getChatRoom().getAllMassages()) {
            allMassages.add(massage.toString());
        }
        return allMassages;
    }

    public void addMassageToActiveAuction(String newMassageContent) {
        User user = UserController.getActiveUser();
        Massage newMassage = new Massage(user, newMassageContent);
        activeAuction.getChatRoom().addMassage(newMassage);
    }
}