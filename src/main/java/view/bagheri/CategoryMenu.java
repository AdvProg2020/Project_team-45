package view.bagheri;

import java.util.ArrayList;

public class CategoryMenu extends ProductSearchMenu {
    private static final CategoryMenu instance = new CategoryMenu();


    private CategoryMenu() {
        super("categoryMenu");
    }

    public static CategoryMenu getInstance() {
        return instance;
    }

    @Override
    protected boolean check() {
        categoryController.changeIsOffMenuToFalse();
        return super.check();
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
                ArrayList<String> submenus = categoryController.getActiveCategorySubmenus();
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
        return CategoryMenu.getInstance();
    }

    @Override
    protected void showProducts() {
        ArrayList<String> categoryProducts = categoryController.getActiveCategoryProducts();
        for (String product : categoryProducts) {
            System.out.println(product);
        }
    }
}