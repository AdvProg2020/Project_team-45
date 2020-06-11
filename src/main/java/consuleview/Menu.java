package consuleview;

import controller.userControllers.UserController;
import consuleview.nedaei.MainMenu;
import consuleview.nedaei.buyerMenus.CartManagingMenu;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu extends UIPage {
    protected HashMap<String, UIPage> submenus;
    protected Menu parent;
    protected boolean needBeLogin;

    protected Menu(String name) {
        super(name);
        this.parent = null;
        this.submenus = new HashMap<>();
        submenus.put("login", Login_RegisterPanel.getInstance());
        submenus.put("view cart", CartManagingMenu.getInstance());
        submenus.put("help", createHelpPanel());
    }

    private Panel createHelpPanel() {
        return new Panel("help") {
            @Override
            public void execute() {
                showHelp();
            }
        };
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
                        MenuManagement.next(nextMenu);
                        return;
                    }
                }
                nextUIPage.execute();
            } else if (input.equals("logout")) {
                if (logout() && needBeLogin)
                    break;
            }
            else if(input.equals("exit")) {
                MenuManagement.exit();
                return;
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

    private boolean logout() {
        UserController userController = UserController.getInstance();
        if (userController.logout()) {
            MainMenu.getInstance().updateSubmenus();
            System.out.println("You logout successfully");
            return true;
        }
        System.out.println("You are not logged in");
        return false;
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
                "back\n" +
                "exit");
    }

    @Override
    protected String getType() {
        return "Menu";
    }
}