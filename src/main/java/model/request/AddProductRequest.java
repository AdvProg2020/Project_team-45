package model.request;

import model.Off;
import model.Product;

import java.util.HashMap;

public class AddProductRequest extends Request {
    private HashMap<String, String> fieldsAndValues;

    public AddProductRequest(HashMap<String, String> fieldsAndValues) {
        super();
        this.fieldsAndValues = fieldsAndValues;
    }

    @Override
    public void apply() {

    }
}
