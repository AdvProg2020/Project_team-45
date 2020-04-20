package model;

import model.user.Seller;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private String productId;
    private String name;
    private Company company;
    private ProductStatus productStatus;
    private ArrayList<SellerInfoForProduct> sellersList;
    private int minimumPrice;
    private Category category;
    private HashMap<String, String> categoryFeatures;
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
        this.categoryFeatures = new HashMap<>();
        this.description = description;
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

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public ArrayList<SellerInfoForProduct> getSellersList() {
        return sellersList;
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

    public void addSeller(SellerInfoForProduct sellerInfoForProduct) {

    }

    public boolean addCategoryFeatures(String feature, String measure) {
        return false;
    }

    public void addComment(Comment newComment) {

    }

    public void addRate(Rate rate) {

    }

    public boolean removeSeller(Seller seller) {
        return false;
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

    enum ProductStatus {
        UNDER_REVIEW_FOR_CREATE,
        REVIEW_FOR_EDITING,
        APPROVED;
    }
}