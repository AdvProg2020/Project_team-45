package controller;

import model.CodedDiscount;
import model.Product;
import model.log.BuyLog;
import model.log.SellLog;
import model.request.Request;
import model.user.User;

import java.util.ArrayList;

public class UserController {
    private MainController mainController;
    private boolean logined;

    public UserController(MainController mainController) {
        this.mainController = mainController;
        this.logined = false;
    }

    public boolean isLogined() {
        return logined;
    }

    public User createUser() {
        return null;
    }

    public void setFieldOfUserOrDownCast(User user, String field, String value) {}

    public User loginUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public void deleteUserByUsername(String username) {
    }

    public void acceptOrDeclineRequest(String requestId, String statusToSet) {
    }

    public ArrayList<SellLog> getSellerSalesHistory(User user) {
        return null;
    }

    public Product getSellerProductById(String productId) {
        return null;
    }

    public ArrayList<BuyLog> getBuyerOrders(User user) {
        return null;
    }

    public ArrayList<CodedDiscount> getBuyerCodedDiscounts(User user) {
        return null;
    }

    public void rateProduct(User user, Product product, int rate) {
    }

    public void makeCommentRequest(User user, Product product, String title, String content) {
    }

}
