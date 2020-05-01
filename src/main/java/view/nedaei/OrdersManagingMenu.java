package view.nedaei;

import view.hatami.ManagingMenu;
import view.bagheri.Menu;

public class OrdersManagingMenu extends ManagingMenu {
    private static OrdersManagingMenu instance;

    public OrdersManagingMenu() {
        super("order managing panel", null);
    }

    public static OrdersManagingMenu getInstance() {
        if (instance == null) {
            instance = new OrdersManagingMenu();
        }
        return instance;
    }
}
