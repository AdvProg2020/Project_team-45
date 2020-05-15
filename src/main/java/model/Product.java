package model;

import model.category.Category;
import model.user.Seller;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private final String productId;
    private String name;
    private Company company;
    private String productStatus;
    private HashMap<Seller, ProductSellInfo> sellersList;
    private int minimumPrice;
    private Category category;
    private final HashMap<String, String> categoryFeatures;
    private String description;
    private float averageScore;
    private ArrayList<Comment> comments;
    private ArrayList<Rate> rates;
    private int sellCount;
    private int seen;

    public Product(String productId, String name, Company company, Category category, String description) {
        this.productId = productId;
        this.name = name;
        this.company = company;
        this.category = category;
        this.categoryFeatures = new HashMap<String, String>();
        this.description = description;
    }


    public ProductSellInfo getSellerInfoForProductByUsername(String sellerUsername) {
        return sellersList.get(sellerUsername);
        // minimum price should be updated
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

    public ArrayList<ProductSellInfo> getSellersList() {
        return (ArrayList<ProductSellInfo>) sellersList.values();
    }

    public Category getCategory() {
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

    public void setCategory(Category category) {
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

//    enum ProductStatus {
//        UNDER_REVIEW_FOR_CREATE,
//        REVIEW_FOR_EDITING,
//        APPROVED;
//    }
}