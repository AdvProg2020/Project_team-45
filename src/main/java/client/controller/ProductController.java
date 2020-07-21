package client.controller;

import client.controller.managers.Deleter;
import client.network.ClientSocket;
import client.network.MethodStringer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.controller.userControllers.BuyerController;
import server.model.Market;
import server.model.product.Product;
import server.model.product.ProductSellInfo;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ProductController implements Deleter {

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
        CartController.getInstance().addProductToCart(activeProduct, activeProductSellInfo);
    }

    public boolean selectSellerForActiveProduct(String sellerUsername) {
        ProductSellInfo sellInfo = activeProduct.getSellerInfoForProductByUsername(sellerUsername);
        if (sellInfo == null)
            return false;
        activeProductSellInfo = sellInfo;
        return true;
    }

    public void addComment(String title, String content) {
        try {
            MethodStringer.sampleMethod(getClass(),
                    "addComment", title, content);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public boolean deleteItemById(String Id) {
        Product product = getItemById(Id);
        if (product == null)
            return false;
        removeProduct(getItemById(Id));
        return true;
    }

    public void removeProduct(Product product) {
        removeProductFromSellersList(product);
        removeProductFromCategory(product);
        market.removeProductFromAllList(product);
    }

    private void removeProductFromCategory(Product product) {
        product.getCategory().removeProduct(product);
    }

    private void removeProductFromSellersList(Product product) {
        for (ProductSellInfo sellInfo : product.getSellInfosList()) {
            sellInfo.removeProduct();
        }
    }

    @Override
    public Product getItemById(String Id) {
        return market.getProductById(Id);
    }

    public ProductSellInfo getActiveProductSellInfo() {
        return activeProductSellInfo;
    }

    public void setActiveProductSellInfo(ProductSellInfo activeProductSellInfo) {
        this.activeProductSellInfo = activeProductSellInfo;
    }

    public Product getActiveProduct() {
        return activeProduct;
    }

    public void setActiveProductById(int productId) {
        this.activeProduct = Market.getInstance().getProductById("" + productId);
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

    public String getActiveProductImageAddress() {
        return activeProduct.getImageAddress();
    }

    public void setActiveProductById(String productId) {
        activeProduct = market.getProductById(productId);
    }


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
        return market.getAllProducts().stream().map(product -> product.getId() + ":" + product.getName()).collect(Collectors.toList());
    }
}
