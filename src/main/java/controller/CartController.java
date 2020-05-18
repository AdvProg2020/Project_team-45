package controller;

import controller.userControllers.BuyerController;
import model.Product;
import model.ProductSellInfo;
import model.user.Buyer;
import model.user.CartHolder;

import java.util.ArrayList;

public class CartController {
    private static CartController instance = new CartController();

    public static CartController getInstance() {
        return instance;
    }

    private CartController() {
    }

    public ArrayList<Product> getCartProducts(CartHolder cartHolder) {
        return null;
    }

    public void addProductToCart(Product product, String sellerUsername) {
        BuyerController.getInstance().updateBuyer();
        CartHolder buyer = BuyerController.getInstance().getBuyer();
        ProductSellInfo productSellInfo = product.getSellerInfoForProductByUsername(sellerUsername);
        if (productSellInfo == null) {
            return;
        }
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
