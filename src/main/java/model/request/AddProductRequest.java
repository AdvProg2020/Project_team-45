package model.request;

import java.util.HashMap;

public class AddProductRequest extends Request {

    public AddProductRequest(HashMap<String, String> fieldsAndValues) {
        super(fieldsAndValues);
    }

    @Override
    public void apply() {

    }
}
