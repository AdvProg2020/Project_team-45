package model.request;

import model.Off;
import model.Product;

public class AddProductRequest extends Request {
    private Product product;

    public AddProductRequest(String requestId, Product product) {
        super(requestId);
        this.product = product;
    }

    @Override
    public void apply() {

    }
}
