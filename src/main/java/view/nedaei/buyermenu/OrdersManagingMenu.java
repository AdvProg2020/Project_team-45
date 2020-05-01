package view.nedaei.buyermenu;

import view.hatami.ManagingMenu;

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
