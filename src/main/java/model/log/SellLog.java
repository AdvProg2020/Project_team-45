package model.log;

import model.product.ProductSellInfo;
import model.user.Seller;

import java.util.ArrayList;

public class SellLog {
    private final Log mainLog;
    private final Seller seller;
    private ArrayList<ProductSellInfo> soldProducts;
    private int income;


    public SellLog(Log mainLog, Seller seller) {
        this.mainLog = mainLog;
        this.seller = seller;
        findMyProductsAndIncome();
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

    //    public SellLog(String logId, Date date, int finalPrice, String address, String buyerUsername) {
//        super(logId, date, finalPrice, address);
//        this.buyerUsername = buyerUsername;
//    }

}
