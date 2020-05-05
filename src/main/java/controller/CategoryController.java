package controller;

import model.Category;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryController implements Editor{
    private static CategoryController instance = new CategoryController();

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
}
