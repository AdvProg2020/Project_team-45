package controller.userControllers;

import controller.InputValidator;
import controller.managers.Creator;
import model.Cart;
import model.CodedDiscount;
import model.ProductSellInfo;
import model.Rate;
import model.log.BuyLog;
import model.log.Log;
import model.user.Buyer;
import model.user.CartHolder;
import model.user.PersonalInfo;
import model.user.User;
import view.hatami.RegisterPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class BuyerController extends UserController implements Creator {
    private static final BuyerController instance = new BuyerController();

    private final ArrayList<String> buyLogInformationToReceive;
    private Log log;
    private CartHolder buyer;

    private BuyerController() {
        super();
        this.buyLogInformationToReceive = new ArrayList<>();
        this.buyLogInformationToReceive.addAll(Arrays.asList("address", "phoneNumber"));
    }

    public static BuyerController getInstance() {
        return instance;
    }

    private void updateBuyer() {
        if (UserController.isLoggedIn()) {
            buyer = ((Buyer) UserController.getActiveUser());
        } else {
            buyer = UserController.getAnonymousUser();
        }
    }

    // cart managing menu

    public HashMap<String, Integer> getCartProductsList() {
        updateBuyer();
        return buyer.getCart().getProductsDisplay();
    }

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

    public int getCartTotalPrice() {
        updateBuyer();
        return buyer.getCart().getTotalPrice();
    }

    // purchase panel

    public ArrayList<String> getBuyLogInformationToReceive() {
        return buyLogInformationToReceive;
    }

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
    }

    // orders managing menu

    public String getBuyerBuyLogDisplayById(String logId) {
        BuyLog buyLog = ((Buyer) UserController.getActiveUser()).getBuyLogById(logId);
        if (buyLog == null) {
            return null;
        }
        return "date = " + buyLog.getMainLog().getDate() + "\n" +
                "buyerUsername = '" + buyLog.getMainLog().getBuyerUsername() + '\'' + "\n" +
                "sellingProducts = " + buyLog.getMainLog().getSellingProducts() + "\n" +
                "appliedDiscount = " + buyLog.getMainLog().getAppliedDiscountPercentage() + "\n" +
                "finalPrice = " + buyLog.getMainLog().getFinalPrice() + "\n" +
                "address = '" + buyLog.getMainLog().getAddress() + '\'' + "\n" +
                "phoneNumber = '" + buyLog.getMainLog().getPhoneNumber() + '\'' + "\n" +
                "deliveryStatus = '" + buyLog.getMainLog().getDeliveryStatus() + '\'';
    }

    public boolean didBuyerBuyProduct(String productId) {
        return ((Buyer) UserController.getActiveUser()).didBuyProduct(productId);
    }

    public void rateProductById(String productId, int score) {
        Rate rate = new Rate(((Buyer) UserController.getActiveUser()), score, productId);
        if (!((Buyer) UserController.getActiveUser()).getPurchasedProducts().containsKey(productId)) {
            market.getProductById(productId).addRate(rate);
        }
        ((Buyer) UserController.getActiveUser()).getPurchasedProducts().put(productId, rate);
    }

    public ArrayList<String> getBuyerBuyLogs() {
        Buyer buyer = ((Buyer) UserController.getActiveUser());
        ArrayList<String> result = new ArrayList<>();
        for (BuyLog buyLog : buyer.getListOfBuyLogs()) {
            result.add(buyLog.getMainLog().getLogId() + " " + buyLog.getMainLog().getDate() + " " +
                    buyLog.getMainLog().getFinalPrice());
        }
        return result;
    }

    // view balance panel

    public int getBuyerBalance() {
        return ((Buyer) UserController.getActiveUser()).getBalance();
    }

    // view discount codes panel

    public ArrayList<String> getBuyerCodedDiscountsDisplay() {
        ArrayList<String> result = new ArrayList<>();
        Buyer buyer = ((Buyer) UserController.getActiveUser());
        for (CodedDiscount codedDiscount : buyer.getListOfCodedDiscounts()) {
            result.add("code = '" + codedDiscount.getCode() + '\'' +
                    " startDate = " + codedDiscount.getStartDate() +
                    " endDate = " + codedDiscount.getEndDate() +
                    " percentage = " + codedDiscount.getPercentage());
        }
        return result;
    }

    @Override
    public LinkedHashMap<String, InputValidator> getNecessaryFieldsToCreate() {
        return super.getNecessaryFieldsToCreate();
    }

    @Override
    public LinkedHashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {
        filledFeatures.put("username", RegisterPanel.getLastRegisterUsername());
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
}
