package model.request;

import model.Market;
import model.Off;
import model.product.ProductSellInfo;
import model.user.Seller;

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

    @Override
    public void apply() {
        ProductSellInfo productSellInfo = seller.getAvailableProductById(productId)
                .getSellerInfoForProductByUsername(seller.getPersonalInfo().getUsername());
        if (fieldsAndValues.containsKey("price")) {
            productSellInfo.setPrice(Integer.parseInt(fieldsAndValues.get("price")));
        } if (fieldsAndValues.containsKey("stock")) {
            productSellInfo.setStock(Integer.parseInt(fieldsAndValues.get("stock")));
        } if (fieldsAndValues.containsKey("offId")) {
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
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = super.convertToHashMap();
        result.put("seller", seller.getId());
        result.put("productId", productId);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        super.setFieldsFromHashMap(theMap);
        seller = (Seller) Market.getInstance().getUserById((String) theMap.get("seller"));
        productId = (String) theMap.get("productId");
    }
}
