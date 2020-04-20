package model.log;

public class BuyLog {
    private int codedDiscountAmount;
    private String sellerUsername;
    private Log log;

    public BuyLog(int codedDiscountAmount, String sellerUsername, Log log) {
        this.codedDiscountAmount = codedDiscountAmount;
        this.sellerUsername = sellerUsername;
        this.log = log;
    }

    public int getCodedDiscountAmount() {
        return codedDiscountAmount;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public Log getLog() {
        return log;
    }

    //    public BuyLog(String logId, Date date, int finalPrice, String address, String sellerUsername) {
//        super(logId, date, finalPrice, address);
//        this.sellerUsername = sellerUsername;
//    }
}
