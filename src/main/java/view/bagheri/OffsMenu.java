package view.bagheri;

import model.Product;

public class OffsMenu extends ProductSearchMenu {
    private static OffsMenu instance = new OffsMenu();


    private OffsMenu() {
        super("offsMenu");
    }

    public static OffsMenu getInstance() {
        return instance;
    }

    @Override
    protected void show() {
        showProduct();
    }

    @Override
    protected Panel creatViewCategoriesPanel() {
        return new Panel("viewCategoriesPanel") {
            @Override
            public void execute() {
                //needs to be completed
            }
        };
    }

    @Override
    protected void showHelp() {
        super.showHelp();
        //needs to be completed
    }
}