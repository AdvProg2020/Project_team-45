package view;

import java.util.HashMap;

public abstract class ManagingMenu extends Menu {
    protected HashMap<String, Object> managingObjects;

    public ManagingMenu(String name, Menu parent) {
        super(name, parent);
    }

    protected void showAll() {
    }

    protected void showOneByKey(String key) {
    }
}
