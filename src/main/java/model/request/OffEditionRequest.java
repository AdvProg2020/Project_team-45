package model.request;

import java.util.HashMap;

public class OffEditionRequest extends Request{
    private String offId;

    public OffEditionRequest(String offId, HashMap<String, String> fieldsAndValues) {
        super(fieldsAndValues);
        this.offId = offId;
    }


    @Override
    public void apply() {

    }
}
