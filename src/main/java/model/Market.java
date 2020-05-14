package model;

import model.category.Category;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Market {
    private static Market marketInstance;
    private static ArrayList<String> availableSortsForProducts;
    private static ArrayList<String> availableSortsForOffs;
    private final ArrayList<User> allUsers;
    private final ArrayList<CodedDiscount> allCodedDiscounts;
    private final ArrayList<Category> allCategories;
    private final ArrayList<Product> allProducts;
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

    public static ArrayList<String> getAvailableSortsForProducts() {
        return availableSortsForProducts;
    }

    public static ArrayList<String> getAvailableSortsForOffs() {
        return availableSortsForOffs;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public boolean doesHaveAdmin() {
        return hasAdmin;
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
        for (User user : allUsers) {
            if (user.getPersonalInfo().getUsername().equals(username))
                return user;
        }
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

