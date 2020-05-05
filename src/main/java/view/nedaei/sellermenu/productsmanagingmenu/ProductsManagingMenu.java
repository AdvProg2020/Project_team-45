package view.nedaei.sellermenu.productsmanagingmenu;

import controller.Controller;
import view.bagheri.Panel;
import view.hatami.ManagingMenu;

public class ProductsManagingMenu extends ManagingMenu {
    private static ProductsManagingMenu instance;

    private ProductsManagingMenu() {
        super("seller products managing page", null);
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
            protected void execute() {
                String productDisplay = Controller.getInstance().getSellerProductById(matcher.group(1));
                System.out.println(productDisplay == null? "not found!" : productDisplay);
            }

        };
    }

    private Panel createViewBuyersPanel() {
        return new Panel("view buyers panel") {

            @Override
            protected void execute() {
                System.out.println(Controller.getInstance().getSellerProductById(matcher.group(1))
                        .getSellerInfoForProductById(Controller.getInstance().getActiveUserId()).getAllBuyers());
            }

        };
    }

    @Override
    protected void showHelp() {
        System.out.println("");
    }
}