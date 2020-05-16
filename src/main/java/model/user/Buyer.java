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
    private HashMap<Product, Rate> purchasedProducts;

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

    public boolean canBuy() {
        return false;
    }

    public void purchase() {
    }

    public BuyLog getOrderByOrderId(String orderId) {
        return null;
    }

    public void rateProduct(Product product, Rate rate) {
    }

    @Override
    public String getRole() {
        return "Buyer";
    }
}
