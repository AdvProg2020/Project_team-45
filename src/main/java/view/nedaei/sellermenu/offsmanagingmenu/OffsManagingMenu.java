package view.nedaei.sellermenu.offsmanagingmenu;

import controller.OffController;
import controller.userControllers.SellerController;
import view.bagheri.Panel;
import view.hatami.ManagingMenu;

public class OffsManagingMenu extends ManagingMenu {
    private static OffsManagingMenu instance;

    private OffsManagingMenu() {
        super("offs managing page");
        this.manager = OffController.getInstance();
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
                String offDisplay = SellerController.getInstance().getSellerOffDisplayById(matcher.group(1));
                System.out.println(offDisplay == null? "id not found!" : offDisplay);
            }

        };
    }

    @Override
    protected void showHelp() {
        super.showHelp();
        System.out.println();
    }


}
