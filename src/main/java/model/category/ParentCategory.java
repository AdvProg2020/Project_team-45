package model.category;

import model.Product;

import java.util.ArrayList;

public class ParentCategory extends Category{
    private ArrayList<Category> subCategory;


    public ParentCategory(String name, Category parent) {
        super(name, parent);
        this.subCategory = new ArrayList<Category>();
    }

    public ArrayList<Category> getSubCategory() {
        return subCategory;
    }

    public void addSubCategory(Category newCategory) {

    }

    public boolean removeSubCategory(Category subCategory) {
        return false;
    }
}
