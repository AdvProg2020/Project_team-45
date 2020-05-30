package model.user;

import model.Company;
import model.Off;
import model.log.SellLog;
import model.product.Product;
import model.product.ProductSellInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends User {
    private final Company company;
    private final ArrayList<SellLog> listOfSellLogs;
    private final HashMap<Product, ProductSellInfo> availableProducts;
    private HashMap<String, Off> listOfOffs; // offIds and offs
    private int balance;

    public Seller(PersonalInfo personalInfo, Company company) {
        super(personalInfo);
        this.company = company;
        this.listOfSellLogs = new ArrayList<>();
        listOfOffs = new HashMap<>();
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
        for (Product product : availableProducts.keySet()) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public ProductSellInfo getAvailableProductSellInfoById(String productId) {
        for (Product product : availableProducts.keySet()) {
            if (product.getProductId().equals(productId)) {
                return availableProducts.get(product);
            }
        }
        return null;
    }

    public HashMap<String, Off> getListOfOffs() {
        return listOfOffs;
    }

    public int getBalance() {
        return balance;
    }

    public Off getOffByOffId(String offId) {
        return listOfOffs.get(offId);
    }

    public void addProduct(Product product, ProductSellInfo productSellInfo) {

    }

    public void removeProductByProductId(String productId) {
        for (Product product : availableProducts.keySet()) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                availableProducts.remove(product);
            }
        }
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String getRole() {
        return "seller";
    }

    public void removeProductFromSellerList(Product product) {
        availableProducts.remove(product);
    }
}
