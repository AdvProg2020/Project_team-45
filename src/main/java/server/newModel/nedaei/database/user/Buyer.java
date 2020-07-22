package server.newModel.nedaei.database.user;

import server.model.CodedDiscount;
import server.model.log.BuyLog;
import server.model.log.Log;
import server.model.product.Rate;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends User implements CartHolder {
    private int cartId;
    private int balance;
//    private ArrayList<CodedDiscount> listOfCodedDiscounts;
//    private ArrayList<BuyLog> listOfBuyLogs;
//    private HashMap<String, Rate> purchasedProducts; // productIds and rates


    public Buyer(String username, String firstName, String lastName, String emailAddress, String phoneNumber,
                 String password, String avatarPath, String role) {
        super(username, firstName, lastName, emailAddress, phoneNumber, password, avatarPath, role);
    }


    public Buyer(int id, boolean justId) {
        super(id, justId);
    }

    @Override
    protected void readFromDataBase() {

    }

    @Override
    protected void saveToDataBase() {

    }

    @Override
    public Cart getCart() {
        return null;
    }

    @Override
    public void setCart(Cart cart) {
    }

    public ArrayList<CodedDiscount> getListOfCodedDiscounts() {
        return null;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ArrayList<BuyLog> getListOfBuyLogs() {
        return null;
    }

    public BuyLog getBuyLogById(int logId) {
        return null;
    }

    public boolean didBuyProduct(int productId) {
        return false;
    }

    public HashMap<String, Rate> getPurchasedProducts() {
        return null;
    }

    public CodedDiscount getDiscountById(int discountCodeId) {
        return null;
    }

    public boolean isDiscountCodeValid(int discountCodeId) {
        return false;
    }

    public void purchase(Log log) {
    }

    public void removeCodedDiscountFromList(CodedDiscount removingCodedDiscount) {
    }

    public void addCodedDiscount(CodedDiscount addingDiscount) {
    }
}
