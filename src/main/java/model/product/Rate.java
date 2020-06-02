package model.product;

import model.IdKeeper;
import model.IdRecognized;
import model.Market;
import model.Savable;
import model.user.Buyer;

import java.util.HashMap;

public class Rate extends IdRecognized implements Savable {
    private Buyer buyer;
    private int score;
    private String productId;

    public Rate(Buyer user, int score, String productId) {
        this.id = "" + IdKeeper.getInstance().getRatesNewId();
        this.buyer = user;
        this.score = score;
        this.productId = productId;
    }

    public Rate(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public String getProductId() {
        return productId;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("buyer", buyer.getId());
        result.put("score", "" + score);
        result.put("productId", productId);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        buyer = (Buyer) Market.getInstance().getUserById(theMap.get("buyer"));
        score = Integer.parseInt(theMap.get("score"));
        productId = theMap.get("productId");
    }

    @Override
    public String getId() {
        return id;
    }
}