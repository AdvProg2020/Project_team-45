package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private String productId;
    private String name;
    private Company company;
    private ProductStatus condition;
    private ArrayList<ProductInfoForSeller> sellersInformation;
    private Category category;
    private HashMap<String, String> categorySpecifications;
    private String description;
    private float averageScore;
    private ArrayList<Comment> comments;
    private ArrayList<Rate> rates;

    public Product(String productId, String name, Company company, Category category, HashMap<String, String> categorySpecifications, String description) {
        this.productId = productId;
        this.name = name;
        this.company = company;
        this.category = category;
        this.categorySpecifications = categorySpecifications;
        this.description = description;
    }

    public String getName() {
        return name;
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

    public void setCategorySpecifications(HashMap<String, String> categorySpecifications) {
        this.categorySpecifications = categorySpecifications;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorySpecifications(String, String) {

    }

    public void addSellersInformation(ProductInfoForSeller) {

    }

    public void addComment(Comment) {

    }

    public void addRate(Rate) {

    }

    public boolean removeSellersInformation(ProductInfoForSeller) {

    }

    public void requestEditing() {

    }

    public void approvingOff() {

    }

    public void updateAverageScore(int) {

    }

    public void updateAverageScore(int, int) {

    }

    public String showDigestInformation() {

    }

    public String showAttributes() {

    }

    enum ProductStatus {

    }
}