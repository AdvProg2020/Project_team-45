package model.request;

import java.util.HashMap;

public class ProductEditionRequest extends Request {
    private String productId;

    public ProductEditionRequest(String productId, HashMap<String, String> fieldsAndValues) {
        super(fieldsAndValues);
        this.productId = productId;
    }

    @Override
    public void apply() {

    }
}
