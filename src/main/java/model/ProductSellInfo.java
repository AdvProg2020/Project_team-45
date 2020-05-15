package model;

import model.user.Buyer;
import model.user.Seller;

import java.util.HashMap;

public class ProductSellInfo {
    private Seller seller;
    private Product product;
    private int price;
    private int stock;
    private Off off;
    private int sellCount;
    private HashMap<Buyer, Integer> allBuyers;

    public ProductSellInfo(Product product, Seller seller, int price, int stock) {
        this.product = product;
        this.seller = seller;
        this.price = price;
        this.stock = stock;
        this.allBuyers = new HashMap<Buyer, Integer>();
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

    public int getSellCount() {
        return sellCount;
    }

    public HashMap<Buyer, Integer> getAllBuyers() {
        return allBuyers;
    }

    @Override
    public ProductSellInfo clone() {
        try {
            return (ProductSellInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
