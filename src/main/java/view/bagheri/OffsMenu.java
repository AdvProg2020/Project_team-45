package view.bagheri;

import model.Product;

public class OffsMenu extends ProductSearchMenu {
    private static OffsMenu instance = new OffsMenu();
    private java.util.ArrayList<Product> inOffProductsList;


    private OffsMenu() {
        super("offsMenu", null);
    }

    public static OffsMenu getInstance() {
        return instance;
    }

    private void showInOffProducts() {
    }

    protected void showHelp() {

    }
}
