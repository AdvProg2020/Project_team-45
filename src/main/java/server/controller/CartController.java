package server.controller;

import server.controller.userControllers.BuyerController;
import server.model.product.Product;
import server.model.product.ProductSellInfo;
import server.model.user.Cart;
import server.model.user.CartHolder;

public class CartController {
    private static final CartController instance = new CartController();

    public static CartController getInstance() {
        return instance;
    }

    private CartController() {
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

    public void changeCartProductAmount(CartHolder cartHolder, String productId, String increaseOrDecrease) {
    }

}
