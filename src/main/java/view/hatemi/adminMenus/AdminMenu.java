package view.hatemi.adminMenus;

import controller.CodedDiscountController;
import controller.userControllers.UserController;
import view.ManagingMenu;
import view.UserMenu;

public class AdminMenu extends UserMenu {
    private static final AdminMenu instance = new AdminMenu();
    private AdminMenu() {
        super("Admin Page");
        submenus.put("manage users", new UsersManagingMenu());
        submenus.put("manage all products", new ProductsManagingMenuForAdmin());
        submenus.put("create discount code", ManagingMenu.createItemCreatorPanel("create discount code", CodedDiscountController.getInstance()));
        submenus.put("view discount codes", new DiscountCodesManagingMenu());
        submenus.put("manage requests", new RequestsManagingMenu());
        submenus.put("manage categories", new CategoriesManagingMenu());
    }

    public static AdminMenu getInstance() {
        return instance;
    }

    @Override
    public void execute() {
        if (!UserController.isAdminLoggedIn()) {
            back();
            return;
        }
        super.execute();
    }

    @Override
    protected void showHelp() {
        super.showHelp();
    }
}