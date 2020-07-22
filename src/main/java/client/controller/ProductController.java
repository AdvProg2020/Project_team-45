package client.controller;

import client.network.MethodStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ProductController {

    // TODO : bagheri check all
    private static final ProductController instance = new ProductController();

    private ProductController() {
    }

    public static ProductController getInstance() {
        return instance;
    }

    public boolean setActiveProductBYProductIdForCategory(String productId) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(),
                    "setActiveProductBYProductIdForCategory", productId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public boolean setActiveProductBYProductIdForCart(String productId) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(),
                    "setActiveProductBYProductIdForCart", productId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public LinkedHashMap<String, String> getProductDigestInformation() {
        try {
            return (LinkedHashMap<String, String>) MethodStringer.sampleMethod(getClass(),
                    "getProductDigestInformation");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void addActiveProductToCart() {
        try {
            MethodStringer.sampleMethod(getClass(), "addActiveProductToCart");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void addComment(String title, String content) {
        try {
            MethodStringer.sampleMethod(getClass(),
                    "addComment", title, content);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public boolean selectSellerForActiveProduct(String sellerUsername) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(),
                    "selectSellerForActiveProduct", sellerUsername);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public void deleteItemById(String Id) {
        try {
            MethodStringer.sampleMethod(getClass(), "deleteItemById", Id);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in cart managing menu

    public void setActiveProductById(int productId) {
        try {
            MethodStringer.sampleMethod(getClass(),
            "setActiveProductById", productId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void setActiveProductById(String productId) {
        try {
            MethodStringer.sampleMethod(getClass(),
            "setActiveProductById", productId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in products managing menu

    public void setActiveProductSellInfo(String sellInfoId) {
        try {
            MethodStringer.sampleMethod(getClass(),
                    "setActiveProductSellInfo", sellInfoId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in add product panel

    public Boolean hasProductWithId(String text) {
        try {
            return (Boolean) MethodStringer.sampleMethod(getClass(),
                    "hasProductWithId", text);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> getActiveProductCommentsList() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(),
                    "getActiveProductCommentsList");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> getActiveProductSellInfos() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(),
                    "getActiveProductSellInfos");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void addActiveProductToCart(String productSellInfoId) {
        try {
            MethodStringer.sampleMethod(getClass(),
                    "addActiveProductToCart", productSellInfoId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public LinkedHashMap<String, String> getActiveProductFeatures() {
        try {
            return (LinkedHashMap<String, String>) MethodStringer.sampleMethod(getClass(),
                    "getActiveProductFeatures");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    // used in edit product panel

    public HashMap<String, String> getActiveSellInfo() {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(),
                    "getActiveSellInfo");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void setActiveProductBYProductIdForOff(String productId, String sellInfoId) {
        try {
            MethodStringer.sampleMethod(getClass(),
                    "setActiveProductBYProductIdForOff", productId, sellInfoId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, String>> getActiveProductSimilarProducts() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(),
                    "getActiveProductSimilarProducts");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public List<String> getAllProductsNamesList() {
        try {
            return (List<String>) MethodStringer.sampleMethod(getClass(),
                    "getAllProductsNamesList");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> getProductAndSellInfo(String sellInfoId) {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getProductAndSellInfo", sellInfoId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String getActiveProductImageAddress() {
        try {
            return (String) MethodStringer.sampleMethod(getClass(),
                    "getActiveProductImageAddress");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String getActiveProductVideoAddress() {
        try {
            return (String) MethodStringer.sampleMethod(getClass(),
                    "getActiveProductVideoAddress");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
