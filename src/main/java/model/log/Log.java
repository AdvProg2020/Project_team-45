package model.log;

import controller.userControllers.BuyerController;
import model.CodedDiscount;
import model.IdRecognized;
import model.Savable;
import model.product.ProductSellInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Log implements Savable, IdRecognized {
    private static Integer newLogId = 1;
    private final String id;
    private final Date date;
    private final String buyerUsername;
    private final ArrayList<ProductSellInfo> sellingProducts;
    private CodedDiscount appliedDiscount;
    private final int buyerFee;
    private int finalPrice;
    private final String address;
    private final String phoneNumber;
    private String deliveryStatus;

    public Log(ArrayList<ProductSellInfo> sellingProducts, String buyerUsername, String address, String phoneNumber) {
        this.id = newLogId.toString();
        newLogId++;
        this.date = new Date();
        this.sellingProducts = sellingProducts;
        this.buyerUsername = buyerUsername;
        this.finalPrice = getFinalPrice();
        this.buyerFee = getFinalPrice();
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getId() {
        return id;
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
            //System.out.println("########################" + ((Buyer) BuyerController.getInstance().getBuyer()).getCart().getCartProducts());
            result += sellInfo.getFinalPrice() * BuyerController.getInstance().getBuyer().getCart()
                    .getProductAmountById(sellInfo.getProduct().getId());
        }
        result = (100 - getAppliedDiscountPercentage()) * result / 100;
        //System.out.println("####################" + result);
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

    @Override
    public HashMap<String, Object> convertToHashMap() {
        return null;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {

    }

//    enum DeliveryStatus{
//        PREPARATION,
//        SENDING,
//        DELIVERED;
//    }
}