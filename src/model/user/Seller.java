package model.user;

import model.Company;
import model.Off;
import model.Product;
import model.SellLog;
import model.user.User;

import java.util.ArrayList;

public class Seller extends User {
    private Company company;
    private ArrayList<SellLog> listOfSellLogs;
    private ArrayList<Product> listOfProducts;
    private ArrayList<Off> listOfOffs;
    private int balance;

    public Seller(String username, String firstName, String lastName, String emailAddress,
                  String phoneNumber, String password, String role, Company company) {
        super(username, firstName, lastName, emailAddress, phoneNumber, password, role);
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public ArrayList<SellLog> getListOfSellLogs() {
        return listOfSellLogs;
    }

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    public Product getProductByProductId(String productId) {

    }

    public void editProductByProductId(String productId, productInfo ... ?) {

    }

    public void addProduct (productInfo ... ?){

    }

    public void removeProductByProductId (String productId) {

    }

    public ArrayList<Off> getListOfOffs() {
        return listOfOffs;
    }

    public Off getOffByOffId (String offId) {

    }

    public void editOffByOffId (String offId, offInfo ... ?) {

    }

    public void addOff(offInfo ... ?) {

    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String getRole() {
        return "Seller";
    }
}
