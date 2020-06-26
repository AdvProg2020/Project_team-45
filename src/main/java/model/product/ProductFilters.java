package model.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductFilters {
    private static final ProductFilters instance = new ProductFilters();
    private String productName;
    private final ArrayList<String> companyNameList;
    private final ArrayList<String> sellerUsernameList;
    private final HashMap<String, ArrayList<String>> featuresAndAmounts;
    private int minimumPrice;
    private int maximumPrice;
    private int minimumAverageScore;
    private int minimumSellCount;
    private int minimumSeen;
    private boolean isAvailable;

    private ProductFilters() {
        companyNameList = new ArrayList<>();
        sellerUsernameList = new ArrayList<>();
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

    public LinkedHashMap<String, String> getCurrentFilters() {
        LinkedHashMap<String, String> currentFilters = new LinkedHashMap<>();
        if (productName != null) {
            currentFilters.put("productName", productName);
        }
        for (String companyName : companyNameList) {
            currentFilters.put("companyName", companyName);
        }
        for (String sellerUsername : sellerUsernameList) {
            currentFilters.put("sellerUsername", sellerUsername);
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
        if (isAvailable) {
            currentFilters.put("availability", "available");
        }
        for (Map.Entry<String, ArrayList<String>> featureAndAmounts : featuresAndAmounts.entrySet()) {
            String feature = featureAndAmounts.getKey();
            for (String featureValue : featureAndAmounts.getValue()) {
                currentFilters.put(feature, featureValue);
            }
        }
        return currentFilters;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public void addSellerUsername(String username) {
        sellerUsernameList.add(username);
    }

    public boolean removeSellerUsername(String username) {
        return sellerUsernameList.remove(username);
    }

    public void clearSellerUsernameList() {
        sellerUsernameList.clear();
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

    public void changeIsAvailable(){
        isAvailable = !isAvailable;
    }

    public void setIsAvailable(boolean input) {
        isAvailable = input;
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
        if (this.productName != null && !product.getName().contains(this.productName)) {
            return false;
        }
        if (!companyNameList.isEmpty() && !companyNameList.contains(product.getCompany().getName()))
            return false;
        if (!sellerUsernameList.isEmpty() && !checkExistSellerForProduct(product)) {
            return false;
        }
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
        if (isAvailable && !product.isAvailable()) {
            return false;
        }
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

    private boolean checkExistSellerForProduct(Product product) {
        for (String sellerUsername : sellerUsernameList) {
            if (product.getSellerInfoForProductByUsername(sellerUsername) != null) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        this.productName = null;
        this.companyNameList.clear();
        this.sellerUsernameList.clear();
        this.featuresAndAmounts.clear();
        this.minimumPrice = 0;
        this.maximumPrice = 0;
        this.minimumAverageScore = 0;
        this.minimumSellCount = 0;
        this.minimumSeen = 0;
        this.isAvailable = false;
    }
}