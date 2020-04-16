package view;

import java.util.HashMap;

public abstract class Panel extends UIPage {
    protected HashMap<String, String> fields;

    public Panel(String name) {
        super(name);
    }

    @Override
    public void execute() {

    }

    protected void getFieldsFromUser() {
    }
}
