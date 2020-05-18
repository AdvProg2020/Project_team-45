package model.request;

import model.Market;
import model.Product;
import model.user.Seller;

import java.util.HashMap;

public class RemoveProductRequest extends Request{
    private String productId;
    private Seller seller;

    public RemoveProductRequest(Seller seller, String productId) {
        super(new HashMap<>());
        this.productId = productId;
        this.seller = seller;
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
}
