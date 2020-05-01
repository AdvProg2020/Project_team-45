package view.nedaei;

import view.bagheri.Menu;
import view.hatami.ManagingMenu;

public class OffsManagingMenu extends ManagingMenu {
    private static OffsManagingMenu instance;

    private OffsManagingMenu() {
        super("offs managing page", null);
    }

    public static OffsManagingMenu getInstance() {
        if (instance == null) {
            instance = new OffsManagingMenu();
        }
        return instance;
    }

}
