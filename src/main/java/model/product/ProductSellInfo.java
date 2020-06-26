package model.product;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
        return ", id:" + id;
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("seller", seller.getId());
        result.put("product", product.getId());
        result.put("price", "" + price);
        result.put("stock", "" + stock);
        if (off != null) {
            result.put("off", off.getId());
        }
        HashMap<String, Integer> buyersId = new HashMap<>();
        for (Map.Entry<Buyer, Integer> buyer : allBuyers.entrySet()) {
            buyersId.put(buyer.getKey().getId(), buyer.getValue());
        }
        result.put("allBuyers", (new Gson()).toJson(buyersId));
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        Market market = Market.getInstance();
        seller = (Seller) (market.getUserById(theMap.get("seller")));
        product = market.getProductById(theMap.get("product"));
        price = Integer.parseInt(theMap.get("price"));
        stock = Integer.parseInt(theMap.get("stock"));
        if (theMap.containsKey("off")) {
            off = market.getOffById(theMap.get("off"));
        }
        HashMap<String, Integer> buyersId = (new Gson()).fromJson(theMap.get("allBuyers"), new TypeToken<HashMap<String, Integer>>(){}.getType());
        for (Map.Entry<String, Integer> buyerId : buyersId.entrySet()) {
            allBuyers.put((Buyer) market.getUserById(buyerId.getKey()), buyerId.getValue());
        }
    }

    public HashMap<String, String> getInformation() {
        HashMap<String, String> information = new HashMap<>();
        information.put("sellerUsername", seller.getUsername());
        information.put("originalPrice", "" + price);
        information.put("finalPrice", "" + getFinalPrice());
        if (off != null) {
            information.put("discountPercent", "" + off.getDiscountAmount());
        } else {
            information.put("discountPercent", "0");
        }
        return information;
    }
}