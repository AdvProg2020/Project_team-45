package contreller;

import model.Product;
import model.ProductInfoForSeller;
import model.Seller;
import model.User;

import java.awt.print.Printable;
import java.util.Date;
import java.util.HashMap;

public class Controller_methods {
    public void createAccount(String type, String username, String password) {
    }

    public User checkLogin(String username, String password) {
        return null;
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public void deleteUser(String username) throws Exception {
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

    public HashMap<String, Printable> getAllOffsList() {
        return null;
    }

    public HashMap<String, Printable> getCartProductsList() {
        return null;
    }

    public HashMap<String, Printable> getCustomerOrdersList() {
        return null;
    }

    public void deleteProduct(String productId) throws Exception {
    }

    public void createDiscountCode(String code, Date startDate, Date endDate) {
    }

    public void editDiscountCode() {
    }

    public void removeDiscountCode() {
    }

    public void acceptDeclineRequest(String requestId) {
    }

    public void editCategory() {
    }

    public void addCategory() {
    }

    public void removeCategory() {
    }

    public void getSalesHistory(Seller seller) {
    }

    public void getProductBuyers(Product product, Seller seller) {
    }

    public void editProduct(ProductInfoForSeller productInfoForSeller) {
    }

    public void addProduct_Request() {
    }

    public void removeProduct_Request() {
    }

    public void changeProductAmountInCart() {
    }

    public void rateProduct() {
    }

}
