package view.nedaei.buyermenu;

import controller.userControllers.BuyerController;
import view.bagheri.Panel;
import view.nedaei.UserMenu;

public class BuyerMenu extends UserMenu {
    private static BuyerMenu instance;

    private BuyerMenu() {
        super("buyer page");
        this.submenus.put("view cart", CartManagingMenu.getInstance());
        this.submenus.put("purchase", PurchasePanel.getInstance());
        this.submenus.put("view orders", OrdersManagingMenu.getInstance());
        this.submenus.put("view balance", createViewBalancePanel());
        this.submenus.put("view discount codes", createViewDiscountCodesPanel());
    }

    public static BuyerMenu getInstance() {
        if (instance == null) {
            instance = new BuyerMenu();
        }
        return instance;
    }

    private Panel createViewBalancePanel() {
        return new Panel("view balance panel") {

            @Override
            public void execute() {
                System.out.println(BuyerController.getInstance().getBuyerBalance());
            }

        };
    }

    private Panel createViewDiscountCodesPanel() {
        return new Panel("view discount codes panel") {

            @Override
            public void execute() {
                System.out.println(BuyerController.getInstance().getBuyerCodedDiscountsDisplay());
            }

        };
    }

    @Override
    protected void showHelp() {
        super.showHelp();
        System.out.println();
    }
}
