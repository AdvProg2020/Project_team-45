package view.bagheri;

import model.Category;

public class AllProductsMenu extends ProductSearchMenu {
    private static AllProductsMenu instance = new AllProductsMenu();
    private Category currentCategory;
    private Category activeCategory;


    private AllProductsMenu() {
        super("allProductsMenu", null);
    }

    public static AllProductsMenu getInstance() {
        return instance;
    }

    protected void showAllCategories(){

    }

    protected void showHelp() {

    }
}