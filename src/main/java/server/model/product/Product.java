package server.model.product;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.model.*;
import server.model.category.FinalCategory;
import server.model.user.Seller;

import java.text.SimpleDateFormat;
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
    private String imageAddress;
    private String videoAddress;

    public Product(String name, FinalCategory category, String description, String imageAddress, String videoAddress) {
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

//        this.videoAddress = "/photos/" + videoAddress.substring(videoAddress.lastIndexOf("\\")+1);
        this.videoAddress = videoAddress;

        if (imageAddress == null) {
            this.imageAddress = "/poker.png";
        }
        else
            this.imageAddress = "/photos/" + imageAddress.substring(imageAddress.lastIndexOf("\\")+1);

    }

    public Product(String productId) {
        this.id = productId;
        this.categoryFeatures = new LinkedHashMap<>();
        this.allComments = new ArrayList<>();
        this.approvedComments = new ArrayList<>();
        this.sellersList = new HashMap<>();
        this.rates = new ArrayList<>();
    }


    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public HashMap<Seller, ProductSellInfo> getSellersList() {
        return sellersList;
    }

    public ProductSellInfo getDefaultSellInfo() {
        updateMinimumPriceAndDefaultSellInfo();
        return defaultSellInfo;
    }

    public int getMinimumPrice() {
        updateMinimumPriceAndDefaultSellInfo();
        return minimumPrice;
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

    public String getVideoAddress() {
        return videoAddress;
    }

    public ArrayList<ProductSellInfo> getSellInfosList() {
        return new ArrayList<>(sellersList.values());
    }

    public ProductSellInfo getSellerInfoForProductByUsername(String sellerUsername) {
        for (Seller seller : sellersList.keySet()) {
            if (seller.getPersonalInfo().getUsername().equals(sellerUsername)) {
                return sellersList.get(seller);
            }
        }
        return null;
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
        int price = 0;
        for (ProductSellInfo productSellInfo : sellersList.values()) {
            if (productSellInfo.getFinalPrice() < price || price == 0 ) {
                if(!productSellInfo.isInAuction()) {
                    sellInfo = productSellInfo;
                    price = sellInfo.getFinalPrice();
                }
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
        seen++;
    }

    public void changeSellCount(int sellCount) {
        this.sellCount += sellCount;
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
            if (sellerInfo.getValue().getStock() != 0  && !sellerInfo.getValue().isInAuction())
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
        generalFeatures.put("companyName", this.company.getName());
        generalFeatures.put("description", this.description);
        generalFeatures.put("price", String.valueOf(getMinimumPrice()));
        generalFeatures.put("averageScore", String.valueOf(averageScore));
        return generalFeatures;
    }

    public LinkedHashMap<String, String> getDigestInformation() {
        LinkedHashMap<String, String> digestInformation = new LinkedHashMap<>();
        digestInformation.putAll(getGeneralFeatures());
        digestInformation.put("category", this.category.getName());
//        for (Seller seller : sellersList.keySet()) {
//            digestInformation.put("seller", seller.getUsername());
//        }
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
                "price: " + getMinimumPrice();
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
        result.put("imageAddress", imageAddress);
        result.put("videoAddress", videoAddress);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        Market market = Market.getInstance();
        name = theMap.get("name");
        company = market.getCompanyByName(theMap.get("company"));
        productionDate = (new Gson()).fromJson(theMap.get("productionDate"), Date.class);
        productStatus = theMap.get("productStatus");
        ArrayList<String> sellInfosId = (new Gson()).fromJson(theMap.get("sellersList"), new TypeToken<ArrayList<String>>() {
        }.getType());
        for (String sellInfoId : sellInfosId) {
            ProductSellInfo productSellInfo = market.getProductSellInfoById(sellInfoId);
            sellersList.put(productSellInfo.getSeller(), productSellInfo);
        }
        defaultSellInfo = market.getProductSellInfoById(theMap.get("defaultSellInfo"));
        minimumPrice = Integer.parseInt(theMap.get("minimumPrice"));
        category = (FinalCategory) market.getCategoryById(theMap.get("category"));
        categoryFeatures = (new Gson()).fromJson(theMap.get("categoryFeatures"), new TypeToken<LinkedHashMap<String, String>>() {
        }.getType());
        description = theMap.get("description");
        averageScore = Float.parseFloat(theMap.get("averageScore"));
        ArrayList<HashMap<String, String>> allCommentMap = (new Gson()).fromJson(theMap.get("allComments"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
        for (HashMap<String, String> commentMap : allCommentMap) {
            Comment comment = new Comment();
            comment.setFieldsFromHashMap(commentMap);
            allComments.add(comment);
            if (comment.isApprovedComment())
                approvedComments.add(comment);
        }
        ArrayList<String> ratesId = (new Gson()).fromJson(theMap.get("rates"), new TypeToken<ArrayList<String>>() {
        }.getType());
        for (String rateId : ratesId) {
            rates.add(market.getRateById(rateId));
        }
        sellCount = Integer.parseInt(theMap.get("sellCount"));
        seen = Integer.parseInt(theMap.get("seen"));
        imageAddress = theMap.get("imageAddress");
        videoAddress = theMap.get("videoAddress");
    }

    public void setDefaultSellInfo(ProductSellInfo defaultSellInfo) {
        this.defaultSellInfo = defaultSellInfo;
    }

    //    enum ProductStatus {
//        UNDER_REVIEW_FOR_CREATE,
//        REVIEW_FOR_EDITING,
//        APPROVED;
//    }

    public HashMap<String, String> getProductInfoForProductsList() {
        HashMap<String, String> productInfo = new HashMap<>();
        productInfo.put("id", id);
        productInfo.put("name", name);
        productInfo.put("averageScore", "" + averageScore);
        productInfo.put("imageAddress", imageAddress);
        if (isAvailable())
            productInfo.put("price", "" + getMinimumPrice());
        else
            productInfo.put("price", "unavailable");
        return productInfo;
    }

    public ArrayList<HashMap<String, String>> getProductOffInfoForProductsList() {
        ArrayList<HashMap<String, String>> productOffInfos = new ArrayList<>();
        for (Map.Entry<Seller, ProductSellInfo> sellerInfo : sellersList.entrySet()) {
            HashMap<String, String> offInfo = new HashMap<>();
            ProductSellInfo sellInfo = sellerInfo.getValue();
            if (sellInfo.isInOff()) {
                offInfo.put("productId", id);
                offInfo.put("name", name);
                offInfo.put("averageScore", "" + averageScore);
                offInfo.put("imageAddress", imageAddress);
                offInfo.put("sellInfoId", sellInfo.getId());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd - hh : mm");
                offInfo.put("startTime", simpleDateFormat.format(sellInfo.getOff().getStartTime()));
                offInfo.put("endTime", simpleDateFormat.format(sellInfo.getOff().getEndTime()));
                offInfo.put("remainingTime", "" + (sellInfo.getOff().getEndTime().getTime())/3600000);
                offInfo.put("originalPrice", "" + sellInfo.getPrice());
                offInfo.put("discountPercent", "" + sellInfo.getOff().getDiscountAmount());
                offInfo.put("finalPrice", "" + sellInfo.getFinalPrice());
                productOffInfos.add(offInfo);
            }
        }
        return productOffInfos;
    }

    public String getImageAddress() {
        return imageAddress;
    }

}