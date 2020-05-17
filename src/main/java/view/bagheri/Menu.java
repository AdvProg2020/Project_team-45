package view.bagheri;

import controller.userControllers.UserController;

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
                UserController userController = UserController.getInstance();
                if (userController.logout()) {
                    System.out.println("You logout successfully");
                } else {
                    System.out.println("You are not logged in");
                }
            }
        };
    }

    private Panel createHelpPanel() {
        return new Panel("help") {
            @Override
            public void execute() {
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
                if (nextUIPage.getType().equals("Menu")) {
                    Menu nextMenu = (Menu) nextUIPage;
                    if (nextMenu.check()) {
                        MenuManagement.setActiveMenu(nextMenu);
                        nextMenu.setParent(this);
                        return;
                    }
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

    protected boolean check() {
        return true;
    }

    protected void showHelp() {
        System.out.println("login\n" +
                "logout\n" +
                "help\n" +
                "back");
    }

    @Override
    protected String getType() {
        return "Menu";
    }
}