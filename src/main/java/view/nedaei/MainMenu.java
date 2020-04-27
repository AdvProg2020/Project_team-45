package view.nedaei;

import view.*;

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
            submenus.put("user page", new AdminMenu(this));
        } else if (role.equals("buyer")) {
            submenus.put("user page", new BuyerMenu(this));
        } else {
            submenus.put("user page", new SellerMenu(this));
        }
        return;
    }


}
