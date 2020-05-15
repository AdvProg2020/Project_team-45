package model.log;

import model.CodedDiscount;
import model.ProductSellInfo;
import model.user.Buyer;

import java.util.ArrayList;
import java.util.Date;

public class Log {
    private final String logId;
    private final Date date;
    private final Buyer buyer;
    private ArrayList<ProductSellInfo> sellingProducts;
    private CodedDiscount appliedDiscount;
    private int buyerFee;
    private int finalPrice;
    private final String address;
    private final String phoneNumber;
    private String deliveryStatus;

    public Log(String logId,Date date, ArrayList<ProductSellInfo> sellingProducts, Buyer buyer, CodedDiscount appliedDiscount, String address, String phoneNumber) {
        this.logId = logId;
        this.date = date;
        this.sellingProducts = new ArrayList<ProductSellInfo>();
        for (ProductSellInfo productSellInfo : sellingProducts) {
            this.sellingProducts.add(productSellInfo.clone());
        }
        this.buyer = buyer;
        this.appliedDiscount = appliedDiscount.clone();
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
        // TODO
        return 0;
    }

    private int calculateBuyerFee() {
        // TODO
        return 0;
    }

    public String getBuyerUsername() {
        return buyer.getPersonalInfo().getUsername();
    }

    public int getAppliedDiscountPercentage() {
        return appliedDiscount.getPercentage();
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