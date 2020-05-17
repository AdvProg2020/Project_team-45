package model.category;

import model.Product;

import java.util.ArrayList;

public abstract class Category {
    private String name;
    private ParentCategory parent;


    public Category(String name, ParentCategory parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public ParentCategory getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(ParentCategory parent) {
        this.parent = parent;
    }

    public abstract ArrayList<Product> getProductsList();

    public abstract ArrayList<Product> getInOffProductsList();

    public abstract boolean hasInOffProduct();

    public abstract String getType();

    public boolean isMain(){
        return parent == null;
    }
}