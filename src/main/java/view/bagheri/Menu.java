package view.bagheri;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu extends UIPage {
    protected HashMap<String, UIPage> submenus;
    protected Menu parent;


    protected Menu(String name) {
        super(name);
        this.parent = null;
        this.submenus = new HashMap<String, UIPage>();
        submenus.put("login", Login_RegisterPanel.getInstance());
        submenus.put("logout", createLogoutPanel());
        submenus.put("help", createHelpPanel());
    }

    private Panel createLogoutPanel() {
        return new Panel("logoutPane") {
            @Override
            public void execute() {
                //needs to be completed
            }
        };
    }

    private Panel createHelpPanel() {
        return new Panel("help") {
            @Override
            protected void execute() {
                showHelp();
            }
        };
    }

    private void setParent(Menu parent) {
        this.parent = parent;
    }

    @Override
    public void execute() {
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

    protected void showHelp() {
        //needs to be completed
    }

    @Override
    protected String getType() {
        return "menu";
    }
}