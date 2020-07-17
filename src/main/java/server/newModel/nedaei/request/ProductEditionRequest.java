package server.newModel.nedaei.request;

import server.model.Market;
import server.model.Off;
import server.model.product.ProductSellInfo;
import server.model.user.Seller;

import java.util.HashMap;

public class ProductEditionRequest extends Request {
    private String productId;
    private Seller seller;

    public ProductEditionRequest(String productId, Seller seller, HashMap<String, String> fieldsAndValues) {
        super(fieldsAndValues);
        this.productId = productId;
        this.seller = seller;
    }

    public ProductEditionRequest(String id) {
        super(id);
    }

    public String getProductId() {
        return productId;
    }

    public Seller getSeller() {
        return seller;
    }

    @Override
    public void apply() {
        ProductSellInfo productSellInfo = seller.getAvailableProductById(productId)
                .getSellerInfoForProductByUsername(seller.getPersonalInfo().getUsername());
        if (fieldsAndValues.containsKey("price") && !fieldsAndValues.get("price").equals("")) {
            productSellInfo.setPrice(Integer.parseInt(fieldsAndValues.get("price")));
        } if (fieldsAndValues.containsKey("stock") && !fieldsAndValues.get("stock").equals("")) {
            productSellInfo.setStock(Integer.parseInt(fieldsAndValues.get("stock")));
        } if (fieldsAndValues.containsKey("offId") && !fieldsAndValues.get("offId").equals("")) {
            Off off = Market.getInstance().getOffById(fieldsAndValues.get("offId"));
            if (off != null) {
                productSellInfo.setOff(off);
            }
        }
    }

    @Override
    public String getType() {
        return "edit product";
    }

    @Override
    public String toString() {
        return super.toString() +
                "productId:'" + productId +
                ", seller:" + seller.getPersonalInfo().getUsername();
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = super.convertToHashMap();
        result.put("seller", seller.getId());
        result.put("productId", productId);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        super.setFieldsFromHashMap(theMap);
        seller = (Seller) Market.getInstance().getUserById(theMap.get("seller"));
        productId = theMap.get("productId");
    }
}
