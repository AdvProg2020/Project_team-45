package view.nedaei.sellermenu.offsmanagingmenu;

import controller.UserController;
import view.bagheri.Panel;
import view.hatami.ManagingMenu;

public class OffsManagingMenu extends ManagingMenu {
    private static OffsManagingMenu instance;

    private OffsManagingMenu() {
        super("offs managing page");
        this.submenus.put("view (\\w+)", createViewOffByIdPanel());
        this.submenus.put("edit (\\w+)", EditOffPanel.getInstance());
        this.submenus.put("add off", CreateOffPanel.getInstance());
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
            public void execute() {
                String offDisplay = UserController.getInstance().getSellerOffDisplayById(matcher.group(1));
                System.out.println(offDisplay == null? "id not found!" : offDisplay);
            }

        };
    }

    @Override
    protected void showHelp() {
        System.out.println("");
    }
}
