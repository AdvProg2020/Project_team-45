package view.hatami;

import controller.CodedDiscountController;
import controller.userControllers.UserController;
import view.nedaei.UserMenu;

public class AdminMenu extends UserMenu {
    private static final AdminMenu instance = new AdminMenu();
    private AdminMenu() {
        super("Admin Page");
        submenus.put("manage users", new UsersManagingMenu());
        submenus.put("manage all products", new ProductsManagingMenuForAdmin());
        submenus.put("create discount code", ManagingMenu.createItemCreatorPanel("create discount code", CodedDiscountController.getInstance()));
        submenus.put("view discount code", new DiscountCodesManagingMenu());
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
        }
        super.execute();
    }

    @Override
    protected void showHelp() {
        super.showHelp();
    }
}
