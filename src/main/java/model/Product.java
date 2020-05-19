package model;

import model.category.FinalCategory;
import model.user.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Product {
    private static Integer newProductId = 1;
    private final String productId;
    private String name;
    private Company company;
    private final Date productionDate;
    private String productStatus;
    private final HashMap<Seller, ProductSellInfo> sellersList;
    private ProductSellInfo defaultSellInfo;
    private int minimumPrice;   // minimum price should be updated
    private FinalCategory category;
    private final HashMap<String, String> categoryFeatures;
    private String description;
    private float averageScore;
    private final ArrayList<Comment> allComments;
    private final ArrayList<Comment> approvedComments;
    private final ArrayList<Rate> rates;
    private int sellCount;
    private int seen;


    public Product(String name, FinalCategory category, String description) {
        this.productId = newProductId.toString();
        newProductId++;
        this.name = name;
        this.productionDate = new Date();
        this.category = category;
        this.categoryFeatures = new HashMap<>();
        this.description = description;
        this.allComments = new ArrayList<>();
        this.approvedComments = new ArrayList<>();
        this.sellersList = new HashMap<>();
        this.rates = new ArrayList<>();
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
        updateMinimumPriceAndDefaultSellInfo();
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

    public Date getProductionDate() {
        return productionDate;
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

    public ProductSellInfo getDefaultSellInfo() {
        updateMinimumPriceAndDefaultSellInfo();
        System.out.println(defaultSellInfo);
        return defaultSellInfo;
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

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public ArrayList<Comment> getApprovedComments() {
        return approvedComments;
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

    public void addSellCount() {
        sellCount++;
    }

    public void addSeller(ProductSellInfo productSellInfo) {
        sellersList.put(productSellInfo.getSeller(), productSellInfo);
    }

    private void updateMinimumPriceAndDefaultSellInfo() {
        if (sellersList.size() == 0) {
            return;
        }
        ProductSellInfo sellInfo = ((ProductSellInfo) sellersList.values().toArray()[0]);
        int price = sellInfo.getFinalPrice();
        for (ProductSellInfo productSellInfo : sellersList.values()) {
            if (productSellInfo.getFinalPrice() < price) {
                sellInfo = productSellInfo;
                price = sellInfo.getFinalPrice();
            }
        }
        defaultSellInfo = sellInfo;
        minimumPrice = price;
    }

    public boolean addCategoryFeatures(String feature, String measure) {
        return false;
    }

    public void addComment(Comment newComment) {
        allComments.add(newComment);
    }

    public void addApprovedComment(Comment newComment) {
        approvedComments.add(newComment);
    }

    public void addRate(Rate rate) {
        rates.add(rate);
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

    public void updateAverageScoreAfterNewRate(int newRate) {
        int ratesSize = rates.size();
        this.averageScore = (this.averageScore * ratesSize + newRate) / ratesSize;
    }

    public void updateAverageScoreAfterEditingRate(int oldRate, int newRate) {
        this.averageScore += ((float)(newRate - oldRate)) / rates.size();
    }


    public boolean isInOff() {
        for (Map.Entry<Seller, ProductSellInfo> sellerInfo : sellersList.entrySet()) {
            if (sellerInfo.getValue().isInOff())
                return true;
        }
        return false;
    }


    private HashMap<String, String> getGeneralFeatures() {
        HashMap<String, String> generalFeatures = new HashMap<>();
        generalFeatures.put("name", this.name);
        generalFeatures.put("description", this.description);
        generalFeatures.put("price", String.valueOf(this.minimumPrice));
        generalFeatures.put("averageScore", String.valueOf(averageScore));
        return generalFeatures;
    }

    public HashMap<String, String> getDigestInformation() {
        HashMap<String, String> digestInformation = new HashMap<>();
        digestInformation.putAll(getGeneralFeatures());
        digestInformation.put("category", this.category.getName());
        for (Seller seller : sellersList.keySet()) {
            digestInformation.put("seller", seller.getUsername());
        }
        return digestInformation;
    }

    public HashMap<String,String> getAttributes(){
        HashMap<String, String> attributes = new HashMap<>();
        attributes.putAll(getGeneralFeatures());
        attributes.putAll(categoryFeatures);
        return attributes;
    }

    public ArrayList<String> getOffsInfo() {
        ArrayList<String> offsInfo = new ArrayList<>();
        // TODO bagheri
        return offsInfo;
    }

    @Override
    public String toString() {
        return "productId: " + productId + '\n' +
                "name: " + name + '\n' +
                "price: " + minimumPrice;
    }

    //    enum ProductStatus {
//        UNDER_REVIEW_FOR_CREATE,
//        REVIEW_FOR_EDITING,
//        APPROVED;
//    }
}