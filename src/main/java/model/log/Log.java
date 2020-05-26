package model.log;

import controller.userControllers.BuyerController;
import model.CodedDiscount;
import model.Market;
import model.ProductSellInfo;
import model.user.Buyer;

import java.util.ArrayList;
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
        this.sellingProducts = sellingProducts;
        this.buyerUsername = buyerUsername;
        this.finalPrice = getFinalPrice();
        this.buyerFee = getFinalPrice();
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
        //calculateFinalPrice();
        return finalPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getBuyerFee() {
        return buyerFee;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    @Override
    protected Log clone() throws CloneNotSupportedException {
        return (Log) super.clone();
    }

    public void calculateFinalPrice(){
        int result = 0;
        for (ProductSellInfo sellInfo : sellingProducts) {
            System.out.println("########################" + ((Buyer) BuyerController.getInstance().getBuyer()).getCart().getCartProducts());
            result += sellInfo.getFinalPrice() * BuyerController.getInstance().getBuyer().getCart()
                    .getProductAmountById(sellInfo.getProduct().getProductId());
        }
        result = (100 - getAppliedDiscountPercentage()) * result / 100;
        finalPrice = result;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public int getAppliedDiscountPercentage() {
        if (appliedDiscount == null) {
            return 0;
        }
        return appliedDiscount.getPercentage();
    }

    public void setAppliedDiscount(CodedDiscount appliedDiscount) {
        this.appliedDiscount = appliedDiscount;
        calculateFinalPrice();
        finalPrice = getFinalPrice();
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