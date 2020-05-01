package view.nedaei;

import view.bagheri.AllProductsSearchingMenu;
import view.bagheri.Login_RegisterPanel;
import view.bagheri.Menu;
import view.bagheri.OffsMenu;
import view.hatami.AdminMenu;

public class MainMenu extends Menu {

    public MainMenu() {
        super("main menu", null);
        this.submenus.put("user page", new Login_RegisterPanel());
        this.submenus.put("products page", new AllProductsSearchingMenu());
        this.submenus.put("offs page", new OffsMenu());
    }

    public void updateSubmenus() {
        if (!controller.isLoggedIn()) {
            return;
        }

        String role = controller.getRole();
        if (role.equals("admin")) {
            submenus.put("user page", new AdminMenu(this)); // TODO: hatami
        } else if (role.equals("buyer")) {
            submenus.put("user page", BuyerMenu.getInstance());
        } else {
            submenus.put("user page", SellerMenu.getInstance());
        }
    }


}
