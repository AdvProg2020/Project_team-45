package controller;

import model.Product;
import model.user.Buyer;
import model.user.CartHolder;

import java.util.ArrayList;

public class CartController {
    private MainController mainController;

    public CartController(MainController mainController) {
        this.mainController = mainController;
    }

    public ArrayList<Product> getCartProducts(CartHolder cartHolder) {
        return null;
    }

    public void addProductToCart(CartHolder cartHolder, Product product, String sellerUsername) {
    }

    public Product getCartProductById(CartHolder cartHolder, String productId) {
        return null;
    }

    public void changeCartProductAmount(CartHolder cartHolder, String productId, String increaseOrDecrease) {
    }

    public void purchaseTheCart(Buyer buyer) {
    }

}
