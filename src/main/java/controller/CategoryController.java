package controller;

import controller.managers.Creator;
import controller.managers.Editor;
import model.Market;
import model.Product;
import model.category.Category;
import model.category.ParentCategory;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryController implements Editor, Creator {
    private static final CategoryController instance = new CategoryController();
    private final Market market;
    private Category activeCategory;
    private boolean isOffMenu;

    private CategoryController() {
        market = Market.getInstance();
        activeCategory = null;
    }

    public static CategoryController getInstance() {
        return instance;
    }

    public void removeCategory(String name) {
    }

    public Category getCategoryByName(String name) {
        return null;
    }

    public void editCategory(String name, Category category) {
    }

    public Category createCategory(String name) {
        return null;
    }

    public void addFeatureToCategory(Category category, String feature) {
    }

    public void removeFeatureFromCategory(Category category, String feature) {
    }

    public void setParentOfCategory(Category category, String parentName) {
    }

    public HashMap<String, String> getAvailableFieldsToEdit() {
        // TODO : hatami
        return null;
    }

    public void deleteItemById(String Id) {
        // TODO : hatami
    }

    public String getAllInListAsString() {
        // TODO : hatami
        return null;
    }

    public String printDetailedById(String Id) {
        // TODO : hatami
        return null;
    }


    public ArrayList<String> getMainCategories() {
        ArrayList<String> mainCategoriesName = new ArrayList<String>();
        ArrayList<Category> mainCategories = market.getMainCategories();
        for (Category mainCategory : mainCategories) {
            mainCategoriesName.add(mainCategory.getName());
        }
        return mainCategoriesName;
    }

    public ArrayList<String> getSubcategoriesByNameCategory(String categoryName) {
        ArrayList<String> subcategoriesName = new ArrayList<String>();
        Category mainCategory = market.getMainCategoryByName(categoryName);
        if (mainCategory == null) {
            return null;
        }
        if (mainCategory.getType().equals("ParentCategory")) {
            ArrayList<Category> subcategories = ((ParentCategory) mainCategory).getSubcategories();
            for (Category subcategory : subcategories) {
                subcategoriesName.add(subcategory.getName());
            }
        }
        return subcategoriesName;
    }

    public ArrayList<String> getActiveCategoryProducts() {
        ArrayList<String> output = new ArrayList<String>();
        ArrayList<Product> activeCategoryProducts = activeCategory.getProductsList();
        for (Product product : activeCategoryProducts) {
            // TODO
        }
        return output;
    }

    public ArrayList<String> getActiveCategoryDiscountedProducts() {
        ArrayList<String> output = new ArrayList<String>();
        ArrayList<Product> activeCategoryProducts;
        if (activeCategory == null) {
            activeCategoryProducts = market.getAllDiscountedProductsList();
        } else {
            activeCategoryProducts = activeCategory.getInOffProductsList();
        }
        for (Product product : activeCategoryProducts) {
            // TODO
        }
        return output;
    }

    public ArrayList<String> getActiveCategorySubcategories() {
        ArrayList<String> subcategoriesName = new ArrayList<String>();
        if (activeCategory.getType().equals("ParentCategory")) {
            ArrayList<Category> subcategories = ((ParentCategory) activeCategory).getSubcategories();
            for (Category subcategory : subcategories) {
                subcategoriesName.add(subcategory.getName());
            }
        }
        return subcategoriesName;
    }

    public ArrayList<String> getActiveCategoryDiscountedSubcategories() {
        ArrayList<String> subcategoriesName = new ArrayList<String>();
        if (activeCategory.getType().equals("ParentCategory")) {
            ArrayList<Category> subcategories = ((ParentCategory) activeCategory).getSubcategories();
            for (Category subcategory : subcategories) {
                if(subcategory.hasInOffProduct()) {
                    subcategoriesName.add(subcategory.getName());
                }
            }
        }
        return subcategoriesName;
    }

    public void changeIsOffMenuToTrue() { //name?!
        isOffMenu = true;
    }

    public void changeIsOffMenuToFalse() { //name?!
        isOffMenu = false;
    }

    public boolean setActiveCategoryByName(String name) {
        Category category = market.getCategoryByName(name);
        if (category != null && (category.getParent() == activeCategory || (activeCategory == null && category.getParent().getParent() == null))) {
            activeCategory = category;
            return true;
        }
        return false;
    }

    public void backCategory() {
        if (activeCategory != null) {
            activeCategory = activeCategory.getParent();
        }
    }


    public ArrayList<String> getNecessaryFieldsToCreate() {
        // TODO : hatami
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {
        //TODO : hatami
    }

    @Override
    public Category getItemById(String Id) {
        return market.getCategoryByName(Id);
    }
}
