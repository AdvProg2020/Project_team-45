package controller;

import model.category.Category;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryController implements Editor{
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

    public String printAllInList() {
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

    public void changeIsOffMenuToTrue() { //name?!
        isOffMenu = true;
    }

    public void changeIsOffMenuToFalse() { //name?!
        isOffMenu = false;
    }

    public boolean setActiveCategoryByName(String name) {
        return true;
    }

    public void backCategory() {
        if (activeCategory != null) {

        }
    }
}
