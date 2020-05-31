package controller;

import controller.managers.Creator;
import controller.managers.Editor;
import controller.userControllers.UserController;
import model.Market;
import model.product.ProductSellInfo;
import model.request.RemoveProductRequest;
import model.user.Seller;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class SellerProductsController implements Creator, Editor {
    private static final SellerProductsController instance = new SellerProductsController();

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
            listString.append(productSellInfo.getProduct().getId() + "," + productSellInfo.getPrice() + "," + productSellInfo.getProduct().getCategory().getName());
        }
        return listString.toString();
    }

    @Override
    public String getDetailStringById(String Id) {
        return getItemById(Id).toString();
    }

    @Override
    public ProductSellInfo getItemById(String Id) {
        Seller activeSeller = (Seller) UserController.getActiveUser();
        return activeSeller.getAvailableProductSellInfoById(Id);
    }

    @Override
    public LinkedHashMap<String, InputValidator> getNecessaryFieldsToCreate() {
        LinkedHashMap<String, InputValidator> necessaryFeatures = new LinkedHashMap<>();
        return null;
    }

    @Override
    public LinkedHashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {

    }

    @Override
    public LinkedHashMap<String, InputValidator> getAvailableFieldsToEdit() {
        return null;
    }

    @Override
    public void editItem(Object editingObject, HashMap<String, String> changedFields) {

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

    @Override
    public boolean justRequests() {
        return true;
    }

    private Seller getActiveSeller(){
        return (Seller) UserController.getActiveUser();
    }
}
