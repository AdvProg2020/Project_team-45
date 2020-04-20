package model;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductFilters {
    private static ArrayList<String> availableFilters = new ArrayList<>();
    private ArrayList<String> companyName;
    private ArrayList<String> sellerUsername;
    private ArrayList<String> categoryName;
    private HashMap<String, String> featuresAndAmounts;
    private int minimumPrice;
    private int maximumPrice;
    private int minimumAverageScore;
    private int minimumSellCount;
    private int minimumSeen;

    public ProductFilters() {
        companyName = new ArrayList<>();
        sellerUsername = new ArrayList<>();
        categoryName = new ArrayList<>();
        featuresAndAmounts = new HashMap<>();
    }

    public static ArrayList<String> getAvailableFilters() {
        return availableFilters;
    }

    public ArrayList<String> getCompanyName() {
        return companyName;
    }

    public ArrayList<String> getSellerUsername() {
        return sellerUsername;
    }

    public ArrayList<String> getCategoryName() {
        return categoryName;
    }

    public HashMap<String, String> getFeaturesAndAmounts() {
        return featuresAndAmounts;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public int getMaximumPrice() {
        return maximumPrice;
    }

    public int getMinimumAverageScore() {
        return minimumAverageScore;
    }

    public int getMinimumSellCount() {
        return minimumSellCount;
    }

    public int getMinimumSeen() {
        return minimumSeen;
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
}