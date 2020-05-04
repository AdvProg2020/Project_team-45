package view.nedaei.sellermenu;

import view.bagheri.Panel;
import view.hatami.RemoveProductPanel;
import view.nedaei.UserMenu;
import view.nedaei.sellermenu.offsmanagingmenu.OffsManagingMenu;
import view.nedaei.sellermenu.productsmanagingmenu.CreateProductPanel;
import view.nedaei.sellermenu.productsmanagingmenu.ProductsManagingMenu;

public class SellerMenu extends UserMenu {
    private static SellerMenu instance;

    private SellerMenu() {
        super("Seller Page");
        this.submenus.put("view company information", createViewCompanyInfoPanel());
        this.submenus.put("view sales history", createViewSalesHistoryPanel());
        this.submenus.put("manage products", ProductsManagingMenu.getInstance());
        this.submenus.put("add product", CreateProductPanel.getInstance());
        this.submenus.put("remove product (\\w+)", RemoveProductPanel.getInstance()); // TODO: hatami
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
                System.out.println(controller.getActiveUser().getCompany());
            }

        };
    }

    private Panel createViewSalesHistoryPanel() {
        return new Panel("view sales history panel") {

            @Override
            public void execute() {
                System.out.println(controller.getActiveUser().getListOfSellLogs());
            }

        };
    }

    private Panel createShowCategoriesPanel() {
        return new Panel("show categories panel") {

            @Override
            public void execute() {
                System.out.println(controller.getAllCategoriesList());
            }

        };
    }

    private Panel createViewBalancePanel() {
        return new Panel("view balance panel") {

            @Override
            public void execute() {
                System.out.println(controller.getActiveUser().getBalance());
            }

        };
    }

    @Override
    protected void showHelp() {
        System.out.println("");
    }
}
