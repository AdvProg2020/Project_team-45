package model.category;

import model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ParentCategory extends Category{
    private final ArrayList<Category> subcategories;

    public ParentCategory(HashMap<String, String> filledFeatures) {
        super(filledFeatures);
        this.subcategories = new ArrayList<>();
    }

    public ArrayList<Category> getSubcategories() {
        return subcategories;
    }

    public void removeSubcategoryFromList(Category subcategory) {
        if (!subcategories.contains(subcategory))
            return;
        subcategories.remove(subcategory);
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
    public ArrayList<Product> getInOffProductsList() {
        ArrayList<Product> discountedProductsList = new ArrayList<Product>();
        for (Category subcategory : subcategories) {
            discountedProductsList.addAll(subcategory.getInOffProductsList());
        }
        return discountedProductsList;
    }

    @Override
    public boolean hasInOffProduct() {
        for (Category subcategory : subcategories) {
            if (subcategory.hasInOffProduct()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getType() {
        return "ParentCategory";
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public void addFeatures(String newFeatures) {
    }

    @Override
    public void removeFeatures(String removing_features) {
    }

    public void addSubcategory(Category createdCategory) {
        this.subcategories.add(createdCategory);
    }
}
