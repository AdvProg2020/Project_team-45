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
import server.model.user.Buyer;
import server.model.user.Cart;
import server.model.user.CartHolder;
import server.model.user.User;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class BuyerController extends UserController implements Manager {
    private static final BuyerController instance = new BuyerController();

    public static BuyerController getInstance() {
        return instance;
    }

    // used in buyer menu

    public Integer getBuyerBalance() {
        try {
            return (Integer) MethodStringer.sampleMethod(getClass(), "getBuyerBalance");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void setBuyerBalance(int balance) {
        try {
            MethodStringer.sampleMethod(getClass(), "setBuyerBalance", balance);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in cart managing menu

    public ArrayList<HashMap<String, String>> getCart() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(), "getCart");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public Integer getCartTotalPrice() {
        try {
            return (Integer) MethodStringer.sampleMethod(getClass(), "getCartTotalPrice");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    // used in orders managing menu

    public ArrayList<HashMap<String, String>> getListOfBuyLogs() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(), "getListOfBuyLogs");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void setCurrentBuyLogById(int buyLogId) {
        try {
            MethodStringer.sampleMethod(getClass(), "setCurrentBuyLogById", buyLogId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    // used in buy log panel
    public HashMap<String, String> getCurrentBuyLog() {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getCurrentBuyLog");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> getBuyLogSellInfosById(int buyLogId) {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(),
                    "getBuyLogSellInfosById", buyLogId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void rateProductById(String productId, int score) {
        try {
            MethodStringer.sampleMethod(getClass(), "rateProductById", productId, score);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in coded discounts panel

    public ArrayList<HashMap<String, String>> getListOfCodedDiscounts() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(), "getListOfCodedDiscounts");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    // used in receive info panel

    public void createNewLog(HashMap<String, String> fieldsAndValues) {
        try {
            MethodStringer.sampleMethod(getClass(), "createNewLog", fieldsAndValues);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public Boolean isDiscountCodeValid(String discountCode) {
        try {
            return (Boolean) MethodStringer.sampleMethod(getClass(), "isDiscountCodeValid", discountCode);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void applyDiscountCode(String discountCode) {
        try {
            MethodStringer.sampleMethod(getClass(), "applyDiscountCode", discountCode);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in payment panel

    public Boolean canPurchase() {
        try {
            return (Boolean) MethodStringer.sampleMethod(getClass(), "canPurchase");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void purchase() {
        try {
            MethodStringer.sampleMethod(getClass(), "purchase");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void setCurrentBuyLog() {
        try {
            MethodStringer.sampleMethod(getClass(), "setCurrentBuyLog");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // todo: nedaei, cart managing menu

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

    // i dont know!

    public void createItem(HashMap<String, String> filledFeatures, String username) {
        try {
            MethodStringer.sampleMethod(getClass(), "createItem", filledFeatures, username);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public Buyer getItemById(String Id) {
        // TODO : must be deleted
        User user = market.getUserByUsername(Id);
        if (user == null || !user.getRole().equals("buyer"))
            return null;
        return (Buyer) market.getUserByUsername(Id);
    }
}
