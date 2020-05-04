package model.log;

public class SellLog {
    private String buyerUsername;
    private int offAmount;
    private Log log;

    public SellLog(int saleAmount, String buyerUsername, Log log) {
        this.offAmount = saleAmount;
        this.buyerUsername = buyerUsername;
        this.log = log;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public int getOffAmount() {
        return offAmount;
    }

    public Log getLog() {
        return log;
    }

    //    public SellLog(String logId, Date date, int finalPrice, String address, String buyerUsername) {
//        super(logId, date, finalPrice, address);
//        this.buyerUsername = buyerUsername;
//    }

}
