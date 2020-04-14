package contreller;

import model.Category;
import model.Market;
import model.Product;
import model.User;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Controller {
    private Market market;

    public Controller() {
        this.market = Market.getInstance();
    }

    public void addUser(String username, String firstname, String lastname, String emailAddress, String phoneNumber, String password, String role) {
    }

    public User loginUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public void deleteUserByUsername(String username) throws Exception {
    }

    public HashMap<String, Printable> getAllUsersList() {
        return null;
    }

    public HashMap<String, Printable> getAllProductsList() {
        return null;
    }

    public HashMap<String, Printable> getAllDiscountCodesList() {
        return null;
    }

    public HashMap<String, Printable> getAllRequestsList() {
        return null;
    }

    public HashMap<String, Printable> getAllCategoriesList() {
        return null;
    }

    public void deleteProduct(String productId) throws Exception {
    }

    public void addCodedDiscount(String discountCode,int discountPercentage, Date startDate, Date endDate) {
    }

    public void editCodedDiscount(String discountCode,int discountPercentage, Date startDate, Date endDate) {
    }

    public void removeCodedDiscount(String discountCode) {
    }

    public void acceptDeclineRequest(String requestId) {
    }

    public void editCategory(String name, ArrayList<String> specialFeatures, ArrayList<Category> subCategory, ArrayList<Product> productList) {
    }

    public void addCategory(String name, Category parent, ArrayList<String> specialFeatures, ArrayList<Category> subCategory, ArrayList<Product> productList) {
    }

    public void removeCategory(String name) {
    }

}
