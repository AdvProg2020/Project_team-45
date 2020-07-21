package client.controller;

import client.network.ClientSocket;
import client.network.MethodStringer;
import com.google.gson.Gson;
import server.model.category.Category;
import server.model.category.FinalCategory;
import server.model.product.Product;
import server.model.product.ProductFilters;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class FilteringController {
    private static final FilteringController instance = new FilteringController();
    private final ProductFilters productFilters;
    private final ArrayList<String> generalFilters;

    private FilteringController() {
        productFilters = ProductFilters.getInstance();
        generalFilters = new ArrayList<>();
        generalFilters.add("productName");
        generalFilters.add("companyName");
        generalFilters.add("sellerUsername");
        generalFilters.add("minimumPrice");
        generalFilters.add("maximumPrice");
        generalFilters.add("minimumAverageScore");
        generalFilters.add("minimumSellCount");
        generalFilters.add("minimumSeen");
        generalFilters.add("available");
    }

    public static FilteringController getInstance() {
        return instance;
    }

    public ArrayList<String> getAvailableFilters() {
        ArrayList<String> availableFilters = new ArrayList<>(generalFilters);
        Category activeCategory = CategoryController.getInstance().getActiveCategory();
        if (activeCategory.getType().equals("FinalCategory")) {
            availableFilters.addAll(((FinalCategory) activeCategory).getSpecialFeatures());
        }
        return availableFilters;
    }

    public LinkedHashMap<String, String> getCurrentFilters() {
        return productFilters.getCurrentFilters();
    }

    public boolean addFilter(String type, String value) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(),
                    "addFilter", type, value);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public void removeFilter(String type, String value) {
        try {
            MethodStringer.sampleMethod(getClass(), "removeFilter", type, value);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public boolean removeFilter(String type) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(),
                    "removeFilter", type);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public ArrayList<Product> filteringProducts(ArrayList<Product> productsList) {
        ArrayList<Product> newProductsList = new ArrayList<>();
        for (Product product : productsList) {
            if (productFilters.checkProductMatchFilter(product)) {
                newProductsList.add(product);
            }
        }
        return newProductsList;
    }

    public void clearFilters() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            ClientSocket.getInstance().sendAction(action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        productFilters.clear();
    }
}