package model.request;

import java.util.HashMap;

public class ProductEditionRequest extends Request {
    private String productId;
    private HashMap<String, String> fieldsAndValues;

    public ProductEditionRequest(String productId, HashMap<String, String> fieldsAndValues) {
        super();
        this.productId = productId;
        this.fieldsAndValues = fieldsAndValues;
    }

    @Override
    public void apply() {

    }
}
