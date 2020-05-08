package view.hatami;

import controller.CodedDiscountController;
import controller.managers.Editor;
import controller.managers.Printer;

public class DiscountCodesManagingMenu extends ManagingMenu {
    public DiscountCodesManagingMenu() {
        super("discount codes managing menu");
        this.manager = CodedDiscountController.getInstance();
        submenus.put("view discount code (\\S+)", createOneItemDisplayPanel("discount code:", (Printer) manager));
        submenus.put("edit discount code (\\S+)", createItemEditorPanel("edit discount code", (Editor) manager));
    }

    protected void showHelp() {

    }
}
