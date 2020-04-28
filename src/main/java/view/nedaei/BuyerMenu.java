package view.nedaei;

import controller.Controller;
import view.*;
import view.nedaei.personalinfopanel.PersonalInfoPanel;

public class BuyerMenu extends UserMenu {
    private BuyerMenu instance;

    private BuyerMenu() {
        super("buyer page", null);
        this.submenus.put("view personal info", PersonalInfoPanel.getInstance());
        this.submenus.put("view cart", new CartManagingMenu());
        this.submenus.put("purchase", new PurchaseMenu(this));
        this.submenus.put("view orders", new OrdersManagingMenu(this));
        this.submenus.put("view balance", createViewBalancePanel());
        this.submenus.put("view discount codes", createViewDiscountCodesPanel());
        this.submenus.put("help", createShowHelpPanel());
    }

    public BuyerMenu getInstance() {
        if (instance == null) {
            instance = new BuyerMenu();
        }
        return instance;
    }

    private Panel createViewBalancePanel() {
        return new Panel("view balance panel") {

            @Override
            public void execute() {
                System.out.println(Controller.getInstance().getActiveUser().getBalance());
            }

        };
    }

    private Panel createViewDiscountCodesPanel() {
        return new Panel("view discount codes panel") {

            @Override
            public void execute() {
                System.out.println(Controller.getInstance().getActiveUser().getListOfCodedDiscounts());
            }

        };
    }

    @Override
    protected Panel createShowHelpPanel() {
        return new Panel("show help panel") {

            @Override
            public void execute() {
                System.out.println("view personal info\n" +
                        "view cart\n" +
                        "purchase\n" +
                        "view orders\n" +
                        "view balance\n" +
                        "view discount codes");
            }

        };
    }
}
