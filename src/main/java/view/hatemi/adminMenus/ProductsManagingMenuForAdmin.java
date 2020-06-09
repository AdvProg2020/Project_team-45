package view.hatemi.adminMenus;

import controller.ProductController;
import controller.managers.Deleter;
import controller.userControllers.UserController;
import view.ManagingMenu;

public class ProductsManagingMenuForAdmin extends ManagingMenu {

    protected ProductsManagingMenuForAdmin() {
        super("manage products");
        this.manager = ProductController.getInstance();
        this.managingObject = "Products";
        submenus.put("remove (\\S+)", createItemDeleterPanel("delete product", (Deleter) manager));
    }

    @Override
    public void execute() {
        if (!UserController.isAdminLoggedIn()) {
            back();
            return;
        }
        super.execute();
    }
}
