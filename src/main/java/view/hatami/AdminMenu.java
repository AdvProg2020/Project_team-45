package view.hatami;

import controller.CodedDiscountController;
import view.nedaei.UserMenu;

public class AdminMenu extends UserMenu {

    protected AdminMenu() {
        super("Admin Page");
        submenus.put("manage users", new UsersManagingMenu());
        submenus.put("manage all products", new ProductsManagingMenuForAdmin());
        submenus.put("create discount code", ManagingMenu.createItemCreatorPanel("create discount code", CodedDiscountController.getInstance()));
        submenus.put("view discount code", new DiscountCodesManagingMenu());
        submenus.put("manage requests", new RequestsManagingMenu());
        submenus.put("manage categories", new CategoriesManagingMenu());
    }

    protected void showHelp() {

    }
}
