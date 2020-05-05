package controller;

import model.Category;

import java.util.ArrayList;

public class CategoryController {
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

}
