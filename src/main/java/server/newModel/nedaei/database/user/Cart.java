package server.newModel.nedaei.database.user;

import server.model.product.ProductSellInfo;
import server.newModel.nedaei.database.DataBaseCompatible;

import java.util.ArrayList;


public class Cart extends DataBaseCompatible {
//    private final ArrayList<ProductInfo> products;

    // todo: nedaei - save amount correctly

    public Cart() {
    }

    public Cart(int id, boolean justId) {
        super(id, justId);
    }

    @Override
    protected void readFromDataBase() {

    }

    @Override
    protected void saveToDataBase() {

    }

    private ProductSellInfo getProductSellInfoById(int productSellInfoId) {
        return null;
    }

    public int getProductSellInfoAmountById(int productSellInfoId) {
        return 0;
    }

    public void changeProductSellInfoAmountById(int productSellInfoId, int amountChange) {
    }

    public int getTotalPrice() {
        return 0;
    }

    public ArrayList<ProductSellInfo> getProductSellInfos() {
        return null;
    }

    public void addProduct(ProductSellInfo productSellInfo) {
    }

    public void removeProduct(ProductSellInfo productSellInfo) {
    }
}



