package controller.userControllers;

import controller.managers.Manager;
import model.CodedDiscount;
import model.Market;
import model.log.BuyLog;
import model.log.Log;
import model.product.ProductSellInfo;
import model.product.Rate;
import model.user.*;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyerController extends UserController implements Manager {
    private static final BuyerController instance = new BuyerController();

    private Log log;
    private CartHolder buyer;
    private BuyLog currentBuyLog;

    public static BuyerController getInstance() {
        return instance;
    }

    public CartHolder getBuyer() {
        BuyerController.getInstance().updateBuyer();
//        buyer = BuyerController.getInstance().getBuyer();
        return buyer;
    }

    public void updateBuyer() {
        if (UserController.isLoggedIn() && UserController.getActiveUser() != null && UserController.getActiveUser().getRole().equals("buyer")) {
            buyer = ((Buyer) UserController.getActiveUser());
        } else {
            buyer = UserController.getAnonymousUser();
        }
    }

    // cart managing menu

    public void increaseCartProductById(String productId) {
        updateBuyer();
        Cart cart = buyer.getCart();
        cart.changeProductAmountById(productId, 1);
    }

    public void decreaseCartProductById(String productId) {
        updateBuyer();
        Cart cart = buyer.getCart();
        cart.changeProductAmountById(productId, -1);
    }

    // purchase panel

    public void createNewLog(HashMap<String, String> fieldsAndValues) {
        ArrayList<ProductSellInfo> sellingProducts = ((Buyer) UserController.getActiveUser()).getCart().getProductSellInfos();
        log = new Log(sellingProducts, UserController.getActiveUser().getPersonalInfo().getUsername()
                , fieldsAndValues.get("address"), fieldsAndValues.get("phoneNumber"));
    }

    public boolean isDiscountCodeValid(String discountCode) {
        return ((Buyer) UserController.getActiveUser()).isDiscountCodeValid(discountCode);
    }

    public void applyDiscountCode(String discountCode) {
        CodedDiscount discount = ((Buyer) UserController.getActiveUser()).getDiscountByCode(discountCode);
        log.setAppliedDiscount(discount);
        ((Buyer) UserController.getActiveUser()).removeCodedDiscountFromList(discount);
    }

    public boolean canPurchase() {
        return ((Buyer) UserController.getActiveUser()).getCart().getTotalPrice() <=
                ((Buyer) UserController.getActiveUser()).getBalance();
    }

    public void purchase() {
        ((Buyer) UserController.getActiveUser()).purchase(log);
        Market.getInstance().getAllLogs().add(log);
    }

    // orders managing menu


    public void rateProductById(String productId, int score) {
        Rate rate = new Rate(((Buyer) UserController.getActiveUser()), score, productId);
        market.getProductById(productId).addRate(rate);
        ((Buyer) UserController.getActiveUser()).getPurchasedProducts().put(productId, rate);
    }

    // view balance panel

    public int getBuyerBalance() {
        return ((Buyer) UserController.getActiveUser()).getBalance();
    }

    // view discount codes panel


    public void createItem(HashMap<String, String> filledFeatures, String username) {
        filledFeatures.put("username", username);
        Buyer newBuyer = new Buyer(new PersonalInfo(filledFeatures));
        market.addUserToList(newBuyer);
    }

    @Override
    public Buyer getItemById(String Id) {
        User user = market.getUserByUsername(Id);
        if (user == null || !user.getRole().equals("buyer"))
            return null;
        return (Buyer) market.getUserByUsername(Id);
    }

    public BuyLog getCurrentBuyLog() {
        return currentBuyLog;
    }

    public void setCurrentBuyLog(BuyLog currentBuyLog) {
        this.currentBuyLog = currentBuyLog;
    }

    public Cart getCart() {
        updateBuyer();
        return buyer.getCart();
    }

    public Log getLog() {
        return log;
    }
}
