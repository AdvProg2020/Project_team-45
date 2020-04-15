package model.user;

import model.BuyLog;
import model.Cart;
import model.CodedDiscount;

import java.util.ArrayList;

public class Buyer extends User {
    private Cart cart;
    private ArrayList<CodedDiscount> listOfCodedDiscounts;
    private int balance;
    private ArrayList<BuyLog> listOfBuyLogs;

    public Buyer(String username, String firstName, String lastName, String emailAddress, String phoneNumber,
                 String password, String role, ArrayList<CodedDiscount> listOfCodedDiscounts) {
        super(username, firstName, lastName, emailAddress, phoneNumber, password, role);
        this.listOfCodedDiscounts = listOfCodedDiscounts;
    }

    public boolean canBuy(int price) {

    }

    public void buy() {

    }

    public ArrayList<BuyLog> getListOfBuyLogs() {

    }

    public BuyLog getOrderByOrderId(String orderId) {

    }

    public void rateProductByProductId(String productId, int rateAmount) {

    }

    int getBalance() {

    }

    ArrayList<CodedDiscount> getListOfCodedDiscounts() {

    }

    @Override
    public String getRole() {
        return "Buyer";
    }
}
