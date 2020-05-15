package model.user;

import model.Company;
import model.Off;
import model.Product;
import model.ProductSellInfo;
import model.log.SellLog;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends User {
    private Company company;
    private ArrayList<SellLog> listOfSellLogs;
    private HashMap<Product, ProductSellInfo> availableProducts;
    private HashMap<String, Off> listOfOffs;
    private int balance;

    public Seller(PersonalInfo personalInfo, Company company) {
        super(personalInfo);
        this.company = company;
        listOfOffs = new HashMap<String, Off>();
        availableProducts = new HashMap<>();
        listOfOffs = new HashMap<>();
    }

    public Company getCompany() {
        return company;
    }

    public ArrayList<SellLog> getListOfSellLogs() {
        return listOfSellLogs;
    }

    public HashMap<Product, ProductSellInfo> getAvailableProducts() {
        return availableProducts;
    }

    public Product getAvailableProductById(String productId) {
        return null;
    }

    public HashMap<String, Off> getListOfOffs() {
        return listOfOffs;
    }

    public int getBalance() {
        return balance;
    }

    public Off getOffByOffId(String offId) {
        return null;
    }

    public void addProduct(Product product, ProductSellInfo productSellInfo) {

    }

    public void removeProductByProductId(String productId) {

    }

    @Override
    public String getRole() {
        return "Seller";
    }
}
