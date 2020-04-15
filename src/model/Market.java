package model;

import model.user.User;

import java.util.ArrayList;
import java.util.Date;

public class Market {
    private static Market marketInstance;
    private ArrayList<User> listOfUsers;
    private ArrayList<CodedDiscount> listOfCodedDiscount;
    private ArrayList<Category> listOfCategories;
    private ArrayList<Product> listOfProducts;
    private boolean hasAdmin;

    private Market() {

    }

    public static Market getInstance() {

    }

    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    public ArrayList<Category> getListOfCategories() {
        return listOfCategories;
    }

    public User getUserByUsername(String username) {

    }

    public boolean getHasAdmin() {

    }

    public void setHasAdmin(boolean hasAdmin) {

    }

    public void addUser(String username, String firstName, String lastName,
                        String emailAddress, String phoneNumber, String password, String role) {

    }

    public Product getProductByProductId(String productId) {

    }

    public ArrayList<CodedDiscount> getListOfCodedDiscount() {
        return listOfCodedDiscount;
    }

    public CodedDiscount getCodedDiscountByDiscountCode(String discountCode) {

    }

    public Category getCategoryByName(String name) {

    }

    public ArrayList<Product> filterProducts(ProductFilters productFilters) {

    }

    public ArrayList<Product> sortProducts(String attribute) {

    }

    public ArrayList<Off> filterOffs(OffFilters offFilters) {

    }

    public ArrayList<Off> sortOffs(String attribute) {

    }

    public void deleteUserByUsername(String username) {

    }

    public void removeProductByProductId(String productId) {

    }

    public void editCodedDiscountByDiscountCode(String discountCode, Date startDate, Date endDate) {

    }

    public void removeCodedDiscountByDiscountCode(String discountCode) {

    }

    public void editCategoryByName(String name, CategoryInfo ... ?) {

    }

    public void addCategory(CategoryInfo ... ?) {

    }

    public void removeCategoryByName(String name) {

    }
}

