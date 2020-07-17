package server.newModel.bagheri;

import server.newModel.IdRecognized;

public class Rate extends IdRecognized {
    private String buyerId;
    private String productId;
    private int score;

    public String getBuyerId() {
        return buyerId;
    }

    public String getProductId() {
        return productId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
