package model;

import model.user.Buyer;
import model.user.Seller;

import java.util.HashMap;

public class SellerInfoForProduct {
    private Seller seller;
    private int price;
    private int stock;
    private Off off;
    private HashMap<Buyer, Integer> allBuyers;

    public SellerInfoForProduct(Seller seller, int price, int stock) {
        this.seller = seller;
        this.price = price;
        this.stock = stock;
        this.allBuyers = new HashMap<>();
    }

    public Seller getSeller() {
        return seller;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Off getOff() {
        return off;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setOff(Off off) {
        this.off = off;
    }

    public int getFinalPrice() {
        return 0;
    }
}
