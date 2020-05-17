package view.nedaei.sellermenu.productsmanagingmenu;

import controller.SellerController;
import controller.UserController;
import view.bagheri.Panel;
import view.hatami.ManagingMenu;

public class ProductsManagingMenu extends ManagingMenu {
    private static ProductsManagingMenu instance;

    private ProductsManagingMenu() {
        super("seller products managing page");
        this.submenus.put("view (\\w+)", createViewProductByIdPanel());
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
    protected void showHelp() {
        System.out.println("");
    }
}
