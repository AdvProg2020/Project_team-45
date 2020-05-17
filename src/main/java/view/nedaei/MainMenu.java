package view.nedaei;

import controller.userControllers.UserController;
import view.bagheri.CategoryMenu;
import view.bagheri.Login_RegisterPanel;
import view.bagheri.Menu;
import view.bagheri.OffsMenu;
import view.hatami.AdminMenu;
import view.nedaei.buyermenu.BuyerMenu;
import view.nedaei.sellermenu.SellerMenu;

public class MainMenu extends Menu {
    private static MainMenu instance;

    private MainMenu() {
        super("main menu");
        this.submenus.put("user page", Login_RegisterPanel.getInstance());
        this.submenus.put("products page", CategoryMenu.getInstance());
        this.submenus.put("offs page", OffsMenu.getInstance());
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    @Override
    protected void showHelp() {
        System.out.println();
    }

    public void updateSubmenus() { // TODO: bagheri
        if (!UserController.isLoggedIn()) {
            submenus.put("user page", Login_RegisterPanel.getInstance());
            return;
        }

        String role = UserController.getActiveUser().getRole();
        if (role.equals("admin")) {
            submenus.put("user page", AdminMenu.getInstance()); // TODO: hatami
        } else if (role.equals("buyer")) {
            submenus.put("user page", BuyerMenu.getInstance());
        } else {
            submenus.put("user page", SellerMenu.getInstance());
        }
    }

}
