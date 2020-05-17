package model;

import model.category.FinalCategory;
import model.user.Seller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product {
    private static Integer newProductId = 1;
    private final String productId;
    private String name;
    private Company company;
    private String productStatus;
    private HashMap<Seller, ProductSellInfo> sellersList;
    private int minimumPrice;   // minimum price should be updated
    private FinalCategory category;
    private final HashMap<String, String> categoryFeatures;
    private String description;
    private float averageScore;
    private ArrayList<Comment> comments;
    private ArrayList<Rate> rates;
    private int sellCount;
    private int seen;

    public Product(String name, FinalCategory category, String description) {
        this.productId = newProductId.toString();
        newProductId++;
        this.name = name;
        this.category = category;
        this.categoryFeatures = new HashMap<>();
        this.description = description;
    }


    public ProductSellInfo getSellerInfoForProductByUsername(String sellerUsername) {
        for (Seller seller : sellersList.keySet()) {
            if (seller.getPersonalInfo().getUsername().equals(sellerUsername)) {
                return sellersList.get(seller);
            }
        }
        return null;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public String getName() {
        return name;
    }

    public String getProductId() {
        return productId;
    }

    public Company getCompany() {
        return company;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public ArrayList<ProductSellInfo> getSellInfosList() {
        return (ArrayList<ProductSellInfo>) sellersList.values();
    }

    public HashMap<Seller, ProductSellInfo> getSellersList() {
        return sellersList;
    }

    public FinalCategory getCategory() {
        return category;
    }

    public HashMap<String, String> getCategoryFeatures() {
        return categoryFeatures;
    }

    public String getDescription() {
        return description;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public int getSellCount() {
        return sellCount;
    }

    public int getSeen() {
        return seen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCategory(FinalCategory category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addSeller(ProductSellInfo productSellInfo) {

    }

    public boolean addCategoryFeatures(String feature, String measure) {
        return false;
    }

    public void addComment(Comment newComment) {

    }

    public void addRate(Rate rate) {

    }

    public void removeSeller(Seller seller) {
        sellersList.remove(seller);
    }

    public boolean removeCategoryFeatures(String feature, String measure) {
        return false;
    }

    public void increaseSeen() {

    }

    public void changeSellCount(int sellCount) {

    }

    public void requestForEdition() {

    }

    public void approveProduct() {

    }

    public void updateAverageScoreAfterNewRate() {

    }

    public void updateAverageScoreAfterEditingRate(int oldRate, int newRate) {

    }


    public boolean isInOff() {
        for (Map.Entry<Seller, ProductSellInfo> sellerInfo : sellersList.entrySet()) {
            if (sellerInfo.getValue().isInOff())
                return true;
        }
        return false;
    }

//    enum ProductStatus {
//        UNDER_REVIEW_FOR_CREATE,
//        REVIEW_FOR_EDITING,
//        APPROVED;
//    }
}