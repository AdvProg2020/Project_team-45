package view.nedaei.sellermenu.offsmanagingmenu;

import controller.Controller;
import view.bagheri.Panel;
import view.hatami.ManagingMenu;

public class OffsManagingMenu extends ManagingMenu {
    private static OffsManagingMenu instance;

    private OffsManagingMenu() {
        super("offs managing page", null);
        this.submenus.put("view (\\w+)", createViewOffByIdPanel());
        this.submenus.put("edit (\\w+)", EditOffPanel.getInstance());
        this.submenus.put("add off", CreateOffPanel.getInstance());
        this.submenus.put("help", createHelpPanel());
    }

    public static OffsManagingMenu getInstance() {
        if (instance == null) {
            instance = new OffsManagingMenu();
        }
        return instance;
    }

    private Panel createViewOffByIdPanel() {
        return new Panel("view off by id panel") {

            @Override
            protected void execute() {
                System.out.println(Controller.getInstance().getSellerOffById(matcher.group(1)));
            }

        };
    }

    private Panel createHelpPanel() {
        return new Panel("create help panel") {

            @Override
            protected void execute() {
                System.out.println("");
            }

        };
    }

}
