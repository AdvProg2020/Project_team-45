package view;

import java.util.HashMap;

public abstract class Menu extends UIPage {
    protected HashMap<String, UIPage> submenus;
    protected Menu parent;


    public Menu(String name, Menu parent) {
        super(name);
        this.parent = parent;
        this.submenus = new HashMap<>();
        submenus.put("login", new Login_RegisterPanel());
    }

    protected void show() {
    }

    protected void execute() {
    }

    protected void showHelp() {
    }
}
