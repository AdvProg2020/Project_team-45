package model;

import model.user.User;

import java.util.ArrayList;

public class Market {
    private static Market marketInstance;
    private ArrayList<User> allUsers;
    private ArrayList<CodedDiscount> allCodedDiscounts;
    private ArrayList<Category> allCategories;
    private ArrayList<Product> allProducts;
    private boolean hasAdmin;

    private Market() {
        allUsers = new ArrayList<>();
        allCategories = new ArrayList<>();
        allCodedDiscounts = new ArrayList<>();
        allProducts = new ArrayList<>();
    }

    public static Market getInstance() {
        if (marketInstance == null)
            marketInstance = new Market();
        return marketInstance;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public ArrayList<CodedDiscount> getAllCodedDiscounts() {
        return allCodedDiscounts;
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public boolean getHasAdmin() {
        return hasAdmin;
    }

    public void changeHasAdmin() {
        hasAdmin = true;
    }

    public void addUser(User user) {

    }

    public Product getProductByProductId(String productId) {
        return null;
    }

    public CodedDiscount getCodedDiscountByCode(String code) {
        return null;
    }

    public Category getCategoryByName(String name) {
        return null;
    }

    public ArrayList<Product> filterProducts(ProductFilters productFilters) {
        return null;
    }

    public ArrayList<Product> sortProducts(String attribute) {
        return null;
    }

    public ArrayList<Off> filterOffs(OffFilters offFilters) {
        return null;
    }

    public ArrayList<Off> sortOffs(String attribute) {
        return null;
    }

    public void deleteUserByUsername(String username) {

    }

    public void removeProductByProductId(String productId) {

    }

    public void removeCodedDiscountByCode(String discountCode) {

    }

    public void addCategory(Category category) {

    }

    public void removeCategoryByName(String name) {

    }
}

