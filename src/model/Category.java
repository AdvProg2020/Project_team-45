package model;

import java.util.ArrayList;

public class Category {
    private String name;
    private Category parent;
    private ArrayList<String> specialFeatures;
    private ArrayList<Category> subCategory;
    private ArrayList<Product> productList;

    public Category(String name, Category parent, ArrayList<String> specialFeatures, ArrayList<Category> subCategory, ArrayList<Product> productList) {
        this.name = name;
        this.parent = parent;

        this.subCategory = subCategory;
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public Category getParent() {
        return parent;
    }

    public ArrayList<String> getSpecialFeatures() {
        return specialFeatures;
    }

    public ArrayList<Category> getSubCategory() {
        return subCategory;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSpecialFeature(String newSpecialFeature) {

    }

    public void addSubCategory(Category newCategory) {

    }

    public void addProduct(Product newProduct) {

    }

    public boolean removeSpecialFeature(String specialFeature) {
        return false;
    }

    public boolean removeSubCategory(Category subCategory) {
        return false;
    }

    public boolean removeProduct(Product removeProduct) {
        return false;
    }
}