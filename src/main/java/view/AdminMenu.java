package view;

public class AdminMenu extends UserMenu {
    protected AdminMenu(String name, Menu parent) {
        super(name, parent);
    }

    private Panel getProductsManagingPanel() {
        return new Panel("manage products") {

        };
    }

    private Panel getCreateDiscountCodePanel() {
        return null;
    }
}
