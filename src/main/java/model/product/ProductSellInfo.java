package model.product;

import model.IdRecognized;
import model.Off;
import model.user.Buyer;
import model.user.Seller;

import java.util.Date;
import java.util.HashMap;

public class ProductSellInfo implements IdRecognized {
    private String id;
    private final Seller seller;
    private final Product product;
    private int price;
    private int stock;
    private Off off;
    private int sellCount;
    private final HashMap<Buyer, Integer> allBuyers;

    public ProductSellInfo(Product product, Seller seller) {
        this.product = product;
        this.seller = seller;
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
        if (off == null) {
            return price;
        }
        Date currentDate = new Date();
        if (off.getStartTime().compareTo(currentDate) <= 0 && off.getEndTime().compareTo(currentDate) >= 0) {
            return (int) (price * (100 - off.getDiscountAmount()) / 100.0);
        }

        return price;
    }

    public String getId() {
        return id;
    }

    public int getSellCount() {
        return sellCount;
    }

    public HashMap<Buyer, Integer> getAllBuyers() {
        return allBuyers;
    }

    public boolean isInOff() {
        return off != null;
    }

    public Product getProduct() {
        return product;
    }

    public void addSellCount() {
        sellCount++;
    }

    @Override
    public ProductSellInfo clone() {
        return new ProductSellInfo(this.product, this.seller);
    }

    public void removeProduct() {
        seller.removeProductFromSellerList(product);
        if (isInOff())
            off.removeProduct(product);
    }

    @Override
    public String toString() {
        return  "seller:" + seller.getUsername() +
                ", product:" + product.getName() +
                ", price:" + price +
                ", stock:" + stock +
                ", off:" + off +
                ", sellCount:" + sellCount;
    }
}
