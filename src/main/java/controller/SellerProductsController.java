package controller;

import controller.managers.Editor;
import controller.userControllers.UserController;
import model.Market;
import model.product.ProductSellInfo;
import model.request.RemoveProductRequest;
import model.user.Seller;

public class SellerProductsController implements  Editor {
    private static final SellerProductsController instance = new SellerProductsController();

    public static SellerProductsController getInstance() {
        return instance;
    }

    private SellerProductsController() {
    }

    @Override
    public ProductSellInfo getItemById(String Id) {
        Seller activeSeller = (Seller) UserController.getActiveUser();
        return activeSeller.getAvailableProductSellInfoById(Id);
    }


    @Override
    public boolean deleteItemById(String Id) {
        ProductSellInfo productSellInfo = getItemById(Id);
        if (productSellInfo == null)
            return false;
        RemoveProductRequest removeProductRequest = new RemoveProductRequest(getActiveSeller(), productSellInfo.getProduct().getId());
        Market.getInstance().addRequest(removeProductRequest);
        return true;
    }

    private Seller getActiveSeller(){
        return (Seller) UserController.getActiveUser();
    }
}
