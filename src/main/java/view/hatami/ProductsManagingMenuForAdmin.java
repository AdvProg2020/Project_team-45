package view.hatami;

import controller.ProductController;
import controller.managers.Deleter;
import controller.userControllers.UserController;

public class ProductsManagingMenuForAdmin extends ManagingMenu{

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
