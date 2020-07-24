package server.controller;

import server.controller.userControllers.UserController;
import server.model.Market;
import server.model.log.Log;
import server.model.product.Product;
import server.model.product.ProductSellInfo;
import server.model.user.Buyer;
import server.model.user.User;
import server.newModel.bagheri.Auction;
import server.newModel.bagheri.Massage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class AuctionController {
    private static final AuctionController instance = new AuctionController();
    private final UserController userController = UserController.getInstance();
    private final Market market;
    private Auction activeAuction; // hotam

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
            else if (!auction.isCompletePurchase())
                CompletingPurchase(auction);
        }
        return auctionInfosList;
    }

    public boolean hasActiveUserParticipatedInActiveAuction() {
        return activeAuction.getSuggestedPrices().get(UserController.getActiveUser()) != null;
    }

    public void recordProposedPrice(String proposedPriceText) throws Exception {
        if (!activeAuction.isAvailable())
            throw new Exception("The auction is over!");
        Buyer activeBuyer = (Buyer) UserController.getActiveUser();
        int proposedPrice = Integer.parseInt(proposedPriceText);
        int usableBalance = activeBuyer.getWallet().getUsableBalance();
        Integer previousPrice = activeAuction.getSuggestedPrices().get(activeBuyer);
        if ((previousPrice == null && proposedPrice > usableBalance) ||
                (previousPrice != null && proposedPrice - previousPrice > usableBalance))
            throw new Exception("The wallet balance is not good!");
        if (proposedPrice < activeAuction.getBasePrice())
            throw new Exception("The bid price is lower than the base price!");
        if (previousPrice != null && proposedPrice < previousPrice)
            throw new Exception("The bid price is lower than the previous bid price!");
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
        return activeAuction.getChatRoom().getAllMassagesText();
    }

    public void addMassageToActiveAuction(String newMassageContent) {
        User user = UserController.getActiveUser();
        Massage newMassage = new Massage(user, newMassageContent);
        activeAuction.getChatRoom().addMassage(newMassage);
    }

    public void createAuction(HashMap<String, String> auctionInfo) {
        Product product = market.getProductById(auctionInfo.get("productId"));
        ProductSellInfo productSellInfo = product.getSellersList().get(UserController.getActiveUser());
        try {
            Auction newAuction = new Auction(productSellInfo,
                    (new SimpleDateFormat("yyyy/MM/dd")).parse(auctionInfo.get("endDate")),
                    Integer.parseInt(auctionInfo.get("basePrice")));
            productSellInfo.setAuction(newAuction);
            market.addAuctionToList(newAuction);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
    }

    private void CompletingPurchase(Auction auction) {
        Buyer winner = auction.getWinner();
        if (winner != null) {
            int finalPrice = auction.getHighestDidPrice();
            for (Buyer buyer : auction.getSuggestedPrices().keySet()) {
                buyer.getWallet().changeUsableBalance(finalPrice);
            }

            ArrayList<ProductSellInfo> sellingProduct = new ArrayList<>();
            sellingProduct.add(auction.getProductSellInfo());
            Log log = new Log(sellingProduct, winner.getUsername(), "-", winner.getPersonalInfo().getPhoneNumber());
            winner.auctionPurchase(log, finalPrice);
        }
        auction.completingPurchase();
    }
}