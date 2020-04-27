package view.nedaei;

import view.*;
import view.nedaei.CreateProductPanel;
import view.nedaei.OffsManagingMenu;
import view.nedaei.SellerProductsManagingMenu;

public class SellerMenu extends UserMenu {

    public SellerMenu(Menu parent) {
        super("Seller Page", parent);
        this.submenus.put("view personal info", new PersonalInfoPanel());
        this.submenus.put("view company information", new Panel("view company information panel") {

            @Override
            public void execute() {
                System.out.println(controller.getActiveUser().getCompany());
            }

        });
        this.submenus.put("view sales history", new Panel("view sales history panel") {

            @Override
            public void execute() {
                System.out.println(controller.getActiveUser().getListOfSellLogs());
            }

        });
        this.submenus.put("manage products", new SellerProductsManagingMenu());
        this.submenus.put("add product", new CreateProductPanel());
        this.submenus.put("remove product \\d+", new RemoveProductPanel());
        this.submenus.put("show categories", new Panel("show categories panel") {

            @Override
            public void execute() {
                System.out.println(controller.getAllCategoriesList());
            }

        });
        this.submenus.put("view offs", new OffsManagingMenu());
        this.submenus.put("view balance", new Panel("view balance panel") {

            @Override
            public void execute() {
                System.out.println(controller.getActiveUser().getBalance());
            }

        });
    }

    protected void showHelp() {
        System.out.println("view personal info\n" +
                "view company information\n" +
                "view sales history\n" +
                "manage products\n" +
                "add product\n" +
                "remove product [productId]\n" +
                "show categories\n" +
                "view offs\n" +
                "view balance\n");
    }
}
