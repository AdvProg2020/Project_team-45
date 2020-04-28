package view;

import java.util.HashMap;

public abstract class Panel extends UIPage {
    protected HashMap<String, String> fields;

    protected Panel(String name) {
        super(name);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected String getType () {
        return "panel";
    }
}
