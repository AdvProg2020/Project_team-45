package model.log;

import model.Market;
import model.Savable;
import model.product.ProductSellInfo;
import model.user.Seller;

import java.util.ArrayList;
import java.util.HashMap;

public class SellLog implements Savable {
    private Log mainLog;
    private Seller seller;
    private ArrayList<ProductSellInfo> soldProducts;
    private int income;


    public SellLog(Log mainLog, Seller seller) {
        this.mainLog = mainLog;
        this.seller = seller;
        findMyProductsAndIncome();
    }

    public SellLog() {

    }

    private void findMyProductsAndIncome() {
        this.soldProducts = new ArrayList<ProductSellInfo>();
        this.income = 0;
        for (ProductSellInfo sellingProduct : mainLog.getSellingProducts()) {
            if (sellingProduct.getSeller().equals(seller)) {
                soldProducts.add(sellingProduct);
                income += sellingProduct.getFinalPrice();
            }
        }
    }

    public Log getMainLog() {
        return mainLog;
    }

    public ArrayList<ProductSellInfo> getSoldProducts() {
        return soldProducts;
    }

    public int getIncome() {
        return income;
    }

    @Override
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("mainLog", mainLog.getId());
        result.put("seller", seller.getId());
        result.put("income", income);
        ArrayList<String> soldIds = new ArrayList<>();
        for (ProductSellInfo soldProduct : soldProducts) {
            soldIds.add(soldProduct.getId());
        }
        result.put("soldProducts", soldIds);

        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        mainLog = Market.getInstance().getLogById((String) theMap.get("mainLog"));
        seller = (Seller) Market.getInstance().getUserById((String) theMap.get("seller"));
        income = Integer.parseInt((String) theMap.get("income"));
        ArrayList<String> soldIds = (ArrayList<String>) theMap.get("soldProducts");
        soldProducts = new ArrayList<>();
        for (String soldId : soldIds) {
            soldProducts.add(Market.getInstance().getProductSellInfoById(soldId));
        }
    }

}
