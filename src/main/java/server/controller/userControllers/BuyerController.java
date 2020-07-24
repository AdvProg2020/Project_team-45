package server.controller.userControllers;

import server.controller.managers.Manager;
import server.model.CodedDiscount;
import server.model.Market;
import server.model.log.BuyLog;
import server.model.log.Log;
import server.model.product.ProductSellInfo;
import server.model.product.Rate;
import server.model.user.*;
import server.newModel.nedaei.FileProduct;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class BuyerController extends UserController implements Manager {
    private static final BuyerController instance = new BuyerController();

    private Log log;
    private CartHolder buyer;
    private Log currentBuyLog;

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

    // used in buyer menu

    public Integer getBuyerBalance() {
        return ((Buyer) UserController.getActiveUser()).getBalance();
    }

    public Integer getBuyerAccountBalance() {
        return ((Buyer) UserController.getActiveUser()).getAccountBalance();
    }

    public void setBuyerBalance(int balance) {
        ((Buyer) UserController.getActiveUser()).setBalance(balance);
    }

    // used in cart managing menu

    public ArrayList<HashMap<String, String>> getCart() {
        updateBuyer();
        Cart cart = buyer.getCart();
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (ProductSellInfo sellInfo : cart.getSellInfos()) {
            HashMap<String, String> product = new HashMap<>();
            product.put("name", sellInfo.getProduct().getName());
            product.put("amount", "" + cart.getProductAmountById(sellInfo.getId()));
            product.put("id", sellInfo.getId());
            product.put("finalPrice", "" + sellInfo.getFinalPrice());
            result.add(product);
        }
        return result;
    }

    public Integer getCartTotalPrice() {
        updateBuyer();
        return buyer.getCart().getTotalPrice();
    }

    // todo: nedaei
//    public void increaseCartProductById(String productId) {
//        updateBuyer();
//        Cart cart = buyer.getCart();
//        cart.changeProductAmountById(productId, 1);
//    }
//
//    public void decreaseCartProductById(String productId) {
//        updateBuyer();
//        Cart cart = buyer.getCart();
//        cart.changeProductAmountById(productId, -1);
//    }

    // used in orders managing menu

    public ArrayList<HashMap<String, String>> getListOfBuyLogs() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        ArrayList<BuyLog> buyLogs = ((Buyer) UserController.getActiveUser()).getListOfBuyLogs();
        for (BuyLog buyLog : buyLogs) {
            HashMap<String, String> buyLogDisplay = new HashMap<>();
            buyLogDisplay.put("id", buyLog.getMainLog().getId());
            buyLogDisplay.put("finalPrice", "" + buyLog.getMainLog().getFinalPrice());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            buyLogDisplay.put("date", simpleDateFormat.format(buyLog.getMainLog().getDate()));
            result.add(buyLogDisplay);
        }
        return result;
    }

    public void setCurrentBuyLogById(String buyLogId) {
        currentBuyLog = Market.getInstance().getLogById(buyLogId);
    }

    // used in buy log panel

    public HashMap<String, String> getCurrentBuyLog() {
        HashMap<String, String> result = new HashMap<>();
        result.put("id", currentBuyLog.getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        result.put("date", simpleDateFormat.format(currentBuyLog.getDate()));
        result.put("buyerUsername", currentBuyLog.getBuyerUsername());
        result.put("discountPercentage", "" + currentBuyLog.getAppliedDiscountPercentage());
        result.put("finalPrice", "" + currentBuyLog.getFinalPrice());
        result.put("address", currentBuyLog.getAddress());
        result.put("phoneNumber", currentBuyLog.getPhoneNumber());
        return result;
    }

    public ArrayList<HashMap<String, String>> getBuyLogSellInfosById(String buyLogId) {
        BuyLog buyLog = ((Buyer) UserController.getActiveUser()).getBuyLogById(buyLogId);
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (ProductSellInfo productSellInfo : buyLog.getMainLog().getSellingProducts()) {
            HashMap<String, String> sellInfo = new HashMap<>();
            sellInfo.put("productName", productSellInfo.getProduct().getName());
            sellInfo.put("finalPrice", "" + productSellInfo.getFinalPrice());
            sellInfo.put("id", productSellInfo.getProduct().getId());
            sellInfo.put("isFile", "" + (productSellInfo.getProduct() instanceof FileProduct));
            result.add(sellInfo);
        }
        return result;
    }

    public void rateProductById(String productId, int score) {
        Rate rate = new Rate(((Buyer) UserController.getActiveUser()), score, productId);
        market.getProductById(productId).addRate(rate);
        ((Buyer) UserController.getActiveUser()).getPurchasedProducts().put(productId, rate);
    }

    // used in coded discounts panel

    public ArrayList<HashMap<String, String>> getListOfCodedDiscounts() {
        ArrayList<CodedDiscount> codedDiscounts = ((Buyer) UserController.getActiveUser()).getListOfCodedDiscounts();
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (CodedDiscount codedDiscount : codedDiscounts) {
            HashMap<String, String> discount = new HashMap<>();
            discount.put("code", codedDiscount.getCode());
            discount.put("percentage", "" + codedDiscount.getPercentage());
            discount.put("id", codedDiscount.getId());
            result.add(discount);
        }
        return result;
    }

    // used in receive info panel

    public void createNewLog(HashMap<String, String> fieldsAndValues) {
        ArrayList<ProductSellInfo> sellingProducts = ((Buyer) UserController.getActiveUser()).getCart().getProductSellInfos();
        log = new Log(sellingProducts, UserController.getActiveUser().getPersonalInfo().getUsername()
                , fieldsAndValues.get("address"), fieldsAndValues.get("phoneNumber"));
    }

    public Boolean isDiscountCodeValid(String discountCode) {
        return ((Buyer) UserController.getActiveUser()).isDiscountCodeValid(discountCode);
    }

    public void applyDiscountCode(String discountCode, String purchaseMode) {
        CodedDiscount discount = ((Buyer) UserController.getActiveUser()).getDiscountByCode(discountCode);
        log.setAppliedDiscount(discount);
        log.setPurchaseMode(purchaseMode);
        ((Buyer) UserController.getActiveUser()).removeCodedDiscountFromList(discount);
    }

    // used in payment panel

    public Boolean canPurchase() {
        return ((Buyer) UserController.getActiveUser()).getCart().getTotalPrice() <=
                ((Buyer) UserController.getActiveUser()).getBalance();
    }

    public Boolean canPurchaseByAccount() {
        return ((Buyer) UserController.getActiveUser()).getCart().getTotalPrice() <=
                ((Buyer) UserController.getActiveUser()).getAccountBalance();
    }

    public void purchase() {
        ((Buyer) UserController.getActiveUser()).purchase(log);
        Market.getInstance().getAllLogs().add(log);
    }

    public void setCurrentBuyLog() {
        setCurrentBuyLogById(log.getId());
    }

    // i don't know!

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
}
