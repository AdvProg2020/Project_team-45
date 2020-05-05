package view.hatami;

import view.bagheri.Menu;
import view.bagheri.Panel;
import view.nedaei.UserMenu;

public class AdminMenu extends UserMenu {
    protected AdminMenu() {
        super("Admin Page");
        submenus.put("manage users", new UsersManagingMenu());
        submenus.put("manage all products", new ProductsManagingMenuForAdmin());
        submenus.put("create discount code", getCreateDiscountCodePanel());
        submenus.put("view discount code", new DiscountCodesManagingMenu());
        submenus.put("manage requests", new RequestsManagingMenu());
        submenus.put("manage categories", new CategoriesManagingMenu());
    }


    private Panel getCreateDiscountCodePanel() {
        // TODO : hatami
        return null;
    }

    protected void showHelp() {

    }
}
