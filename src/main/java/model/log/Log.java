package model.log;

import model.CodedDiscount;
import model.Market;
import model.ProductSellInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Log {
    private static Integer newLogId = 1;
    private final String logId;
    private final Date date;
    private final String buyerUsername;
    private ArrayList<ProductSellInfo> sellingProducts;
    private CodedDiscount appliedDiscount;
    private int buyerFee;
    private int finalPrice;
    private final String address;
    private final String phoneNumber;
    private String deliveryStatus;

    public Log(ArrayList<ProductSellInfo> sellingProducts, String buyerUsername, String address, String phoneNumber) {
        this.logId = newLogId.toString();
        newLogId++;
        this.date = new Date();
        this.sellingProducts = new ArrayList<>();
        for (ProductSellInfo productSellInfo : sellingProducts) {
            this.sellingProducts.add(productSellInfo.clone());
        }
        this.buyerUsername = buyerUsername;
        this.finalPrice = calculateFinalPrice();
        this.buyerFee = calculateBuyerFee();
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getLogId() {
        return logId;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<ProductSellInfo> getSellingProducts() {
        return sellingProducts;
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

    @Override
    protected Log clone() throws CloneNotSupportedException {
        return (Log) super.clone();
    }

    public int calculateFinalPrice(){
        int result = 0;
        for (ProductSellInfo sellInfo : sellingProducts) {
            sellInfo.getFinalPrice();
        }
        return 0;
    }

    private int calculateBuyerFee() {
        // TODO
        return 0;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public int getAppliedDiscountPercentage() {
        return appliedDiscount.getPercentage();
    }

    public void setAppliedDiscount(CodedDiscount appliedDiscount) {
        this.appliedDiscount = appliedDiscount;
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