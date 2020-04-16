package controller;

import model.Cart;
import model.Product;
import model.user.User;

import java.util.ArrayList;

public class CartController {
    private MainController mainController;

    public CartController(MainController mainController) {
        this.mainController = mainController;
    }

    public ArrayList<Product> getCartProducts(User user) {
        return null;
    }

    public void addProductToCart(User user, Product product, String sellerUsername) {
    }

    public Product getCartProductById(User user, String productId) {
        return null;
    }

    public void changeCartProductAmount(User user, String productId, String increaseOrDecrease) {
    }

    public void purchaseTheCart(User user) {
    }
}
