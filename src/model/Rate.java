package model;

import model.user.Buyer;

public class Rate {
    private Buyer user;
    private int score;
    private Product product;

    public Rate(Buyer user, int score, Product product) {
        this.user = user;
        this.score = score;
        this.product = product;
    }

    public int getScore() {
        return score;
    }

    public Product getProduct() {
        return product;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
