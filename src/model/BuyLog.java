package model;

import java.util.Date;

public class BuyLog extends Log {
    private int codedDiscountAmount;
    private String sellerUsername;

    public BuyLog(String logId, Date date, int finalPrice, String address, String sellerUsername) {
        super(logId, date, finalPrice, address);
        this.sellerUsername = sellerUsername;
    }

    @Override
    public String toString() {
        return "BuyLog{" +
                "logId='" + logId + '\'' +
                '}';
    }
}
