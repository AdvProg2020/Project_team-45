package view.userMenus.adminMenus;

import controller.CodedDiscountController;
import controller.managers.Editor;
import controller.managers.Printer;
import controller.userControllers.UserController;
import view.ManagingMenu;

public class DiscountCodesManagingMenu extends ManagingMenu {
    public DiscountCodesManagingMenu() {
        super("discount codes managing menu");
        this.manager = CodedDiscountController.getInstance();
        this.managingObject = "Discount codes";
        submenus.put("view discount code (\\S+)", createOneItemDisplayPanel("discount code:", (Printer) manager));
        submenus.put("edit discount code (\\S+)", createItemEditorPanel("edit discount code", (Editor) manager));
    }

    @Override
    public void execute() {
        if (!UserController.isAdminLoggedIn()) {
            back();
            return;
        }
        super.execute();
    }

    @Override
    protected void showHelp() {
        super.showHelp();
    }
}
