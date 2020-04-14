package contreller;

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

}
