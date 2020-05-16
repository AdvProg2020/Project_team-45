package model.user;

import model.*;
import model.log.BuyLog;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends User implements CartHolder {
    private Cart cart;
    private ArrayList<CodedDiscount> listOfCodedDiscounts;
    private int balance;
    private ArrayList<BuyLog> listOfBuyLogs;
    private HashMap<String, Rate> purchasedProducts; // productIds and rates

    public Buyer(PersonalInfo personalInfo) {
        super(personalInfo);
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public ArrayList<CodedDiscount> getListOfCodedDiscounts() {
        return listOfCodedDiscounts;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<BuyLog> getListOfBuyLogs() {
        return listOfBuyLogs;
    }

    public BuyLog getBuyLogById(String logId) {
        for (BuyLog buyLog : listOfBuyLogs) {
            if (buyLog.getMainLog().getLogId().equals(logId))
                return buyLog;
        }
        return null;
    }

    public boolean didBuyProduct(String productId) {
        for (String id : purchasedProducts.keySet()) {
            if (id.equals(productId)) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Rate> getPurchasedProducts() {
        return purchasedProducts;
    }

    public boolean canBuy() {
        return false;
    }

    public void purchase() {
    }

    public void rateProduct(Product product, Rate rate) {
    }

    @Override
    public String getRole() {
        return "Buyer";
    }
}
