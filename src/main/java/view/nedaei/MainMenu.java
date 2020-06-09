package view.nedaei;

import controller.userControllers.UserController;
import view.Login_RegisterPanel;
import view.Menu;
import view.bagheri.AllProductsMenu;
import view.bagheri.OffsMenu;
import view.hatemi.adminMenus.AdminMenu;
import view.nedaei.buyerMenus.BuyerMenu;
import view.nedaei.sellerMenus.SellerMenu;

public class MainMenu extends Menu {
    private static MainMenu instance;

    private MainMenu() {
        super("main menu");
        this.submenus.put("user page", Login_RegisterPanel.getInstance());
        this.submenus.put("products", AllProductsMenu.getInstance());
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
        super.showHelp();
        System.out.println();
    }

    public void updateSubmenus() { // TODO: bagheri
        if (!UserController.isLoggedIn()) {
            submenus.put("user page", Login_RegisterPanel.getInstance());
            return;
        }

        String role = UserController.getActiveUser().getRole();
        if (role.equals("admin")) {
            submenus.put("user page", AdminMenu.getInstance());
        } else if (role.equals("buyer")) {
            submenus.put("user page", BuyerMenu.getInstance());
        } else {
            submenus.put("user page", SellerMenu.getInstance());
        }
    }

}