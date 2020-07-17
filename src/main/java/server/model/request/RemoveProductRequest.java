package server.model.request;

import server.model.Market;
import server.model.product.Product;
import server.model.user.Seller;

import java.util.HashMap;

public class RemoveProductRequest extends Request{
    private String productId;
    private Seller seller;

    public RemoveProductRequest(Seller seller, String productId) {
        super(new HashMap<>());
        this.productId = productId;
        this.seller = seller;
    }

    public RemoveProductRequest(String id) {
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
        Product product = seller.getAvailableProductById(productId);
        seller.removeProductByProductId(productId);
        product.getSellersList().remove(seller);
        if (product.getSellersList().size() == 0) {
            product.getCategory().removeProduct(product);
            Market.getInstance().removeProductByProductId(productId);
        }
    }

    @Override
    public String getType() {
        return "remove product";
    }

    @Override
    public String toString() {
        return super.toString() +
                "productId:" + productId +
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
