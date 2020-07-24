package server.newModel.bagheri;

import server.model.IdKeeper;
import server.model.IdRecognized;
import server.model.Savable;
import server.model.product.ProductSellInfo;
import server.model.user.Buyer;
import server.newModel.bagheri.chatRoom.ChatRoom;

import java.util.Date;
import java.util.HashMap;

public class Auction extends IdRecognized implements Savable {
    private final ProductSellInfo productSellInfo;
    private final Date endTime;
    private final int basePrice;
    private int highestDidPrice;
    private Buyer winner;
    private final HashMap<Buyer, Integer> suggestedPrices;
    private final ChatRoom chatRoom;
    private boolean CompletePurchase;

    public Auction(ProductSellInfo productSellInfo, Date endTime, int basePrice) {
        this.id = "" + IdKeeper.getInstance().getAuctionNewId();
        this.productSellInfo = productSellInfo;
        this.endTime = endTime;
        this.basePrice = basePrice;
        highestDidPrice = 0;
        winner = null;
        suggestedPrices = new HashMap<>();
        chatRoom = new ChatRoom();
    }

    @Override
    public String getId() {
        return id;
    }

    public ProductSellInfo getProductSellInfo() {
        return productSellInfo;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getHighestDidPrice() {
        return highestDidPrice;
    }

    public Buyer getWinner() {
        return winner;
    }

    public HashMap<Buyer, Integer> getSuggestedPrices() {
        return suggestedPrices;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public boolean addSuggestedPrice(Buyer buyer, int suggestedPrice) {
        if(suggestedPrice > basePrice) {
            Integer previousPrice = suggestedPrices.get(buyer);
            if(previousPrice == null || previousPrice < suggestedPrice) {
                suggestedPrices.put(buyer, suggestedPrice);
                if (suggestedPrice > highestDidPrice) {
                    highestDidPrice = suggestedPrice;
                    winner = buyer;
                }
                return true;
            }
        }
        return false;
    }

    public boolean equals(Auction auction) {
        return auction.id.equals(this.id);
    }

    public HashMap<String, String> getAuctionInfo() {
        HashMap<String, String> auctionInfo = new HashMap<>();
        auctionInfo.put("auctionId", this.id);
        auctionInfo.put("basePrice", "" + this.basePrice);
        auctionInfo.put("endDate", "" + endTime);
        HashMap<String, String> productInfo = productSellInfo.getProduct().getProductInfoForProductsList();
        auctionInfo.put("productId", productInfo.get("id"));
        auctionInfo.put("name", productInfo.get("name"));
        auctionInfo.put("averageScore", productInfo.get("averageScore"));
        auctionInfo.put("imageAddress", productInfo.get("imageAddress"));
        return auctionInfo;
    }

    public boolean isAvailable() {
        return endTime.compareTo(new Date()) >= 0;
    }

    public boolean isCompletePurchase() {
        return CompletePurchase;
    }

    public void completingPurchase() {
        CompletePurchase = true;
    }
}