package controller.userControllers;

import controller.managers.Manager;
import model.Company;
import model.Market;
import model.Off;
import model.log.SellLog;
import model.product.Product;
import model.product.ProductSellInfo;
import model.request.*;
import model.user.PersonalInfo;
import model.user.Seller;
import model.user.User;

import java.util.HashMap;

public class SellerController extends UserController implements Manager {
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
        Market.getInstance().getAllRequests().add(new ProductEditionRequest(productId, ((Seller)UserController.getActiveUser())
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
                .add(new RemoveProductRequest(((Seller)UserController.getActiveUser()), productId));
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
        Market.getInstance().getAllRequests().add(new OffEditionRequest(((Seller)UserController.getActiveUser()), offId
                , fieldsAndValues));
    }


    public void createAddOffRequest(Off off) {
        Market.getInstance().getAllRequests().add(new AddOffRequest(((Seller)UserController.getActiveUser()), off));
    }

    // view balance panel

    public int getSellerBalance() {
        return ((Seller) UserController.getActiveUser()).getBalance();
    }


    public void createItem(HashMap<String, String> filledFeatures, String username) {
        filledFeatures.put("username", username);
        Seller newSeller = new Seller(new PersonalInfo(filledFeatures), new Company(filledFeatures));
        SellerRegisterRequest registerRequest  = new SellerRegisterRequest(newSeller);
        market.addRequest(registerRequest);
        market.addRequestedSeller(newSeller);
    }

    @Override
    public Seller getItemById(String Id) {
        User user = market.getUserByUsername(Id);
        if (user == null || !user.getRole().equals("seller"))
            return null;
        return (Seller) market.getUserByUsername(Id);
    }

    public Company getSellerCompany() {
        return ((Seller) UserController.getActiveUser()).getCompany();
    }

    public SellLog getCurrentSellLog() {
        return currentSellLog;
    }

    public void setCurrentSellLog(String id) {
        this.currentSellLog = ((Seller) getActiveUser()).getSellLogById(id);
    }
}