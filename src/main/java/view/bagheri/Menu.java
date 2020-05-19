package view.bagheri;

import controller.userControllers.UserController;
import view.nedaei.MainMenu;
import view.nedaei.buyermenu.CartManagingMenu;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu extends UIPage {
    protected HashMap<String, UIPage> submenus;
    protected Menu parent;

    protected Menu(String name) {
        super(name);
        this.parent = null;
        this.submenus = new HashMap<>();
        submenus.put("login", Login_RegisterPanel.getInstance());
        submenus.put("logout", createLogoutPanel());
        submenus.put("view cart", CartManagingMenu.getInstance());
        submenus.put("help", createHelpPanel());
        submenus.put("exit", createExitPanel());
    }

    public Menu() {
        super("");
    }

    private Panel createLogoutPanel() {
        return new Panel("logoutPane") {
            @Override
            public void execute() {
                UserController userController = UserController.getInstance();
                if (userController.logout()) {
                    MainMenu.getInstance().updateSubmenus();
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

    private Panel createExitPanel() {
        return new Panel("exit") {
            @Override
            public void execute() {
                MenuManagement.exit();
            }
        };
    }

    @Override
    public void execute() {
        checkLogin();
        show();
        String input;
        while (!(input = scanner.nextLine()).equals("back")) {
            UIPage nextUIPage = getUIPageByCommand(input);
            if (nextUIPage != null) {
                if (nextUIPage.getType().equals("Menu")) {
                    Menu nextMenu = (Menu) nextUIPage;
                    if (nextMenu.check()) {
                        MenuManagement.next(nextMenu);
                        return;
                    }
                }
                nextUIPage.execute();
            } else if("exit") {

            }
            else {
                System.out.println("invalid command!");
            }
        }
        back();
    }

    private UIPage getUIPageByCommand(String input) {
        for(Map.Entry<String,UIPage> submenu : submenus.entrySet()) {
            matcher = getMatcher(submenu.getKey(), input);
            if(matcher != null)
                return submenu.getValue();
        }
        return null;
    }

    protected void back() {
        MenuManagement.back();
    }

    @Override
    protected void show() {
        System.out.println(name);
    }

    protected boolean check() {
        return true;
    }

    protected void showHelp() {
        System.out.println("login\n" +
                "logout\n" +
                "view cart\n" +
                "help\n" +
                "back");
    }

    @Override
    protected String getType() {
        return "Menu";
    }
}