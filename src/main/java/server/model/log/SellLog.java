package server.model.log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.model.Market;
import server.model.Savable;
import server.model.product.ProductSellInfo;
import server.model.user.Seller;

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
        this.soldProducts = new ArrayList<>();
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
        findMyProductsAndIncome();
        return income;
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("mainLog", mainLog.getId());
        result.put("seller", seller.getId());
        findMyProductsAndIncome();
        result.put("income", "" + income);
        ArrayList<String> soldIds = new ArrayList<>();
        for (ProductSellInfo soldProduct : soldProducts) {
            soldIds.add(soldProduct.getId());
        }
        result.put("soldProducts", (new Gson()).toJson(soldIds));

        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        mainLog = Market.getInstance().getLogById(theMap.get("mainLog"));
        seller = (Seller) Market.getInstance().getUserById(theMap.get("seller"));
        income = Integer.parseInt(theMap.get("income"));
        ArrayList<String> soldIds = (new Gson()).fromJson(theMap.get("soldProducts"), new TypeToken<ArrayList<String>>(){}.getType());
        soldProducts = new ArrayList<>();
        for (String soldId : soldIds) {
            soldProducts.add(Market.getInstance().getProductSellInfoById(soldId));
        }
    }

}
