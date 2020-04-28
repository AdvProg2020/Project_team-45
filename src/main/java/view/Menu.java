package view;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu extends UIPage {
    protected HashMap<String, UIPage> submenus;
    protected Menu parent;


    protected Menu(String name, Menu parent) {
        super(name);
        this.parent = parent;
        this.submenus = new HashMap<String, UIPage>();
        submenus.put("login", Login_RegisterPanel.getInstance());
        submenus.put("logout", createLogoutPanel());
    }

    private Panel createLogoutPanel() {
        return new Panel("logoutPane") {
            @Override
            public void execute() {
                //***************//
            }
        };
    }

    private void setParent(Menu parent) {
        this.parent = parent;
    }

    protected void execute() {
        show();
        String input;
        while (!(input = scanner.nextLine()).equals("back")) {
            UIPage nextUIPage = getUIPageByCommand(input);
            if (nextUIPage != null) {
                if (nextUIPage.getType().equals("menu")) {
                    Menu nextMenu = (Menu) nextUIPage;
                    MenuManagement.setActiveMenu(nextMenu);
                    nextMenu.setParent(this);
                    return;
                }
                nextUIPage.execute();
            } else {
                System.out.println("invalid command!");
            }
        }
        MenuManagement.setActiveMenu(parent);
    }

    private UIPage getUIPageByCommand(String input) {
        for(Map.Entry<String,UIPage> submenu : submenus.entrySet()) {
            matcher = getMatcher(submenu.getKey(), input);
            if(matcher != null)
                return submenu.getValue();
        }
        return null;
    }

    protected void show() {
    }

    protected abstract void showHelp();

    @Override
    protected String getType() {
        return "menu";
    }
}