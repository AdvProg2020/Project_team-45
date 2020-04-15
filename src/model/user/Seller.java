package model.user;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends User {
    private Company company;
    private ArrayList<SellLog> listOfSellLogs;
    private HashMap<Product, SellerInfoForProduct> availableProducts;
    private ArrayList<Off> listOfOffs;
    private int balance;

    public Seller(PersonalInfo personalInfo, Company company) {
        super(personalInfo);
        this.company = company;
        listOfOffs = new ArrayList<>();
        availableProducts = new HashMap<>();
        listOfOffs = new ArrayList<>();
    }

    public Company getCompany() {
        return company;
    }

    public ArrayList<SellLog> getListOfSellLogs() {
        return listOfSellLogs;
    }

    public HashMap<Product, SellerInfoForProduct> getAvailableProducts() {
        return availableProducts;
    }

    public Product getAvailableProductByProductId(String productId) {
        return null;
    }

    public ArrayList<Off> getListOfOffs() {
        return listOfOffs;
    }

    public int getBalance() {
        return balance;
    }

    public void addProduct(Product product, SellerInfoForProduct sellerInfoForProduct) {

    }

    public void removeProductByProductId(String productId) {

    }

    public Off getOffByOffId(String offId) {
        return null;
    }

    public void addOff(Off off) {

    }

    @Override
    public String getRole() {
        return "Seller";
    }
}
