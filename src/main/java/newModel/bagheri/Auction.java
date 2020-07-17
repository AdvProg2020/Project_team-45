package newModel.bagheri;

import model.product.ProductSellInfo;
import model.user.Buyer;

import java.util.Date;
import java.util.HashMap;

public class Auction {
    private final ProductSellInfo productSellInfo;
    private final Date endTime;
    private int highestDidPrice;
    private Buyer winner;
    private final HashMap<Buyer, Integer> suggestedPrices;
    private final ChatRoom chatRoom;

    public Auction(ProductSellInfo productSellInfo, Date endTime) {
        this.productSellInfo = productSellInfo;
        this.endTime = endTime;
        highestDidPrice = 0;
        winner = null;
        suggestedPrices = new HashMap<>();
        chatRoom = new ChatRoom();
    }

    public ProductSellInfo getProductSellInfo() {
        return productSellInfo;
    }

    public Date getEndTime() {
        return endTime;
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
        if(suggestedPrice > highestDidPrice) {
            suggestedPrices.put(buyer, suggestedPrice);
            highestDidPrice = suggestedPrice;
            winner = buyer;
            return true;
        }
        return false;
    }
}
