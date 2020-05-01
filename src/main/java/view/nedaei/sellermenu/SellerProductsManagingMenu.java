package view.nedaei.sellermenu;

import controller.Controller;
import view.bagheri.Panel;
import view.hatami.ManagingMenu;
import view.nedaei.sellermenu.editproductpanel.EditProductPanel;

public class SellerProductsManagingMenu extends ManagingMenu {
    private static SellerProductsManagingMenu instance;

    private SellerProductsManagingMenu() {
        super("seller products managing page", null);
        this.submenus.put("view (\\w+)", createViewProductByIdPanel());
        this.submenus.put("view buyers (\\w+)", createViewBuyersPanel());
        this.submenus.put("edit (\\w+)", EditProductPanel.getInstance());
        this.submenus.put("help", createHelpPanel());
    }

    public static SellerProductsManagingMenu getInstance() {
        if (instance == null) {
            instance = new SellerProductsManagingMenu();
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

    private Panel createHelpPanel() {
        return new Panel("help panel") {

            @Override
            protected void execute() {
                System.out.println("");
            }

        };
    }

}
