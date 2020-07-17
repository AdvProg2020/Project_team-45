package server.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.model.product.Product;

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
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        ArrayList<String> productsId = new ArrayList<>();
        for (Product product : productsList) {
            productsId.add(product.getId());
        }
        result.put("productsList", (new Gson()).toJson(productsId));
        result.put("offStatus", (new Gson()).toJson(offStatus));
        result.put("startTime", (new Gson()).toJson(startTime));
        result.put("endTime", (new Gson()).toJson(endTime));
        result.put("discountAmount", "" + discountAmount);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        Market market = Market.getInstance();
        ArrayList<String> productsId = (new Gson()).fromJson(theMap.get("productsList"), new TypeToken<ArrayList<String>>(){}.getType());
        for (String productId : productsId) {
            productsList.add(market.getProductById(productId));
        }
        offStatus = (new Gson()).fromJson(theMap.get("offStatus"), OffStatus.class);
        startTime = (new Gson()).fromJson(theMap.get("startTime"), Date.class);
        endTime = (new Gson()).fromJson(theMap.get("endTime"), Date.class);
        discountAmount = Integer.parseInt(theMap.get("discountAmount"));
    }

    enum OffStatus {
        UNDER_REVIEW_FOR_CREATE,
        REVIEW_FOR_EDITING,
        APPROVED
    }
}
