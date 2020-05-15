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

    public void addProduct(Product product, ProductSellInfo productSellInfo) {

    }

    public void removeProduct(Product product, ProductSellInfo productSellInfo) {

    }

    public void changeProductByProductIdAndSellerUsername(String productId, String sellerUsername, int amountChange) {

    }

    public int getTotalPrice() {
        return 0;
    }
}

class productInfo {
    private Product product;
    private ProductSellInfo productSellInfo;
    private int amount;

    public productInfo(Product product, ProductSellInfo productSellInfo) {
        this.product = product;
        this.productSellInfo = productSellInfo;
        this.amount = 1;
    }

    public Product getProduct() {
        return product;
    }

    public ProductSellInfo getProductSellInfo() {
        return productSellInfo;
    }

    public int getAmount() {
        return amount;
    }

    public void changeAmount(int amountChange) {

    }
}


