package model.product;

import model.Market;
import model.Savable;
import model.user.Buyer;

import java.util.HashMap;

public class Rate implements Savable {
    private Buyer buyer;
    private int score;
    private String productId;

    public Rate() {
    }

    public Rate(Buyer user, int score, String productId) {
        this.buyer = user;
        this.score = score;
        this.productId = productId;
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
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("buyer", buyer.getId());
        result.put("score", score);
        result.put("productId", productId);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        buyer = (Buyer) Market.getInstance().getUserById((String) theMap.get("buyer"));
        score = (int) theMap.get("productId");
        productId = (String) theMap.get("productId");
    }
}