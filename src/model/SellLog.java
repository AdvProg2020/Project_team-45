package model;

import java.util.Date;

public class SellLog extends Log {
    private int saleAmount;
    private String buyerUsername;

    public SellLog(String logId, Date date, int finalPrice, String address, String buyerUsername) {
        super(logId, date, finalPrice, address);
        this.buyerUsername = buyerUsername;
    }

    @Override
    public String toString() {
        return "SellLog{" +
                "logId='" + logId + '\'' +
                '}';
    }
}
