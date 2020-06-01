package model.product;

import com.sun.applet2.AppletParameters;
import model.*;
import model.user.Buyer;
import model.user.Seller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProductSellInfo extends IdRecognized implements Savable {
    private Seller seller;
    private Product product;
    private int price;
    private int stock;
    private Off off;
    private int sellCount;
    private final HashMap<Buyer, Integer> allBuyers;

    public ProductSellInfo(Product product, Seller seller) {
        this.id = "" + IdKeeper.getInstance().getProductSellInfosNewId();
        this.product = product;
        this.seller = seller;
        this.allBuyers = new HashMap<>();
    }

    public ProductSellInfo(String id) {
        allBuyers = new HashMap<>();
        this.id = id;
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

    @Override
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
        return "seller:" + seller.getUsername() +
                ", product:" + product.getName() +
                ", price:" + price +
                ", stock:" + stock +
                ", off:" + off +
                ", sellCount:" + sellCount;
    }

    @Override
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("seller", seller.getId());
        result.put("product", product.getId());
        result.put("price", price);
        result.put("stock", stock);
        result.put("off", off.getId());
        HashMap<String, Integer> buyersId = new HashMap<>();
        for (Map.Entry<Buyer, Integer> buyer : allBuyers.entrySet()) {
            buyersId.put(buyer.getKey().getId(), buyer.getValue());
        }
        result.put("allBuyers", buyersId);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        Market market = Market.getInstance();
        seller = (Seller) (market.getUserById((String) theMap.get("seller")));
        product = market.getProductById((String) theMap.get("product"));
        price = (int) theMap.get("price");
        stock = (int) theMap.get("stock");
        off = market.getOffById((String) theMap.get("off"));
        for (Map.Entry<String, Integer> buyerId : ((HashMap<String, Integer>) theMap.get("allBuyers")).entrySet()) {
            allBuyers.put((Buyer) market.getUserById(buyerId.getKey()), buyerId.getValue());
        }
    }
}
