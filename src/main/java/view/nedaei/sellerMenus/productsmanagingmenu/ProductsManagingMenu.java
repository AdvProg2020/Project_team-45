package view.nedaei.sellerMenus.productsmanagingmenu;

import controller.SellerProductsController;
import controller.managers.Printer;
import controller.userControllers.SellerController;
import controller.userControllers.UserController;
import view.ManagingMenu;
import view.Panel;

public class ProductsManagingMenu extends ManagingMenu {
    private static ProductsManagingMenu instance;

    private ProductsManagingMenu() {
        super("seller products managing page");
        this.manager = SellerProductsController.getInstance();
        this.managingObject = "products";
        this.submenus.put("view (\\w+)", createOneItemDisplayPanel("view product", (Printer) manager));
        this.submenus.put("view buyers (\\w+)", createViewBuyersPanel());
        this.submenus.put("edit (\\w+)", EditProductPanel.getInstance());
    }

    public static ProductsManagingMenu getInstance() {
        if (instance == null) {
            instance = new ProductsManagingMenu();
        }
        return instance;
    }

    private Panel createViewProductByIdPanel() {
        return new Panel("view product by id panel") {

            @Override
            public void execute() {
                String productDisplay = SellerController.getInstance().getSellerProductDisplayById(matcher.group(1));
                System.out.println(productDisplay == null? "id not found!" : productDisplay);
            }

        };
    }

    private Panel createViewBuyersPanel() {
        return new Panel("view buyers panel") {

            @Override
            public void execute() {
                String productAllBuyersDisplay = SellerController.getInstance()
                        .getSellerProductAllBuyersDisplayById(matcher.group(1));
                System.out.println(productAllBuyersDisplay == null? "id not found!" : productAllBuyersDisplay);
            }

        };
    }

    @Override
    public void execute() {
        if (!UserController.isSellerLoggedIn()) {
            back();
            return;
        }
        super.execute();
    }

    @Override
    protected void showHelp() {
        super.showHelp();
        System.out.println();
    }
}
