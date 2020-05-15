package model.log;

public class BuyLog {
    private Log mainLog;

    public BuyLog(Log log) {
        this.mainLog = log;
    }

    public Log getMainLog() {
        return mainLog;
    }

    //    public BuyLog(String logId, Date date, int finalPrice, String address, String sellerUsername) {
//        super(logId, date, finalPrice, address);
//        this.sellerUsername = sellerUsername;
//    }
}
