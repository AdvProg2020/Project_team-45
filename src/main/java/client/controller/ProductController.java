package client.controller;

import client.controller.managers.Deleter;
import client.controller.userControllers.BuyerController;
import client.controller.userControllers.UserController;
import client.network.ClientSocket;
import client.network.MethodStringer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.model.Comment;
import server.model.Market;
import server.model.product.Product;
import server.model.product.ProductSellInfo;
import server.model.request.CommentRequest;
import server.model.user.Buyer;
import server.model.user.User;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductController implements Deleter {

    // TODO : bagheri check all
    private static final ProductController instance = new ProductController();
    private final Market market;
    private Product activeProduct;
    private ProductSellInfo activeProductSellInfo;

    private ProductController() {
        this.market = Market.getInstance();
    }

    public static ProductController getInstance() {
        return instance;
    }


    public boolean setActiveProductBYProductIdForCategory(String productId) {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, productId);
            String returnJson = ClientSocket.sendAction(action);
            return (new Gson()).fromJson(returnJson, boolean.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setActiveProductBYProductIdForCart(String productId) {
        ArrayList<Product> productsList = BuyerController.getInstance().getBuyer().getCart().getCartProducts();
        return setActiveProductByListAndId(productsList, productId);
    }

    private boolean setActiveProductByListAndId(ArrayList<Product> productsList, String productId) {
        for (Product product : productsList) {
            if (product.getId().equals(productId)) {
                activeProduct = product;
                activeProduct.increaseSeen();
                activeProductSellInfo = activeProduct.getDefaultSellInfo();
                return true;
            }
        }
        return false;
    }

    public LinkedHashMap<String, String> getProductDigestInformation() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, title, content);
            ClientSocket.sendAction(action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

    //bagheri
    public ArrayList<HashMap<String, String>> getActiveProductCommentsList() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> getActiveProductSellInfos() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addActiveProductToCart(String productSellInfoId) {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, productSellInfoId);
            ClientSocket.sendAction(action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        CartController.getInstance().addProductToCart(activeProduct, market.getProductSellInfoById(productSellInfoId));
    }

    public LinkedHashMap<String, String> getActiveProductFeatures() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setActiveProductBYProductIdForOff(String productId, String sellInfoId) {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, productId, sellInfoId);
            ClientSocket.sendAction(action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, String>> getActiveProductSimilarProducts() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getAllProductsNamesList() {
        return market.getAllProducts().stream().map(product -> product.getId() + ":" + product.getName()).collect(Collectors.toList());
    }
    //bahgeri
}
