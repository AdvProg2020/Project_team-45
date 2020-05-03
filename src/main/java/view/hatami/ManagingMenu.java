package view.hatami;

import view.bagheri.Menu;

import java.util.HashMap;

public abstract class ManagingMenu extends Menu {

    public ManagingMenu(String name, Menu parent) {
        super(name, parent);
    }

    protected void showAll() {
    }

    protected void showOneByKey(String key) {
    }
}
