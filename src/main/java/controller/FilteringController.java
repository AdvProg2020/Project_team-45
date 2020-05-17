package controller;

import model.Product;
import model.ProductFilters;
import model.category.Category;
import model.category.FinalCategory;

import java.util.ArrayList;
import java.util.HashMap;

public class FilteringController {
    private static final FilteringController instance = new FilteringController();
    private final ProductFilters productFilters;
    private final ArrayList<String> generalFilters;

    private FilteringController() {
        productFilters = ProductFilters.getInstance();
        generalFilters = new ArrayList<>();
        generalFilters.add("companyName");
        generalFilters.add("minimumPrice");
        generalFilters.add("maximumPrice");
        generalFilters.add("minimumAverageScore");
        generalFilters.add("minimumSellCount");
        generalFilters.add("minimumSeen");
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

    public HashMap<String, String> getCurrentFilters() {
        return productFilters.getCurrentFilters();
    }

    public boolean addFilter(String type, String value) {
        Category activeCategory = CategoryController.getInstance().getActiveCategory();
        if (type.equals("companyName")) {
            productFilters.addCompanyName(value);
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
        if (type.equals("companyName")) {
            productFilters.clearCompanyNameList();
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