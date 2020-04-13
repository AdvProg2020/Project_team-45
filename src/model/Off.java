package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Off {
    private String offId;
    private ArrayList<Product> productList;
    private OffStatus content;
    private LocalDateTime starTime;
    private LocalDateTime endTime;
    private int discountAmount;

    public Off(String offId, ArrayList<Product> productList, OffStatus content, LocalDateTime endTime, int discountAmount) {
        this.offId = offId;
        this.productList = productList;
        this.content = content;
        this.endTime = endTime;
        this.discountAmount = discountAmount;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void addProduct(Product) {

    }

    public boolean removeProduct(Product) {

    }

    public void requestEditing() {

    }

    public void approvingOff() {

    }

    enum OffStatus {

    }
}
