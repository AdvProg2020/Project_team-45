package model;

import model.user.Buyer;

public class Rate {
    private final Buyer buyer;
    private int score;
    private final String productId;

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
}
