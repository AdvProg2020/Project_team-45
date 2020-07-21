package client.controller;

import client.controller.userControllers.BuyerController;
import client.network.ClientSocket;
import client.network.MethodStringer;
import server.model.product.Product;
import server.model.product.ProductSellInfo;
import server.model.user.CartHolder;

import java.lang.reflect.Method;

public class CartController {
    private static final CartController instance = new CartController();

    public static CartController getInstance() {
        return instance;
    }

    private CartController() {
    }

    public void resetCart() {
        try {
            MethodStringer.sampleMethod(getClass(), "resetCart");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void addProductToCart(Product product, ProductSellInfo productSellInfo) {
        BuyerController.getInstance().updateBuyer();
        CartHolder buyer = BuyerController.getInstance().getBuyer();
        buyer.getCart().addProduct(product, productSellInfo);
    }

    public void changeCartProductAmount(CartHolder cartHolder, String productId, String increaseOrDecrease) {
    }

}
