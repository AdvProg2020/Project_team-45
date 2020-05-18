package view.nedaei.sellermenu;

import controller.CategoryController;
import controller.userControllers.SellerController;
import view.bagheri.Panel;
import view.nedaei.UserMenu;
import view.nedaei.sellermenu.offsmanagingmenu.OffsManagingMenu;
import view.nedaei.sellermenu.productsmanagingmenu.AddProductPanel;
import view.nedaei.sellermenu.productsmanagingmenu.ProductsManagingMenu;

public class SellerMenu extends UserMenu {
    private static SellerMenu instance;

    private SellerMenu() {
        super("Seller Page");
        this.submenus.put("view company information", createViewCompanyInfoPanel());
        this.submenus.put("view sales history", createViewSalesHistoryPanel());
        this.submenus.put("manage products", ProductsManagingMenu.getInstance());
        this.submenus.put("add product", AddProductPanel.getInstance());
        this.submenus.put("remove product (\\w+)", createRemoveProductPanel());
        this.submenus.put("show categories", createShowCategoriesPanel());
        this.submenus.put("view offs", OffsManagingMenu.getInstance());
        this.submenus.put("view balance", createViewBalancePanel());
    }

    public static SellerMenu getInstance() {
        if (instance == null) {
            instance = new SellerMenu();
        }
        return instance;
    }

    private Panel createViewCompanyInfoPanel() {
        return new Panel("view company information panel") {

            @Override
            public void execute() {
                System.out.println(SellerController.getInstance().getCompanyDisplayForSeller());
            }

        };
    }

    private Panel createViewSalesHistoryPanel() {
        return new Panel("view sales history panel") {

            @Override
            public void execute() {
                System.out.println(SellerController.getInstance().getSalesHistoryDisplayForSeller());
            }

        };
    }

    private Panel createRemoveProductPanel() {
        return new Panel("remove product panel") {

            @Override
            public void execute() {
                SellerController.getInstance().createRemoveProductRequest(matcher.group(1));
            }

        };
    }

    private Panel createShowCategoriesPanel() {
        return new Panel("show categories panel") {

            @Override
            public void execute() {
                System.out.println(CategoryController.getInstance().getMainCategories());
            }

        };
    }

    private Panel createViewBalancePanel() {
        return new Panel("view balance panel") {

            @Override
            public void execute() {
                System.out.println(SellerController.getInstance().getSellerBalance());
            }

        };
    }

    @Override
    protected void showHelp() {
        super.showHelp();
        System.out.println();
    }

}
