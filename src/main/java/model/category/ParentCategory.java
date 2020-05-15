package model.category;

import model.Product;

import java.util.ArrayList;

public class ParentCategory extends Category{
    private final ArrayList<Category> subcategories;


    public ParentCategory(String name, Category parent) {
        super(name, parent);
        this.subcategories = new ArrayList<Category>();
    }

    public ArrayList<Category> getSubcategories() {
        return subcategories;
    }

    public void addSubcategory(Category newCategory) {

    }

    public boolean removeSubcategory(Category subcategory) {
        return false;
    }

    @Override
    public ArrayList<Product> getProductsList() {
        ArrayList<Product> allProducts = new ArrayList<Product>();
        for (Category subcategory : subcategories) {
            allProducts.addAll(subcategory.getProductsList());
        }
        return allProducts;
    }

    @Override
    public ArrayList<Product> getDiscountedProductsList() {
        ArrayList<Product> discountedProductsList = new ArrayList<Product>();
        for (Category subcategory : subcategories) {
            discountedProductsList.addAll(subcategory.getDiscountedProductsList());
        }
        return discountedProductsList;
    }

    @Override
    public boolean isDiscounted() {
        for (Category subcategory : subcategories) {
            if (subcategory.isDiscounted()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getType() {
        return "ParentCategory";
    }
}
