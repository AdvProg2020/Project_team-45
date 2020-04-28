package view.nedaei;

import view.hatami.ManagingMenu;
import view.bagheri.Menu;

public class OrdersManagingMenu extends ManagingMenu {

    public OrdersManagingMenu(Menu parent) {
        super("order managing panel", parent);
    }

    protected void showHelp() {
        System.out.println("show order [orderId]\n" +
                "rate [productId] [1-5]");
    }
}
