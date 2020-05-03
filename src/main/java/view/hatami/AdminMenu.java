package view.hatami;

import view.bagheri.Menu;
import view.bagheri.Panel;
import view.nedaei.UserMenu;

public class AdminMenu extends UserMenu {
    protected AdminMenu(Menu parent) {
        super("Admin Page", parent);
        submenus.put("manage users", new UsersManagingMenu(this));
        submenus.put("manage all products", getProductsManagingMenu());
        submenus.put("create discount code", getCreateDiscountCodePanel());
        submenus.put("view discount code", new DiscountCodesManagingMenu(this));
        submenus.put("manage requests", new RequestsManagingMenu(this));
        submenus.put("manage categories", new CategoriesManagingMenu(this));
    }

    private Menu getProductsManagingMenu() {
        Menu productsManagingMenu = new Menu("manage products", this) {};

    }

    private Panel getCreateDiscountCodePanel() {
        return null;
    }

    protected void showHelp() {

    }
}
