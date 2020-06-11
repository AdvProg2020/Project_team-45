package consuleview.bagheri;

import controller.CategoryController;
import consuleview.Menu;
import consuleview.Panel;

import java.util.ArrayList;

public class AllProductsMenu extends Menu {
    private static final AllProductsMenu instance = new AllProductsMenu();
    private final CategoryController categoryController;


    private AllProductsMenu() {
        super("allProductMenu");
        categoryController = CategoryController.getInstance();
        submenus.put("view main categories", createViewMainCategoryPanel());
        submenus.put("view subcategories (.+)", createViewSubcategories());
        submenus.put("show category (.+)", CategoryMenu.getInstance());
    }

    public static AllProductsMenu getInstance() {
        return instance;
    }

    private Panel createViewMainCategoryPanel() {
        return new Panel("viewMainCategoryPanel") {
            @Override
            public void execute() {
                show();
            }
        };
    }

    private Panel createViewSubcategories() {
        return new Panel("viewSubcategories") {
            @Override
            public void execute() {
                ArrayList<String> subcategories = categoryController.getSubcategoriesByNameCategory(matcher.group(1));
                if (subcategories == null) {
                    System.out.println("There is no category with this name");
                    return;
                }
                if (subcategories.isEmpty()) {
                    System.out.println("This category has no subcategories");
                }
                for (String subcategory : subcategories) {
                    System.out.println(subcategory);
                }
            }
        };
    }

    @Override
    protected void show() {
        super.show();
        ArrayList<String> mainCategories = categoryController.getMainCategories();
        for (String mainCategory : mainCategories) {
            System.out.println(mainCategory);
        }
    }

    @Override
    protected void showHelp() {
        super.showHelp();
        System.out.println("view mainCategories\n" +
                "view subcategories [main_category_name]\n" +
                "show category [category_name]");
    }

    @Override
    public void execute() {
        categoryController.clearActiveCategory();
        super.execute();
    }
}
