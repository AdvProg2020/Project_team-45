package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductFilters {
    private static final ProductFilters instance = new ProductFilters();
    private final ArrayList<String> companyNameList;
    private final ArrayList<String> sellerUsernameList;
    private final ArrayList<String> categoryName;
    private final HashMap<String, ArrayList<String>> featuresAndAmounts;
    private int minimumPrice;
    private int maximumPrice;
    private int minimumAverageScore;
    private int minimumSellCount;
    private int minimumSeen;

    private ProductFilters() {
        companyNameList = new ArrayList<>();
        sellerUsernameList = new ArrayList<>();
        categoryName = new ArrayList<>();
        featuresAndAmounts = new HashMap<>();
        minimumPrice = 0;
        maximumPrice = 0;
        minimumAverageScore = 0;
        minimumSellCount = 0;
        minimumSeen = 0;
    }

    public static ProductFilters getInstance() {
        return instance;
    }

//    public ArrayList<String> getCompanyName() {
//        return companyName;
//    }
//
//    public ArrayList<String> getSellerUsername() {
//        return sellerUsername;
//    }
//
//    public ArrayList<String> getCategoryName() {
//        return categoryName;
//    }
//
//    public HashMap<String, String> getFeaturesAndAmounts() {
//        return featuresAndAmounts;
//    }
//
//    public int getMinimumPrice() {
//        return minimumPrice;
//    }
//
//    public int getMaximumPrice() {
//        return maximumPrice;
//    }
//
//    public int getMinimumAverageScore() {
//        return minimumAverageScore;
//    }
//
//    public int getMinimumSellCount() {
//        return minimumSellCount;
//    }
//
//    public int getMinimumSeen() {
//        return minimumSeen;
//    }

    public LinkedHashMap<String, String> getCurrentFilters() {
        LinkedHashMap<String, String> currentFilters = new LinkedHashMap<>();
        for (String companyName : companyNameList) {
            currentFilters.put("companyName", companyName);
        }
        if (minimumPrice != 0)
            currentFilters.put("minimumPrice", String.valueOf(minimumPrice));
        if (maximumPrice != 0)
            currentFilters.put("maximumPrice", String.valueOf(maximumPrice));
        if (minimumAverageScore != 0)
            currentFilters.put("minimumAverageScore", String.valueOf(minimumAverageScore));
        if (minimumSellCount != 0)
            currentFilters.put("minimumSellCount", String.valueOf(minimumSellCount));
        if (minimumSeen != 0)
            currentFilters.put("minimumSeen", String.valueOf(minimumSeen));
        for (Map.Entry<String, ArrayList<String>> featureAndAmounts : featuresAndAmounts.entrySet()) {
            String feature = featureAndAmounts.getKey();
            for (String featureValue : featureAndAmounts.getValue()) {
                currentFilters.put(feature, featureValue);
            }
        }
        return currentFilters;
    }

    public void addCompanyName(String companyName) {
        companyNameList.add(companyName);
    }

    public boolean removeCompanyName(String companyName) {
        return companyNameList.remove(companyName);
    }

    public void clearCompanyNameList() {
        companyNameList.clear();
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public void setMaximumPrice(int maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public void setMinimumAverageScore(int minimumAverageScore) {
        this.minimumAverageScore = minimumAverageScore;
    }

    public void setMinimumSellCount(int minimumSellCount) {
        this.minimumSellCount = minimumSellCount;
    }

    public void setMinimumSeen(int minimumSeen) {
        this.minimumSeen = minimumSeen;
    }

    public void addFeatureAndAmount(String feature, String amount) {
        ArrayList<String> featureAndAmounts = featuresAndAmounts.get(feature);
        if (featureAndAmounts == null) {
            ArrayList<String> amounts = new ArrayList<>();
            amounts.add(amount);
            featuresAndAmounts.put(feature, amounts);
        } else {
            if (!featureAndAmounts.contains(amount))
                featureAndAmounts.add(amount);
        }
    }

    public boolean removeFeatureAndAmount(String feature, String amount) {
        ArrayList<String> amounts = featuresAndAmounts.get(feature);
        if (amount != null)
            return amounts.remove(amount);
        return false;
    }

    public boolean clearFeature(String feature) {
        return featuresAndAmounts.remove(feature) != null;
    }

    public boolean checkProductMatchFilter(Product product) {
        if (!companyNameList.isEmpty() && !companyNameList.contains(product.getCompany().getName()))
            return false;
        if (minimumPrice != 0 && product.getMinimumPrice() < minimumSeen)
            return false;
        if (maximumPrice != 0 && product.getMinimumPrice() < maximumPrice)
            return false;
        if (minimumAverageScore != 0 && product.getAverageScore() < minimumAverageScore)
            return false;
        if (minimumSellCount != 0 && product.getSellCount() < minimumSellCount)
            return false;
        if (minimumSeen != 0 && product.getSeen() < minimumSeen)
            return false;
        if (!featuresAndAmounts.isEmpty()) {
            LinkedHashMap<String, String> productFeatures = product.getCategoryFeatures();
            for (Map.Entry<String, ArrayList<String>> featureAndAmounts : featuresAndAmounts.entrySet()) {
                String productFeatureValue = productFeatures.get(featureAndAmounts.getKey());
                if (!featureAndAmounts.getValue().contains(productFeatureValue))
                    return false;
            }
        }
        return true;
    }

    public void clear() {
        companyNameList.clear();
        sellerUsernameList.clear();
        categoryName.clear();
        featuresAndAmounts.clear();
        minimumPrice = 0;
        maximumPrice = 0;
        minimumAverageScore = 0;
        minimumSellCount = 0;
        minimumSeen = 0;
    }
}