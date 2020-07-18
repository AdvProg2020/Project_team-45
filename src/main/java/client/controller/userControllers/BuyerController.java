package client.controller.userControllers;

import client.controller.managers.Manager;
import client.network.ClientSocket;
import client.network.MethodStringer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.model.CodedDiscount;
import server.model.Market;
import server.model.log.BuyLog;
import server.model.log.Log;
import server.model.product.ProductSellInfo;
import server.model.product.Rate;
import server.model.user.*;

import java.lang.reflect.Method;
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

    public ArrayList<HashMap<String, String>> getListOfBuyLogs() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getCartTotalPrice() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, Integer.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
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
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, productId, score);
            String returnJson = ClientSocket.getInstance().sendAction(action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // view balance panel

    public Integer getBuyerBalance() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, Integer.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
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

    public HashMap<String, String> getCurrentBuyLog() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<HashMap<String, String>>(){}.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setCurrentBuyLogById(int buyLogId) {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, buyLogId);
            String returnJson = ClientSocket.getInstance().sendAction(action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, String>> getCart() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getSellInfoTotalPrice(int sellInfoId) {
        updateBuyer();
        return buyer.getCart().getProductAmountById("" + sellInfoId)*
                buyer.getCart().getSellInfoById(sellInfoId).getFinalPrice();
    }

    public Log getLog() {
        return log;
    }

    public void setBuyerBalance(int balance) {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, balance);
            String returnJson = ClientSocket.getInstance().sendAction(action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, String>> getBuyLogSellInfosById(int buyLogId) {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, buyLogId);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
