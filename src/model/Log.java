package model;

import java.util.ArrayList;
import java.util.Date;

public abstract class Log {
    protected String logId;
    protected Date date;
    protected int finalPrice;
    protected ArrayList<Product> listOfProducts;
    protected String deliveryStatus;
    protected String type;
    protected String address;

    public Log(String logId, Date date, int finalPrice, String address) {
        this.logId = logId;
        this.date = date;
        this.finalPrice = finalPrice;
        this.address = address;
    }
}
