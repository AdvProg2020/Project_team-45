package model.request;

import model.Product;

public class ProductEditionRequest extends Request {
    private Product product;
    private String productId;

    public ProductEditionRequest(String requestId, Product product, String productId) {
        super(requestId);
        this.product = product;
        this.productId = productId;
    }

    @Override
    public void apply() {

    }
}
