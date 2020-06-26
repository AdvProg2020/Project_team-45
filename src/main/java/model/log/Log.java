package model.log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.deploy.security.WSeedGenerator;
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
        calculateFinalPrice();
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
            result += sellInfo.getFinalPrice() * BuyerController.getInstance().getBuyer().getCart()
                    .getProductAmountById(sellInfo.getId());
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

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("id", id);
        result.put("date", (new Gson()).toJson(date));
        result.put("buyerUsername", buyerUsername);
        result.put("finalPrice", "" + finalPrice);
        result.put("buyerFee", "" + buyerFee);
        if (appliedDiscount != null) {
            result.put("appliedDiscount", appliedDiscount.getId());
        }
        result.put("address", address);
        result.put("phoneNumber", phoneNumber);
        result.put("deliveryStatus", deliveryStatus);

        ArrayList<String> sellInfos = new ArrayList<>();
        for (ProductSellInfo sellingProduct : sellingProducts) {
            sellInfos.add(sellingProduct.getId());
        }
        result.put("sellingProducts", (new Gson()).toJson(sellInfos));
        return result;
    }


    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        id = theMap.get("id");
        date = (new Gson()).fromJson(theMap.get("date"), Date.class);
        buyerUsername = theMap.get("buyerUsername");
        finalPrice = Integer.parseInt(theMap.get("finalPrice"));
        buyerFee = Integer.parseInt(theMap.get("buyerFee"));
        if (theMap.containsKey("appliedDiscount")) {
            appliedDiscount = Market.getInstance().getCodedDiscountByCode(theMap.get("appliedDiscount"));
        }
        address = theMap.get("address");
        phoneNumber = theMap.get("phoneNumber");
        deliveryStatus = theMap.get("deliveryStatus");

        ArrayList<String> sellInfos = (new Gson()).fromJson(theMap.get("sellingProducts"), new TypeToken<ArrayList<String>>(){}.getType());
        sellingProducts = new ArrayList<>();
        for (String sellInfo : sellInfos) {
            sellingProducts.add(Market.getInstance().getProductSellInfoById(sellInfo));
        }
    }

//    enum DeliveryStatus{
//        PREPARATION,
//        SENDING,
//        DELIVERED;
//    }
}