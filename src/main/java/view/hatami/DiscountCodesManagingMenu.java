package view.hatami;

import controller.CodedDiscountController;
import model.CodedDiscount;
import view.bagheri.Menu;
import view.nedaei.personalinfopanel.PersonalInfoPanel;

public class DiscountCodesManagingMenu extends ManagingMenu {
    public DiscountCodesManagingMenu() {
        super("discount codes managing menu");
        this.printer = new CodedDiscountController();
        submenus.put("view discount code (\\S+)", createOneItemDisplayPanel("discount code:", printer));
        submenus.put("edit discount code (\\S+)", )
    }

    private PersonalInfoPanel getDiscountEditorPanel(CodedDiscount codedDiscount) {
    }

    protected void showHelp() {

    }
}
