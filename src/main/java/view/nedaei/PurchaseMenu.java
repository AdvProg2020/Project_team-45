package view.nedaei;

import view.bagheri.Menu;
import view.bagheri.Panel;

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
