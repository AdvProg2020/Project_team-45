package model;

import model.category.Category;
import model.log.Log;
import model.user.User;

import java.util.ArrayList;

public class Market {
    private static Market marketInstance;
    private static ArrayList<String> availableSortsForProducts;
    private static ArrayList<String> availableSortsForOffs;
    private final ArrayList<User> allUsers;
    private final ArrayList<CodedDiscount> allCodedDiscounts;
    private final ArrayList<Category> allCategories;
    private final ArrayList<Category> mainCategories;
    private final ArrayList<Product> allProducts;
    private final ArrayList<Log> allLogs;
    private final ArrayList<Off> allOffs;
    private boolean hasAdmin;

    private Market() {
        allLogs = new ArrayList<Log>();
        allOffs = new ArrayList<Off>();
        allUsers = new ArrayList<User>();
        allCodedDiscounts = new ArrayList<CodedDiscount>();
        allCategories = new ArrayList<Category>();
        mainCategories = new ArrayList<Category>();
        allProducts = new ArrayList<Product>();
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

    public ArrayList<Off> getAllOffs() {
        return allOffs;
    }

    public ArrayList<Log> getAllLogs() {
        return allLogs;
    }

    public ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public ArrayList<Category> getMainCategories() {
        return mainCategories;
    }

    public ArrayList<CodedDiscount> getAllCodedDiscounts() {
        return allCodedDiscounts;
    }

    public Category getMainCategoryByName(String name) {
        for (Category mainCategory : mainCategories) {
            if (mainCategory.getName().equals(name)) {
                return mainCategory;
            }
        }
        return null;
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

    public ArrayList<Product> getAllDiscountedProductsList() {
        ArrayList<Product> allDiscountedProductsList = new ArrayList<Product>();
        for (Product product : allProducts) {
            if (product.isInOff()) {
                allDiscountedProductsList.add(product);
            }
        }
        return allDiscountedProductsList;
    }

    public boolean isDiscountCodeValid(String discountCode) {
        return false;
    }

    public Product getProductById(String productId) {
        for (Product product : allProducts) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public Category getCategoryByName(String name) {
        for (Category category : allCategories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
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

    public void removeUserFromAllUsers(User user) {
        allUsers.remove(user);
    }

    public void removeProductByProductId(String productId) {

    }



    public void removeCodedDiscountByCode(String discountCode) {

    }

    public void addCategory(Category category) {

    }

    public void removeCategoryByName(String name) {

    }

    public void removeOffById(String offId) {
        allOffs.removeIf(off -> off.getOffId().equals(offId));
    }


    public Product getProductById(String Id) {
        for (Product product : allProducts) {
            if (product.getProductId().equals(Id))
                return product;
        }
        return null;
    }
}

