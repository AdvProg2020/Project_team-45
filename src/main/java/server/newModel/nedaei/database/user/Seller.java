package server.newModel.nedaei.database.user;

import server.model.Company;
import server.model.Off;
import server.model.log.SellLog;
import server.model.product.Product;
import server.model.product.ProductSellInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends User {
    private int companyId;
    private int balance;
//    private ArrayList<SellLog> listOfSellLogs;
//    private HashMap<Product, ProductSellInfo> availableProducts;
//    private HashMap<String, Off> listOfOffs; // offIds and offs

    public Seller(String username, String firstName, String lastName, String emailAddress, String phoneNumber,
                  String password, String avatarPath, String role) {
        super(username, firstName, lastName, emailAddress, phoneNumber, password, avatarPath, role);
    }

    public Seller(int id, boolean justId) {
        super(id, justId);
    }

    @Override
    protected void readFromDataBase() {

    }

    @Override
    protected void saveToDataBase() {

    }

    public Company getCompany() {
        return null;
    }

    public ArrayList<SellLog> getListOfSellLogs() {
        return null;
    }

    public HashMap<Product, ProductSellInfo> getAvailableProducts() {
        return null;
    }

    public Product getAvailableProductById(int productId) {
        return null;
    }

    public ProductSellInfo getAvailableProductSellInfoById(int productSellInfoId) {
        return null;
    }

    public HashMap<String, Off> getListOfOffs() {
        return null;
    }

    public int getBalance() {
        return balance;
    }

    public Off getOffByOffId(int offId) {
        return null;
    }

    public void addProduct(ProductSellInfo productSellInfo) {
    }

    public void removeProductByProductId(int productId) {
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void removeProductFromSellerList(Product product) {
    }

    public SellLog getSellLogById(int id) {
        return null;
    }
}
