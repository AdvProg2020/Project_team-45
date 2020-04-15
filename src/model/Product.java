package model;

import model.user.Seller;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private String productId;
    private String name;
    private Company company;
    private ProductStatus condition;
    private HashMap<Seller, SellerInfoForProduct> sellersList;
    private Category category;
    private HashMap<String, String> categorySpecifications;
    private String description;
    private float averageScore;
    private ArrayList<Comment> comments;
    private ArrayList<Rate> rates;

//    public Product(String productId, String name, Company company, Category category, HashMap<String, String> categorySpecifications, String description) {
//        this.productId = productId;
//        this.name = name;
//        this.company = company;
//        this.category = category;
//        this.categorySpecifications = categorySpecifications;
//        this.description = description;
//    }

    public Product(String productId, String name, Company company, Category category, String description) {
        this.productId = productId;
        this.name = name;
        this.company = company;
        this.category = category;
        this.categorySpecifications = new HashMap<>();
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

    public ProductStatus getCondition() {
        return condition;
    }

    public HashMap<Seller, SellerInfoForProduct> getSellersList() {
        return sellersList;
    }

    public Category getCategory() {
        return category;
    }

    public HashMap<String, String> getCategorySpecifications() {
        return categorySpecifications;
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

    public ArrayList<Rate> getRates() {
        return rates;
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

    public void addSellersInformation(Seller seller, SellerInfoForProduct productInfoForSeller) {

    }

    public void addCategorySpecifications(HashMap<String, String> newCategorySpecifications) {

    }

    public boolean addCategorySpecifications(String , String ) {
        return false;
    }

    public void addComment(Comment newComment) {

    }

    public void addRate(Rate rate) {

    }

    public boolean removeSellers(Seller seller) {
        return false;
    }

    public boolean removeCategorySpecifications(String , String ) {
        return false;
    }

    public void requestEditing() {

    }

    public void approvingOff() {

    }

    public void updateAverageScore(int) {

    }

    public void updateAverageScore(int, int) {

    }

//    public String showDigestInformation() {
//
//    }
//
//    public String showAttributes() {
//
//    }

    enum ProductStatus {
        UNDER_REVIEW_FOR_CREATE,
        REVIEW_FOR_EDITING,
        APPROVED;
    }
}