package model.log;

import controller.userControllers.BuyerController;
import model.*;
import model.product.ProductSellInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Log extends IdRecognized implements Savable {
    private Date date;
    private String buyerUsername; // hatam : change to buyer :|
    private ArrayList<ProductSellInfo> sellingProducts;
    private CodedDiscount appliedDiscount;
    private int buyerFee;
    private int finalPrice;
    private String address;
    private String phoneNumber;
    private String deliveryStatus;


    public Log(ArrayList<ProductSellInfo> sellingProducts, String buyerUsername, String address, String phoneNumber) {
        this.id = "" + IdKeeper.getInstance().getLogsNewId();
        this.date = new Date();
        this.sellingProducts = sellingProducts;
        this.buyerUsername = buyerUsername;
        this.finalPrice = getFinalPrice();
        this.buyerFee = getFinalPrice();
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Log(String id) {
        this.id = id;
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
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
//        result.put("id", id);
//        result.put("date", date);
//        result.put("buyerUsername", buyerUsername);
//        result.put("finalPrice", finalPrice);
//        result.put("buyerFee", buyerFee);
//        result.put("appliedDiscount", appliedDiscount.getId());
//        result.put("address", address);
//        result.put("phoneNumber", phoneNumber);
//        result.put("deliveryStatus", deliveryStatus);
//
//        ArrayList<String> sellInfos = new ArrayList<>();
//        for (ProductSellInfo sellingProduct : sellingProducts) {
//            sellInfos.add(sellingProduct.getId());
//        }
//        result.put("sellingProducts", sellInfos);
        return result;
    }


    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        id = theMap.get("id");
//        date = (Date) theMap.get("date");
        buyerUsername = theMap.get("buyerUsername");
        finalPrice = Integer.parseInt(theMap.get("finalPrice"));
        buyerFee = Integer.parseInt(theMap.get("buyerFee"));
        appliedDiscount = Market.getInstance().getCodedDiscountByCode(theMap.get("appliedDiscount"));
        address = theMap.get("address");
        phoneNumber = theMap.get("phoneNumber");
        deliveryStatus = theMap.get("deliveryStatus");

//        ArrayList<String> sellInfoIds = (ArrayList<String>) theMap.get("sellingProducts");
//        sellingProducts = new ArrayList<>();
//        for (String sellInfoId : sellInfoIds) {
//            sellingProducts.add(Market.getInstance().getProductSellInfoById(sellInfoId));
//        }
    }

//    enum DeliveryStatus{
//        PREPARATION,
//        SENDING,
//        DELIVERED;
//    }
}