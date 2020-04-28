package view;

import java.util.HashMap;

public abstract class Menu extends UIPage {
    protected HashMap<String, UIPage> submenus;
    protected Menu parent;


    protected Menu(String name, Menu parent) {
        super(name);
        this.parent = parent;
        this.submenus = new HashMap<String, UIPage>();
        submenus.put("login", Login_RegisterPanel.getInstance());
        submenus.put("logout", getLogoutPanel());
    }

    private Panel getLogoutPanel() {
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
            UIPage nextUIPage = submenus.get(input);
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

    protected abstract Panel createShowHelpPanel();

    @Override
    protected String getType() {
        return "menu";
    }
}