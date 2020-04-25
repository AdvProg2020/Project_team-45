package view;

public class AdminMenu extends UserMenu {
    protected AdminMenu(Menu parent) {
        super("Admin Page", parent);
    }

    private Panel getProductsManagingPanel() {
        return new Panel("manage products") {

        };
    }

    private Panel getCreateDiscountCodePanel() {
        return null;
    }
}
