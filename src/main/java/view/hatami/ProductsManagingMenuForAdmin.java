package view.hatami;

import controller.Deleter;
import controller.ProductController;

public class ProductsManagingMenuForAdmin extends ManagingMenu{

    protected ProductsManagingMenuForAdmin() {
        super("manage products");
        this.printer = new ProductController();
        submenus.put("remove (//S+)", createItemDeleterPanel("delete product", (Deleter) printer));
    }
}
