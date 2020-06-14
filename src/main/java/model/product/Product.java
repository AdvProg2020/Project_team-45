package model.product;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import model.category.FinalCategory;
import model.user.Seller;

import java.util.*;

public class Product extends IdRecognized implements Savable {
    private String name;
    private Company company;
    private Date productionDate;
    private String productStatus;
    private final HashMap<Seller, ProductSellInfo> sellersList;
    private ProductSellInfo defaultSellInfo;
    private int minimumPrice;   // minimum price should be updated
    private FinalCategory category;
    private LinkedHashMap<String, String> categoryFeatures;
    private String description;
    private float averageScore;
    private final ArrayList<Comment> allComments;
    private final ArrayList<Comment> approvedComments;
    private final ArrayList<Rate> rates;
    private int sellCount;
    private int seen;


    public Product(String name, FinalCategory category, String description) {
        this.id = "" + IdKeeper.getInstance().getProductsNewId();
        this.name = name;
        this.productionDate = new Date();
        this.category = category;
        this.categoryFeatures = new LinkedHashMap<>();
        this.description = description;
        this.allComments = new ArrayList<>();
        this.approvedComments = new ArrayList<>();
        this.sellersList = new HashMap<>();
        this.rates = new ArrayList<>();
    }

    public Product(String productId) {
        this.id = productId;
        this.categoryFeatures = new LinkedHashMap<>();
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

    @Override
    public String getId() {
        return id;
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
        return new ArrayList<>(sellersList.values());
    }

    public HashMap<Seller, ProductSellInfo> getSellersList() {
        return sellersList;
    }

    public ProductSellInfo getDefaultSellInfo() {
        updateMinimumPriceAndDefaultSellInfo();
        return defaultSellInfo;
    }

    public FinalCategory getCategory() {
        return category;
    }

    public LinkedHashMap<String, String> getCategoryFeatures() {
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
        updateAverageScoreAfterNewRate(rate.getScore());
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
        this.averageScore += ((float) (newRate - oldRate)) / rates.size();
    }

    public boolean isAvailable() {
        for (Map.Entry<Seller, ProductSellInfo> sellerInfo : sellersList.entrySet()) {
            if (sellerInfo.getValue().getStock() != 0)
                return true;
        }
        return false;
    }

    public boolean isInOff() {
        for (Map.Entry<Seller, ProductSellInfo> sellerInfo : sellersList.entrySet()) {
            if (sellerInfo.getValue().isInOff())
                return true;
        }
        return false;
    }

    private LinkedHashMap<String, String> getGeneralFeatures() {
        LinkedHashMap<String, String> generalFeatures = new LinkedHashMap<>();
        generalFeatures.put("name", this.name);
        generalFeatures.put("description", this.description);
        generalFeatures.put("price", String.valueOf(this.minimumPrice));
        generalFeatures.put("averageScore", String.valueOf(averageScore));
        return generalFeatures;
    }

    public LinkedHashMap<String, String> getDigestInformation() {
        LinkedHashMap<String, String> digestInformation = new LinkedHashMap<>();
        digestInformation.putAll(getGeneralFeatures());
        digestInformation.put("category", this.category.getName());
        for (Seller seller : sellersList.keySet()) {
            digestInformation.put("seller", seller.getUsername());
        }
        return digestInformation;
    }

    public LinkedHashMap<String, String> getAttributes() {
        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
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
        return "productId: " + id + '\n' +
                "name: " + name + '\n' +
                "price: " + minimumPrice;
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("name", name);
        result.put("company", company.getName());
        result.put("productionDate", (new Gson()).toJson(productionDate));
        result.put("productStatus", productStatus);
        ArrayList<String> sellers = new ArrayList<>();
        for (ProductSellInfo sellInfo : sellersList.values()) {
            sellers.add(sellInfo.getId());
        }
        result.put("sellersList", (new Gson()).toJson(sellers));
        result.put("defaultSellInfo", defaultSellInfo.getId());
        result.put("minimumPrice", "" + minimumPrice);
        result.put("category", category.getId());
        result.put("categoryFeatures", "" + categoryFeatures);
        result.put("description", description);
        result.put("averageScore", "" + averageScore);
        ArrayList<HashMap<String, String>> commentsMap = new ArrayList<>();
        for (Comment comment : allComments) {
            commentsMap.add(comment.convertToHashMap());
        }
        result.put("allComments", (new Gson()).toJson(commentsMap));
        ArrayList<String> ratesId = new ArrayList<>();
        for (Rate rate : rates) {
            ratesId.add(rate.getId());
        }
        result.put("rates", (new Gson()).toJson(ratesId));
        result.put("sellCount", "" + sellCount);
        result.put("seen", "" + seen);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        Market market = Market.getInstance();
        name = theMap.get("name");
        company = market.getCompanyByName(theMap.get("company"));
        productionDate = (new Gson()).fromJson(theMap.get("productionDate"), Date.class) ;
        productStatus = theMap.get("productStatus");
        ArrayList<String> sellInfosId = (new Gson()).fromJson(theMap.get("sellersList"), new TypeToken<ArrayList<String>>(){}.getType());
        for (String sellInfoId : sellInfosId) {
            ProductSellInfo productSellInfo = market.getProductSellInfoById(sellInfoId);
            sellersList.put(productSellInfo.getSeller(), productSellInfo);
        }
        defaultSellInfo = market.getProductSellInfoById(theMap.get("defaultSellInfo"));
        minimumPrice = Integer.parseInt(theMap.get("minimumPrice"));
        category = (FinalCategory) market.getCategoryById(theMap.get("category"));
        categoryFeatures = (new Gson()).fromJson(theMap.get("categoryFeatures"), new TypeToken<LinkedHashMap<String, String>>(){}.getType());
        description = theMap.get("description");
        averageScore = Float.parseFloat(theMap.get("averageScore"));
        ArrayList<HashMap<String, String>> allCommentMap = (new Gson()).fromJson(theMap.get("allComments"), new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType());
        for (HashMap<String, String> commentMap : allCommentMap) {
            Comment comment = new Comment();
            comment.setFieldsFromHashMap(commentMap);
            allComments.add(comment);
            if (comment.isApprovedComment())
                approvedComments.add(comment);
        }
        ArrayList<String> ratesId = (new Gson()).fromJson(theMap.get("rates"), new TypeToken<ArrayList<String>>(){}.getType());
        for (String rateId : ratesId) {
            rates.add(market.getRateById(rateId));
        }
        sellCount = Integer.parseInt(theMap.get("sellCount"));
        seen = Integer.parseInt(theMap.get("seen"));
    }

    public void setDefaultSellInfo(ProductSellInfo defaultSellInfo) {
        this.defaultSellInfo = defaultSellInfo;
    }

    //    enum ProductStatus {
//        UNDER_REVIEW_FOR_CREATE,
//        REVIEW_FOR_EDITING,
//        APPROVED;
//    }
}