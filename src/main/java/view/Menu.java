package view;

import java.util.HashMap;

public abstract class Menu extends UIPage {
    protected HashMap<String, UIPage> submenus;
    protected Menu parent;


    public Menu(String name, Menu parent) {
        super(name);
        this.parent = parent;
        this.submenus = new HashMap<String, UIPage>();
        submenus.put("login", new Login_RegisterPanel());
    }

    protected void show() {
    }

    protected void execute() {
        String input;
        while (!(input = scanner.nextLine()).equals("back")) {
            UIPage nextUIPage = submenus.get(input);
            if (nextUIPage != null) {
                if (nextUIPage.getType().equals("menu")) {
                    activeMenu = (Menu) nextUIPage;
                    return;
                } else {
                    nextUIPage.execute();
                }
            } else {
                System.out.println("invalid command!");
            }
        }
        activeMenu = parent;
    }

//    //The name of the function must be changed:
//    protected abstract boolean checkExistCommand();

    protected abstract void showHelp();

    @Override
    public String getType() {
        return "menu";
    }
}