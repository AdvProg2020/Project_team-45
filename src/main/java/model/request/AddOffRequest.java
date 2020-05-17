package model.request;

import java.util.HashMap;

public class AddOffRequest extends Request{

    public AddOffRequest(HashMap<String, String> fieldsAndValues) {
        super(fieldsAndValues);
    }

    @Override
    public void apply() {

    }

    @Override
    public String getType() {
        return "add off";
    }
}
