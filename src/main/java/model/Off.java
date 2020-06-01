package model;

import model.product.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Off extends IdRecognized implements Savable {
    private final ArrayList<Product> productsList;
    private OffStatus offStatus;
    private Date startTime;
    private Date endTime;
    private int discountAmount;

    public Off(ArrayList<Product> productsList, Date startTime, Date endTime, int discountAmount) {
        this.id = "" + IdKeeper.getInstance().getOffsNewId();
        this.productsList = productsList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountAmount = discountAmount;
    }

    public Off(String id) {
        productsList = new ArrayList<>();
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public OffStatus getOffStatus() {
        return offStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void addProduct(Product newProduct) {

    }

    public void removeProduct(Product product) {
        productsList.remove(product);
    }

    public void requestForEdition() {

    }

    public void approveOff() {

    }

    public boolean equals(Off off) {
        return off.id.equals(this.id);
    }

    @Override
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        ArrayList<String> productsId = new ArrayList<>();
        for (Product product : productsList) {
            productsId.add(product.getId());
        }
        result.put("productsList", productsId);
        result.put("offStatus", offStatus);
        result.put("startTime", startTime);
        result.put("endTime", endTime);
        result.put("discountAmount", discountAmount);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        Market market = Market.getInstance();
        for (String productId : ((ArrayList<String>) theMap.get("productsList"))) {
            productsList.add(market.getProductById(productId));
        }
        offStatus = (OffStatus) theMap.get("offStatus");
        startTime = (Date) theMap.get("startTime");
        endTime = (Date) theMap.get("endTime");
        discountAmount = (int) theMap.get("discountAmount");
    }

    enum OffStatus {
        UNDER_REVIEW_FOR_CREATE,
        REVIEW_FOR_EDITING,
        APPROVED
    }
}
