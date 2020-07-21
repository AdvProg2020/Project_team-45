package client.controller.userControllers;

import client.network.MethodStringer;
import server.model.Market;
import server.model.Off;
import server.model.log.SellLog;
import server.model.product.Product;
import server.model.product.ProductSellInfo;
import server.model.request.*;
import server.model.user.Seller;

import java.util.HashMap;

public class SellerController extends UserController {
    private static final SellerController instance = new SellerController();

    private SellLog currentSellLog;


    public static SellerController getInstance() {
        return instance;
    }


    // products managing menu

    public Product getSellerAvailableProductById(String productId) {
        return ((Seller) UserController.getActiveUser()).getAvailableProductById(productId);
    }


    public void createProductEditionRequest(String productId, HashMap<String, String> fieldsAndValues) {
        Market.getInstance().getAllRequests().add(new ProductEditionRequest(productId, ((Seller) UserController.getActiveUser())
                , fieldsAndValues));
    }

    // add product panel


    public void createAddProductRequest(String mode, Product product, int price, int stock) {
        product.setCompany(((Seller) getActiveUser()).getCompany());
        ProductSellInfo productSellInfo = new ProductSellInfo(product, (Seller) getActiveUser());
        productSellInfo.setPrice(price);
        productSellInfo.setStock(stock);
        if (mode.equals("new")) {
            product.setDefaultSellInfo(productSellInfo);
        }
        Market.getInstance().getAllProducts().add(product);
        Market.getInstance().getAllProductSellInfos().add(productSellInfo);
        Market.getInstance().getAllRequests().add(new AddProductRequest(mode, productSellInfo));
    }

    // remove product panel

    public void createRemoveProductRequest(String productId) {
        if (getSellerAvailableProductById(productId) == null) {
            return;
        }
        Market.getInstance().getAllRequests()
                .add(new RemoveProductRequest(((Seller) UserController.getActiveUser()), productId));
    }

    // offs managing menu

    private Off getAvailableOffById(String offId) {
        return ((Seller) UserController.getActiveUser()).getOffByOffId(offId);
    }


    public void createOffEditionRequest(String offId, HashMap<String, String> fieldsAndValues) {
        Off off = getAvailableOffById(offId);
        if (off == null) {
            return;
        }
        Market.getInstance().getAllRequests().add(new OffEditionRequest(((Seller) UserController.getActiveUser()), offId
                , fieldsAndValues));
    }


    public void createAddOffRequest(Off off) {
        Market.getInstance().getAllRequests().add(new AddOffRequest(((Seller) UserController.getActiveUser()), off));
    }

    // view balance panel

    public Integer getSellerBalance() {
        try {
            return (Integer) MethodStringer.sampleMethod(getClass(), "getSellerBalance");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }


    public void createItem(HashMap<String, String> filledFeatures, String username) {
        try {
            MethodStringer.sampleMethod(getClass(), "createItem", filledFeatures, username);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public HashMap<String, String> getSellerCompany() {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getSellerCompany");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public SellLog getCurrentSellLog() {
        return currentSellLog;
    }

    public void setCurrentSellLog(String id) {
        this.currentSellLog = ((Seller) getActiveUser()).getSellLogById(id);
    }
}