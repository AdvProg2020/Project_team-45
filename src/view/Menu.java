package view;

public class Menu extends UI {
    private java.util.ArrayList<UI> submenus;
    private Menu parent;


    public Menu(String name, Menu parent) {
        super(name);
        this.parent = parent;
    }

    protected void show() {
    }

    protected void execute() {
    }

    protected void showHelp() {
    }
}
