package client.controller;

import server.model.category.Category;
import server.model.category.FinalCategory;
import server.model.product.Product;
import server.model.product.ProductFilters;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
        Category activeCategory = CategoryController.getInstance().getActiveCategory();
        if (type.equals("productName")) {
            productFilters.setProductName(value);
        } else if (type.equals("companyName")) {
            productFilters.addCompanyName(value);
        } else if (type.equals("sellerUsername")) {
            productFilters.addSellerUsername(value);
        } else if (type.equals("minimumPrice")) {
            productFilters.setMinimumPrice(Integer.parseInt(value));
        } else if (type.equals("maximumPrice")) {
            productFilters.setMaximumPrice(Integer.parseInt(value));
        } else if (type.equals("minimumAverageScore")) {
            productFilters.setMinimumAverageScore(Integer.parseInt(value));
        } else if (type.equals("minimumSellCount")) {
            productFilters.setMinimumSellCount(Integer.parseInt(value));
        } else if (type.equals("minimumSeen")) {
            productFilters.setMinimumSeen(Integer.parseInt(value));
        } else if (type.equals("available")) {
            productFilters.setIsAvailable(true);
        } else if (activeCategory.getType().equals("FinalCategory")) {
            ArrayList<String> specialFeatures = ((FinalCategory) activeCategory).getSpecialFeatures();
            if (specialFeatures.contains(type)) {
                productFilters.addFeatureAndAmount(type, value);
            }
        } else {
            return false;
        }
        return true;
    }

    public void removeFilter(String type, String value) {
        Category activeCategory = CategoryController.getInstance().getActiveCategory();
        if (type.equals("companyName")) {
            productFilters.removeCompanyName(value);
        } else if (type.equals("sellerUsername")) {
            productFilters.removeSellerUsername(value);
        } else if (activeCategory.getType().equals("FinalCategory")) {
            ArrayList<String> specialFeatures = ((FinalCategory) activeCategory).getSpecialFeatures();
            if (specialFeatures.contains(type)) {
                productFilters.removeFeatureAndAmount(type, value);
            }
        } else {

        }
    }

    public boolean removeFilter(String type) {
        Category activeCategory = CategoryController.getInstance().getActiveCategory();
        if (type.equals("productName")) {
            productFilters.setProductName(null);
        } else if (type.equals("companyName")) {
            productFilters.clearCompanyNameList();
        } else if (type.equals("sellerUsername")) {
            productFilters.clearSellerUsernameList();
        } else if (type.equals("minimumPrice")) {
            productFilters.setMinimumPrice(0);
        } else if (type.equals("maximumPrice")) {
            productFilters.setMaximumPrice(0);
        } else if (type.equals("minimumAverageScore")) {
            productFilters.setMinimumAverageScore(0);
        } else if (type.equals("minimumSellCount")) {
            productFilters.setMinimumSellCount(0);
        } else if (type.equals("minimumSeen")) {
            productFilters.setMinimumSeen(0);
        } else if (type.equals("available")) {
            productFilters.setIsAvailable(false);
        } else if (activeCategory.getType().equals("FinalCategory")) {
            ArrayList<String> specialFeatures = ((FinalCategory) activeCategory).getSpecialFeatures();
            if (specialFeatures.contains(type)) {
                productFilters.clearFeature(type);
            }
        } else {
            return false;
        }
        return true;
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
        productFilters.clear();
    }
}