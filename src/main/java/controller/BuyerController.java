package controller;

import model.*;
import model.log.BuyLog;
import model.log.Log;
import model.user.Buyer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BuyerController {
    private static final BuyerController instance = new BuyerController();
    private final Market market;

    private final ArrayList<String> buyLogInformationToReceive;
    private Log log;

    private BuyerController() {
        market = Market.getInstance();

        this.buyLogInformationToReceive = new ArrayList<>();
        this.buyLogInformationToReceive.addAll(Arrays.asList("address", "phoneNumber"));
    }

    public static BuyerController getInstance() {
        return instance;
    }

    // cart managing menu

    public HashMap<String, Integer> getCartProductsList() {
        return ((Buyer) UserController.getActiveUser()).getCart().getProductsDisplay();
    }

    public void increaseCartProductById(String productId) {
        Cart cart = ((Buyer) UserController.getActiveUser()).getCart();
        cart.changeProductAmountById(productId, 1);
    }

    public void decreaseCartProductById(String productId) {
        Cart cart = ((Buyer) UserController.getActiveUser()).getCart();
        cart.changeProductAmountById(productId, -1);
    }

    public int getCartTotalPrice() {
        return ((Buyer) UserController.getActiveUser()).getCart().getTotalPrice();
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
        return market.isDiscountCodeValid(discountCode);
    }

    public void applyDiscountCode(String discountCode) {
        log.setAppliedDiscount(market.getCodedDiscountByCode(discountCode));
    }

    public boolean canPurchase() {
        return ((Buyer) UserController.getActiveUser()).getCart().getTotalPrice() <=
                ((Buyer) UserController.getActiveUser()).getBalance();
    }

    public void purchase() {
        ...
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
        ((Buyer) UserController.getActiveUser()).getPurchasedProducts().put(productId, rate);
        market.getProductById(productId).addRate(rate);
    }

    public ArrayList<String> getBuyerBuyLogs() {
        Buyer buyer = ((Buyer) UserController.getActiveUser());
        ArrayList<String> result = new ArrayList<>();
        for (BuyLog buyLog : buyer.getListOfBuyLogs()) {
            result.add(buyLog.getMainLog().getLogId() + buyLog.getMainLog().getDate() + buyLog.getMainLog().getFinalPrice());
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

}
