package model.user;

import model.*;
import model.log.BuyLog;
import model.log.Log;
import model.log.SellLog;

import java.util.ArrayList;
import java.util.Date;
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
        this.balance = 100;
        this.listOfCodedDiscounts = new ArrayList<>();
        this.listOfBuyLogs = new ArrayList<>();
        this.purchasedProducts = new HashMap<>();
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

    public void setBalance(int balance) {
        this.balance = balance;
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

    public CodedDiscount getDiscountByCode(String discountCode) {
        for (CodedDiscount discount : listOfCodedDiscounts) {
            if (discount.getCode().equalsIgnoreCase(discountCode)) {
                return discount;
            }
        }
        return null;
    }

    public boolean isDiscountCodeValid(String discountCode) {
        CodedDiscount discount = getDiscountByCode(discountCode);
        if (discount == null) {
            return false;
        }
        Date currentDate = new Date();
        if (discount.getStartDate().compareTo(currentDate) <= 0 && discount.getEndDate().compareTo(currentDate) >= 0) {
            return true;
        }
        return false;
    }

    public void purchase(Log log) {
        for (ProductSellInfo sellInfo : getCart().getProductSellInfos()) {
            purchasedProducts.put(sellInfo.getProduct().getProductId(), null);
            sellInfo.getProduct().addSellCount();
            sellInfo.addSellCount();
            sellInfo.getAllBuyers().put(this, getCart().getProductAmountById(sellInfo.getProduct().getProductId()));
            Seller seller = sellInfo.getSeller();
            SellLog sellLog = new SellLog(log, seller);
            seller.getListOfSellLogs().add(sellLog);
            seller.setBalance(seller.getBalance() + sellLog.getIncome());
        }
        BuyLog buyLog = new BuyLog(log);
        listOfBuyLogs.add(buyLog);
        //System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + getCart().getTotalPrice());
        log.calculateFinalPrice();
        setBalance(getBalance() - log.getFinalPrice());
        cart = new Cart();
    }

    public boolean canBuy() {
        return false;
    }

    public void rateProduct(Product product, Rate rate) {
    }

    @Override
    public String getRole() {
        return "buyer";
    }

    public void removeCodedDiscountFromList(CodedDiscount removingCodedDiscount) {
        listOfCodedDiscounts.remove(removingCodedDiscount);
    }

    public void addCodedDiscount(CodedDiscount addingDiscount) {
        listOfCodedDiscounts.add(addingDiscount);
    }
}
