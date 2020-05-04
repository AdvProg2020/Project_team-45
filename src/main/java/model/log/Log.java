package model.log;

import model.Product;

import java.util.ArrayList;
import java.util.Date;

public class Log {
    private String logId;
    private Date date;
    private ArrayList<Product> listOfProducts;
    private int finalPrice;
    private String address;
    private String phoneNumber;
    private String deliveryStatus;

    public Log(String logId, Date date, int finalPrice, String address) {
        this.logId = logId;
        this.date = date;
        this.finalPrice = finalPrice;
        this.address = address;
    }

    public String getLogId() {
        return logId;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void sendOrder(){

    }

    public void deliverOrder(){

    }

//    enum DeliveryStatus{
//        PREPARATION,
//        SENDING,
//        DELIVERED;
//    }
}