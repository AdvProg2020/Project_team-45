package controller;

import controller.managers.Creator;
import controller.managers.Editor;
import model.category.Category;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryController implements Editor, Creator {
    private static final CategoryController instance = new CategoryController();
    private Category activeCategory;
    private boolean isOffMenu;

    private CategoryController() {
    }

    public static CategoryController getInstance() {
        return instance;
    }

    public void removeCategory(String name) {
    }

    public Category getCategoryByName(String name) {
        return null;
    }

    public void editCategory(String name, Category category) {}

    public Category createCategory(String name) {
        return null;
    }

    public void addFeatureToCategory(Category category, String feature) {
    }

    public void removeFeatureFromCategory(Category category, String feature) {
    }

    public void setParentOfCategory(Category category, String parentName) {}

    public HashMap<String, String> getAvailableFields() {
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
        return null;
    }

    public ArrayList<String> getSubcategoriesByNameCategory(String nameCategory) {
        return null;
    }

    public ArrayList<String> getActiveCategoryProducts() {
        return null;
    }

    public ArrayList<String> getActiveCategoryDiscountedProducts() {
        return null;
    }

    public ArrayList<String> getActiveCategorySubmenus() {
        return null;
    }

    public ArrayList<String> getActiveCategoryDiscountedSubmenus() {
        return null;
    }

    public void a () { //name?!
        isOffMenu = true;
    }

    public void b () { //name?!
        isOffMenu = false;
    }

    public HashMap<String, String> getNecessaryFields() {
        // TODO : hatami
        return null;
    }
}
