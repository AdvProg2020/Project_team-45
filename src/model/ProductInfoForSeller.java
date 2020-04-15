package model;

import model.user.Seller;

public class ProductInfoForSeller {
    private Product product;
    private Seller seller; //useless
    private int price;
    private int stock;
    private Off off;

    public ProductInfoForSeller(Product product, Seller seller, int price, int stock) {
        this.product = product;
        this.seller = seller;
        this.price = price;
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
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

    private boolean isItAtOff() {
        return false;
    }
}
