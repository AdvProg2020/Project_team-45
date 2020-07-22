package client.controller.userControllers;

import client.network.MethodStringer;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerController extends UserController {
    private static final SellerController instance = new SellerController();

    public static SellerController getInstance() {
        return instance;
    }

    // used in seller menu

    public HashMap<String, String> getSellerCompany() {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getSellerCompany");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public Integer getSellerBalance() {
        try {
            return (Integer) MethodStringer.sampleMethod(getClass(), "getSellerBalance");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    // used in sales history panel

    public ArrayList<HashMap<String, String>> getSellerSellLogs() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(), "getSellerSellLogs");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void setCurrentSellLog(int id) {
        try {
            MethodStringer.sampleMethod(getClass(), "setCurrentSellLog", id);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in sell log panel

    public HashMap<String, String> getCurrentSellLog() {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getCurrentSellLog");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    // used in products managing menu

    public ArrayList<HashMap<String, String>> getSellInfos() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(), "getSellInfos");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    // used in add product panel

    public void createAddProductRequest(String mode, HashMap<String, String> product, int price, int stock) {
        try {
            MethodStringer.sampleMethod(getClass(), "createAddProductRequest", mode, product, price, stock);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in edit product panel

    public void createProductEditionRequest(String productId, HashMap<String, String> fieldsAndValues) {
        try {
            MethodStringer.sampleMethod(getClass(), "createProductEditionRequest", productId, fieldsAndValues);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in remove product panel

    public ArrayList<HashMap<String, String>> getAvailableProducts() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(), "getAvailableProducts");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void createRemoveProductRequest(String productId) {
        try {
            MethodStringer.sampleMethod(getClass(), "createRemoveProductRequest", productId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in offs managing menu

    public ArrayList<HashMap<String, String>> getSellerOffs() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(), "getSellerOffs");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    // used in add off panel

    public void createAddOffRequest(ArrayList<String> products, HashMap<String, String> off) {
        try {
            MethodStringer.sampleMethod(getClass(), "createAddOffRequest", products, off);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in edit off panel

    public void createOffEditionRequest(HashMap<String, String> fieldsAndValues) {
        try {
            MethodStringer.sampleMethod(getClass(), "createOffEditionRequest", fieldsAndValues);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // i don't know!

    public void createItem(HashMap<String, String> filledFeatures, String username) {
        try {
            MethodStringer.sampleMethod(getClass(), "createItem", filledFeatures, username);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}