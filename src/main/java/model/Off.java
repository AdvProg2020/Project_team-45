package model;

import model.product.Product;

import java.util.ArrayList;
import java.util.Date;

public class Off {
    private static Integer newOffId = 1;
    private final String offId;
    private final ArrayList<Product> productsList;
    private OffStatus offStatus;
    private Date startTime;
    private Date endTime;
    private int discountAmount;

    public Off(ArrayList<Product> productsList,Date startTime, Date endTime, int discountAmount) {
        this.offId = newOffId.toString();
        newOffId++;
        this.productsList = productsList;
        this.startTime = startTime;
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
        return off.offId.equals(this.offId);
    }

    enum OffStatus {
        UNDER_REVIEW_FOR_CREATE,
        REVIEW_FOR_EDITING,
        APPROVED
    }
}
