package model;

import java.util.ArrayList;


public class Cart {
    private ArrayList<productInfo> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public Product getProductByProductIdAndSellerUsername(String productId, String sellerUsername) {
        return null;
    }

    public ArrayList<productInfo> getProducts() {
        return products;
    }

    public void addProduct(Product product, SellerInfoForProduct sellerInfoForProduct) {

    }

    public void removeProduct(Product product, SellerInfoForProduct sellerInfoForProduct) {

    }

    public void changeProductByProductIdAndSellerUsername(String productId, String sellerUsername, int amountChange) {

    }

    public int getTotalPrice() {
        return 0;
    }
}

class productInfo {
    private Product product;
    private SellerInfoForProduct sellerInfoForProduct;
    private int amount;

    public productInfo(Product product, SellerInfoForProduct sellerInfoForProduct) {
        this.product = product;
        this.sellerInfoForProduct = sellerInfoForProduct;
        this.amount = 1;
    }

    public Product getProduct() {
        return product;
    }

    public SellerInfoForProduct getSellerInfoForProduct() {
        return sellerInfoForProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void changeAmount(int amountChange) {

    }
}


