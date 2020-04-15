package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Off {
    private String offId;
    private ArrayList<Product> productList;
    private OffStatus condition;
    private LocalDateTime starTime;
    private LocalDateTime endTime;
    private int discountAmount;

    public Off(String offId, ArrayList<Product> productList, OffStatus condition, LocalDateTime endTime, int discountAmount) {
        this.offId = offId;
        this.productList = productList;
        this.condition = condition;
        this.endTime = endTime;
        this.discountAmount = discountAmount;
    }

    public String getOffId() {
        return offId;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public OffStatus getCondition() {
        return condition;
    }

    public LocalDateTime getStarTime() {
        return starTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStarTime(LocalDateTime starTime) {
        this.starTime = starTime;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void addProduct(Product newProduct) {

    }

    public boolean removeProduct(Product product) {
        return false;
    }

    public void requestEditing() {

    }

    public void approvingOff() {

    }

    enum OffStatus {
        UNDER_REVIEW_FOR_CREATE,
        REVIEW_FOR_EDITING,
        APPROVED;
    }
}
