package model.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.CodedDiscount;
import model.Market;
import model.Savable;
import model.log.BuyLog;
import model.log.Log;
import model.log.SellLog;
import model.product.Product;
import model.product.ProductSellInfo;
import model.product.Rate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Buyer extends User implements CartHolder, Savable {
    private Cart cart;
    private ArrayList<CodedDiscount> listOfCodedDiscounts;
    private int balance;
    private ArrayList<BuyLog> listOfBuyLogs;
    private HashMap<String, Rate> purchasedProducts; // productIds and rates

    public Buyer(PersonalInfo personalInfo) {
        super(personalInfo);
        this.cart = new Cart();
        this.balance = 100;
        this.listOfCodedDiscounts = new ArrayList<>();
        this.listOfBuyLogs = new ArrayList<>();
        this.purchasedProducts = new HashMap<>();
    }

    public Buyer(String id) {
        super(id);
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public ArrayList<CodedDiscount> getListOfCodedDiscounts() {
        return listOfCodedDiscounts;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ArrayList<BuyLog> getListOfBuyLogs() {
        return listOfBuyLogs;
    }

    public BuyLog getBuyLogById(String logId) {
        for (BuyLog buyLog : listOfBuyLogs) {
            if (buyLog.getMainLog().getId().equals(logId))
                return buyLog;
        }
        return null;
    }

    public boolean didBuyProduct(String productId) {
        for (String id : purchasedProducts.keySet()) {
            if (id.equals(productId)) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Rate> getPurchasedProducts() {
        return purchasedProducts;
    }

    public CodedDiscount getDiscountByCode(String discountCode) {
        for (CodedDiscount discount : listOfCodedDiscounts) {
            if (discount.getCode().equalsIgnoreCase(discountCode)) {
                return discount;
            }
        }
        return null;
    }

    public boolean isDiscountCodeValid(String discountCode) {
        CodedDiscount discount = getDiscountByCode(discountCode);
        if (discount == null) {
            return false;
        }
        Date currentDate = new Date();
        return discount.getStartDate().compareTo(currentDate) <= 0 && discount.getEndDate().compareTo(currentDate) >= 0;
    }

    public void purchase(Log log) {
        for (ProductSellInfo sellInfo : getCart().getProductSellInfos()) {
            purchasedProducts.put(sellInfo.getProduct().getId(), null);
            sellInfo.getProduct().addSellCount();
            sellInfo.addSellCount();
            sellInfo.getAllBuyers().put(this, getCart().getProductAmountById(sellInfo.getProduct().getId()));
            Seller seller = sellInfo.getSeller();
            SellLog sellLog = new SellLog(log, seller);
            seller.getListOfSellLogs().add(sellLog);
            seller.setBalance(seller.getBalance() + sellLog.getIncome());
        }
        BuyLog buyLog = new BuyLog(log);
        listOfBuyLogs.add(buyLog);
        log.calculateFinalPrice();
        setBalance(getBalance() - log.getFinalPrice());
        cart = new Cart();
    }

    public boolean canBuy() {
        return false;
    }

    public void rateProduct(Product product, Rate rate) {
    }

    @Override
    public String getRole() {
        return "buyer";
    }

    public void removeCodedDiscountFromList(CodedDiscount removingCodedDiscount) {
        listOfCodedDiscounts.remove(removingCodedDiscount);
    }

    public void addCodedDiscount(CodedDiscount addingDiscount) {
        listOfCodedDiscounts.add(addingDiscount);
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("personalInfo", (new Gson()).toJson(personalInfo));
        ArrayList<String> discounts = new ArrayList<>();
        for (CodedDiscount discount : listOfCodedDiscounts) {
            discounts.add(discount.getId());
        }
        result.put("listOfCodedDiscounts", (new Gson()).toJson(discounts));

        result.put("balance", "" + balance);

        ArrayList<HashMap<String, String>> buyLogs = new ArrayList<>();
        for (BuyLog buyLog : listOfBuyLogs) {
            buyLogs.add(buyLog.convertToHashMap());
        }
        result.put("listOfBuyLogs", (new Gson()).toJson(buyLogs));

        HashMap<String, String> products = new HashMap<>();
        System.out.println(purchasedProducts);
        for (String productId : purchasedProducts.keySet()) {
            products.put(productId, purchasedProducts.get(productId) == null ? "null" : purchasedProducts.get(productId).getId());
        }
        result.put("purchasedProducts", (new Gson()).toJson(products));

        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        personalInfo = (new Gson()).fromJson(theMap.get("personalInfo"), PersonalInfo.class);
        ArrayList<String> codedDiscounts = (new Gson()).fromJson(theMap.get("listOfCodedDiscounts"), new TypeToken<ArrayList<String>>(){}.getType());
        listOfCodedDiscounts = new ArrayList<>();
        for (String id : codedDiscounts) {
            listOfCodedDiscounts.add(Market.getInstance().getCodedDiscountById(id));
        }

        balance = Integer.parseInt((new Gson()).fromJson(theMap.get("balance"), String.class));

        ArrayList<HashMap<String, String>> buyLogs = (new Gson()).fromJson(theMap.get("listOfBuyLogs"), new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType());
        listOfBuyLogs = new ArrayList<>();
        for (HashMap<String, String> hashMap : buyLogs) {
            BuyLog buyLog = new BuyLog();
            buyLog.setFieldsFromHashMap(hashMap);
            listOfBuyLogs.add(buyLog);
        }

        HashMap<String, String> products = (new Gson()).fromJson(theMap.get("purchasedProducts"), new TypeToken<HashMap<String, String>>(){}.getType());
        purchasedProducts = new HashMap<>();
        for (String productId : products.keySet()) {
            purchasedProducts.put(productId, Market.getInstance().getRateById(products.get(productId)));
        }
    }
}
