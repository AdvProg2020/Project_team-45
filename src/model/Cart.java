package model;

import model.user.Seller;

import java.util.ArrayList;


public class Cart {
    private ArrayList<productInfo> listOfProducts;
    private int totalPrice;

    public Cart() {
        listOfProducts = new ArrayList<>();
    }

    public Product getProductById(String productId) {
        return null;
    }

    public void increaseProductById(String productId, String sellerName) {

    }

    public void decreaseProductById(String productId, String sellerName) {

    }

    public int getTotalPrice() {
        return totalPrice;
    }

}

class productInfo{
    private Product product;
    private Seller seller;
    private int number;

    public productInfo(Product product, Seller seller) {
        this.product = product;
        this.seller = seller;
        this.number = 1;
    }

    public Product getProduct() {
        return product;
    }

    public Seller getSeller() {
        return seller;
    }

    public int getNumber() {
        return number;
    }

    public void increaseNumber() {

    }
}


