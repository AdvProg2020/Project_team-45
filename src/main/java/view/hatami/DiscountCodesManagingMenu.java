package view.hatami;

import controller.CodedDiscountController;
import controller.Editor;
import model.CodedDiscount;
import view.bagheri.Menu;
import view.nedaei.personalinfopanel.PersonalInfoPanel;

public class DiscountCodesManagingMenu extends ManagingMenu {
    public DiscountCodesManagingMenu() {
        super("discount codes managing menu");
        this.printer = new CodedDiscountController();
        submenus.put("view discount code (\\S+)", createOneItemDisplayPanel("discount code:", printer));
        submenus.put("edit discount code (\\S+)", createItemEditorPanel("edit discount code", (Editor) printer));
    }

    protected void showHelp() {

    }
}
