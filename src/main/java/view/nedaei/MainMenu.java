package view.nedaei;

import view.bagheri.CategoryMenu;
import view.bagheri.Login_RegisterPanel;
import view.bagheri.Menu;
import view.bagheri.OffsMenu;
import view.hatami.AdminMenu;
import view.nedaei.buyermenu.BuyerMenu;
import view.nedaei.sellermenu.SellerMenu;

public class MainMenu extends Menu {

    public MainMenu() {
        super("main menu", null);
        this.submenus.put("user page", new Login_RegisterPanel());
        this.submenus.put("products page", new CategoryMenu());
        this.submenus.put("offs page", new OffsMenu());
    }

//    public void updateSubmenus() { // TODO: bagheri
//        if (!controller.isLoggedIn()) {
//            return;
//        }
//
//        String role = controller.getRole();
//        if (role.equals("admin")) {
//            submenus.put("user page", new AdminMenu(this)); // TODO: hatami
//        } else if (role.equals("buyer")) {
//            submenus.put("user page", BuyerMenu.getInstance());
//        } else {
//            submenus.put("user page", SellerMenu.getInstance());
//        }
//    }


}
