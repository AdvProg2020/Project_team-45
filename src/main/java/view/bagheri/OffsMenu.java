package view.bagheri;

import java.util.ArrayList;

public class OffsMenu extends ProductSearchMenu {
    private static final OffsMenu instance = new OffsMenu();


    private OffsMenu() {
        super("offsMenu");
    }

    public static OffsMenu getInstance() {
        return instance;
    }

    @Override
    protected boolean check() {

        return true;
    }

    @Override
    protected Panel creatViewCategoriesPanel() {
        return new Panel("viewCategoriesPanel") {
            @Override
            public void execute() {
                show();
            }

            @Override
            protected void show() {
                ArrayList<String> submenus = categoryController.getActiveCategoryDiscountedSubmenus();
                if (submenus.isEmpty()) {
                    System.out.println("This category has no subcategories");
                }
                for (String submenu : submenus) {
                    System.out.println(submenu);
                }
            }
        };
    }

    @Override
    protected Menu getCategoryMenu() {
        return OffsMenu.getInstance();
    }

    @Override
    protected void showProducts() {
        ArrayList<String> categoryProducts = categoryController.getActiveCategoryDiscountedProducts();
        for (String product : categoryProducts) {
            System.out.println(product);
        }
    }
}