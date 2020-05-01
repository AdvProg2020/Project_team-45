package view.nedaei.buyermenu;

import view.bagheri.Menu;

public class PurchaseMenu extends Menu {
    private static PurchaseMenu instance;

    private PurchaseMenu() {
        super("purchase page", null);
    }

    public static PurchaseMenu getInstance() {
        if (instance == null) {
            instance = new PurchaseMenu();
        }
        return instance;
    }

}
