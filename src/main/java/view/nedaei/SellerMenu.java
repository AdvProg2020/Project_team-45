package view.nedaei;

import view.bagheri.Menu;
import view.bagheri.Panel;
import view.hatami.RemoveProductPanel;
import view.nedaei.personalinfopanel.PersonalInfoPanel;

public class SellerMenu extends UserMenu {
    private static SellerMenu instance;

    private SellerMenu() {
        super("Seller Page");
        this.submenus.put("view personal info", PersonalInfoPanel.getInstance());
        this.submenus.put("view company information", createViewCompanyInfoPanel());
        this.submenus.put("view sales history", createViewSalesHistoryPanel());
        this.submenus.put("manage products", SellerProductsManagingMenu.getInstance());
        this.submenus.put("add product", CreateProductPanel.getInstance());
        this.submenus.put("remove product (\\d+)", new RemoveProductPanel()); // TODO: hatami
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

//    protected void showHelp() {
//        System.out.println("view personal info\n" +
//                "view company information\n" +
//                "view sales history\n" +
//                "manage products\n" +
//                "add product\n" +
//                "remove product [productId]\n" +
//                "show categories\n" +
//                "view offs\n" +
//                "view balance\n");
//    }

}
