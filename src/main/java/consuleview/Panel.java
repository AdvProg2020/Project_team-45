package consuleview;

import java.util.HashMap;

public abstract class Panel extends UIPage {
    protected HashMap<String, String> fields;

    protected Panel(String name) {
        super(name);
    }

    @Override
    public abstract void execute();

    @Override
    protected String getType () {
        return "Panel";
    }
}