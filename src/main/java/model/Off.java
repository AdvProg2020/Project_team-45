package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Off {
    private String offId;
    private ArrayList<Product> productsList;
    private OffStatus offStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int discountAmount;

    public Off(String offId, ArrayList<Product> productsList, OffStatus offStatus, LocalDateTime endTime, int discountAmount) {
        this.offId = offId;
        this.productsList = productsList;
        this.offStatus = offStatus;
        this.endTime = endTime;
        this.discountAmount = discountAmount;
    }

    public String getOffId() {
        return offId;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public OffStatus getOffStatus() {
        return offStatus;
    }

    public LocalDateTime getStartTime() {
        return startTime;
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

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void addProduct(Product newProduct) {

    }

    public boolean removeProduct(Product product) {
        return false;
    }

    public void requestForEdition() {

    }

    public void approveOff() {

    }

    public boolean equals(Off off) {
        return off.offId.equals(this.offId);
    }

    enum OffStatus {
        UNDER_REVIEW_FOR_CREATE,
        REVIEW_FOR_EDITING,
        APPROVED
    }
}
