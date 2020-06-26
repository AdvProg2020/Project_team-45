package controller;

import controller.userControllers.BuyerController;
import model.product.Product;
import model.product.ProductSellInfo;
import model.user.Buyer;
import model.user.Cart;
import model.user.CartHolder;

import java.util.ArrayList;

public class CartController {
    private static final CartController instance = new CartController();

    public static CartController getInstance() {
        return instance;
    }

    private CartController() {
    }

    public ArrayList<Product> getCartProducts(CartHolder cartHolder) {
        return null;
    }

    public void resetCart() {
        BuyerController.getInstance().updateBuyer();
        CartHolder buyer = BuyerController.getInstance().getBuyer();
        buyer.setCart(new Cart());
    }

    public void addProductToCart(Product product, ProductSellInfo productSellInfo) {
        BuyerController.getInstance().updateBuyer();
        CartHolder buyer = BuyerController.getInstance().getBuyer();
        buyer.getCart().addProduct(product, productSellInfo);
    }

    public Product getCartProductById(CartHolder cartHolder, String productId) {
        return null;
    }

    public void changeCartProductAmount(CartHolder cartHolder, String productId, String increaseOrDecrease) {
    }

    public void purchaseTheCart(Buyer buyer) {
    }

}
