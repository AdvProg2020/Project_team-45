package view;

public class BuyerMenu extends UserMenu {

    public BuyerMenu(Menu parent) {
        super("buyer page", parent);
        this.submenus.put("view personal info", new PersonalInfoPanel());
        this.submenus.put("view cart", new CartManagingMenu(this));
        this.submenus.put("purchase", new PurchaseMenu(this));
        this.submenus.put("view orders", new OrdersManagingMenu());
        this.submenus.put("view balance", new Panel("view balance panel") {

            @Override
            public void execute() {
                System.out.println(controller.getActiveUser().getBalance());
            }

        });
        this.submenus.put("view discount codes", new Panel("view discount code panel") {

            @Override
            public void execute() {
                System.out.println(controller.getActiveUser().getListOfCodedDiscounts());
            }

        });
    }

}
