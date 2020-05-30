package model.product;

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
    public HashMap convertToHashMap() {
        return null;
    }

    @Override
    public void setFieldsFromHashMap(HashMap theMap) {

    }
}
