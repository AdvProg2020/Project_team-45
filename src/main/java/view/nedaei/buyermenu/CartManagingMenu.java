package view.nedaei.buyermenu;

import view.hatami.ManagingMenu;

public class CartManagingMenu extends ManagingMenu {
    private static CartManagingMenu instance;

    private CartManagingMenu() {
        super("cart managing page", null);
//        this.put();
//        this.put();
//        this.put();
//        this.put();
//        this.put();
//        this.put();
    }

    public static CartManagingMenu getInstance() {
        if (instance == null) {
            instance = new CartManagingMenu();
        }
        return instance;
    }

}
