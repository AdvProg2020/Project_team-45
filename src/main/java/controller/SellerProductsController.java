package controller;

import controller.managers.Printer;
import controller.userControllers.UserController;
import model.ProductSellInfo;
import model.user.Seller;

public class SellerProductsController implements Printer {
    private static SellerProductsController instance = new SellerProductsController();

    public static SellerProductsController getInstance() {
        return instance;
    }

    private SellerProductsController() {
    }

    @Override
    public String getAllInListAsString() {
        Seller activeSeller = (Seller) UserController.getActiveUser();
        StringBuilder listString = new StringBuilder("product name,price,category\n");
        for (ProductSellInfo productSellInfo : activeSeller.getAvailableProducts().values()) {
            listString.append(productSellInfo.getProduct().getProductId() + "," + productSellInfo.getPrice() + "," + productSellInfo.getProduct().getCategory().getName());
        }
        return listString.toString();
    }

    @Override
    public String getDetailStringById(String Id) {
        // TODO : not now
        return null;
    }

    @Override
    public Object getItemById(String Id) {
        return null;
    }
}
