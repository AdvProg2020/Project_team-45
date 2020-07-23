package server.controller;

import server.model.Market;
import server.newModel.bagheri.Auction;

public class AuctionController {
    private static final AuctionController instance = new AuctionController();
    private final Market market;
    private Auction activeAuction;

    private AuctionController() {
        market = Market.getInstance();
    }

    public static AuctionController getInstance() {
        return instance;
    }

    public void setActiveAuctionById(String AuctionId) {
        //activeAuction = market.getAuctionById(AuctionId);
    }
}
