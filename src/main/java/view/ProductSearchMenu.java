package view;


import controller.CategoryController;
import controller.FilteringController;
import controller.SortingController;

public abstract class ProductSearchMenu extends Menu {
    protected CategoryController categoryController;

    protected ProductSearchMenu(String name) {
        super(name);
        categoryController = CategoryController.getInstance();
        submenus.put("view categories", creatViewCategoriesPanel());
        submenus.put("show category (.+)", getCategoryMenu());
        submenus.put("filtering", FilteringPanel.getInstance());
        submenus.put("sorting", SortingPanel.getInstance());
        submenus.put("show products", creatShowProductsPanel());
        submenus.put("show product (\\w+)", ProductMenu.getInstance());
    }

    protected abstract Panel creatViewCategoriesPanel();

    protected abstract Menu getCategoryMenu();

    protected Panel creatShowProductsPanel() {
        return new Panel("showProductPanel") {
            @Override
            public void execute() {
                showProducts();
            }
        };
    }

    @Override
    public void execute() {
        super.execute();
        FilteringController.getInstance().clearFilters();
        SortingController.getInstance().disableCurrentSort();
    }

    @Override
    protected void back() {
        categoryController.backCategory();
        super.back();
    }

    @Override
    protected boolean check() {
        if (categoryController.setActiveCategoryByName(matcher.group(1)))
            return true;
        System.out.println("There is no category with this name!");
        return false;
    }

    @Override
    protected void show() {
        super.show();
        showProducts();
    }

    protected abstract void showProducts();

    @Override
    protected void showHelp() {
        super.showHelp();
        System.out.println("view categories\n" +
                "show category [category_name]\n" +
                "filtering\n" +
                "sorting\n" +
                "show products\n" +
                "show product [productId]");
    }
}