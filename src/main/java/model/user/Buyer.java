package model.user;

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

    public Cart getCart() {
        return cart;
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
        //System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + getCart().getTotalPrice());
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
    public HashMap convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        ArrayList<String> codedDiscounts = new ArrayList<>();
        for (CodedDiscount discount : listOfCodedDiscounts) {
            codedDiscounts.add(discount.getCode());
        }
        result.put("listOfCodedDiscounts", codedDiscounts);

        result.put("balance", balance);

        ArrayList<HashMap> buyLogs = new ArrayList<>();
        for (BuyLog buyLog : listOfBuyLogs) {
            buyLogs.add(buyLog.convertToHashMap());
        }
        result.put("listOfBuyLogs", buyLogs);

        HashMap<String, HashMap> products = new HashMap<>();
        for (String productId : purchasedProducts.keySet()) {   // we must change this part if allRates is stored in market
            products.put(productId, purchasedProducts.get(productId).convertToHashMap());
        }
        result.put("purchasedProducts", products);

        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap theMap) {
        ArrayList<String> codedDiscounts = (ArrayList<String>) theMap.get("listOfCodedDiscounts");
        listOfCodedDiscounts = new ArrayList<>();
        for (String code : codedDiscounts) {
            listOfCodedDiscounts.add(Market.getInstance().getCodedDiscountByCode(code));
        }

        balance = (int) theMap.get("balance");

        ArrayList<HashMap> buyLogs = (ArrayList<HashMap>) theMap.get("listOfBuyLogs");
        listOfBuyLogs = new ArrayList<>();
        for (HashMap hashMap : buyLogs) {
            BuyLog buyLog = new BuyLog();
            buyLog.setFieldsFromHashMap(hashMap);
            listOfBuyLogs.add(buyLog);
        }

        HashMap<String, HashMap> products = (HashMap<String, HashMap>) theMap.get("purchasedProducts");
        purchasedProducts = new HashMap<>();
        for (String productId : products.keySet()) {   // we must change this part if allRates is stored in market
            Rate rate = new Rate();
            rate.setFieldsFromHashMap(products.get(productId));
            purchasedProducts.put(productId, rate);
        }
    }
}
