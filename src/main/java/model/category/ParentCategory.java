package model.category;

import model.Market;
import model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ParentCategory extends Category {
    private final ArrayList<Category> subcategories;

    public ParentCategory(String name, ParentCategory parent) {
        super(name, parent);
        this.subcategories = new ArrayList<>();
    }

    public ParentCategory(HashMap<String, String> filledFeatures) {
        super(filledFeatures);
        this.subcategories = new ArrayList<>();
    }

    public ParentCategory(String categoryId) {
        super(categoryId);
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
        ArrayList<Product> allProducts = new ArrayList<>();
        for (Category subcategory : subcategories) {
            allProducts.addAll(subcategory.getProductsList());
        }
        return allProducts;
    }

    @Override
    public ArrayList<Product> getInOffProductsList() {
        ArrayList<Product> discountedProductsList = new ArrayList<>();
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

    public void addSubcategory(Category createdCategory) {
        this.subcategories.add(createdCategory);
    }

    @Override
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = super.convertToHashMap();
        ArrayList<String> subcategoriesId = new ArrayList<>();
        for (Category subcategory : subcategories) {
            subcategoriesId.add(subcategory.getId());
        }
        result.put("subcategories", subcategoriesId);
        return null;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        Market market = Market.getInstance();
        super.setFieldsFromHashMap(theMap);
        for (String subcategoryId : ((ArrayList<String>) theMap.get("subcategories"))) {
            subcategories.add(market.getCategoryById(subcategoryId));
        }
    }
}
