package view.nedaei;

import view.bagheri.Menu;
import view.bagheri.Panel;

public class BuyerMenu extends UserMenu {
    private BuyerMenu instance;

    private BuyerMenu(Menu parent) {
        super("buyer page", parent);
        this.submenus.put("view personal info", new PersonalInfoPanel());
        this.submenus.put("view cart", new CartManagingMenu(this));
        this.submenus.put("purchase", new PurchaseMenu(this));
        this.submenus.put("view orders", new OrdersManagingMenu(this));
        this.submenus.put("view balance", createViewBalancePanel());
        this.submenus.put("view discount codes", createViewDiscountCodesPanel());
    }

    public BuyerMenu getInstance(Menu parent) {
        if (instance == null) {
            instance = new BuyerMenu(parent);
        }
        return instance;
    }

    private Panel createViewBalancePanel() {
        return new Panel("view balance panel") {

            @Override
            public void execute() {
                System.out.println(controller.getActiveUser().getBalance());
            }

        };
    }

    private Panel createViewDiscountCodesPanel() {
        return new Panel("view discount codes panel") {

            @Override
            public void execute() {
                System.out.println(controller.getActiveUser().getListOfCodedDiscounts());
            }

        };
    }

}
