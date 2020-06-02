package model.category;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    public HashMap<String, String> convertToHashMap(int i) {
        HashMap<String, String> result = super.convertToHashMap(i);
        ArrayList<String> subcategoriesId = new ArrayList<>();
        for (Category subcategory : subcategories) {
            subcategoriesId.add(subcategory.getId());
        }
        result.put("subcategories", (new Gson()).toJson(subcategoriesId));
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap, int i) {
        Market market = Market.getInstance();
        super.setFieldsFromHashMap(theMap, i);
        ArrayList<String> subcategoriesId = (new Gson()).fromJson(theMap.get("subcategories"), new TypeToken<ArrayList<String>>(){}.getType());
        for (String subcategoryId : subcategoriesId) {
            subcategories.add(market.getCategoryById(subcategoryId));
        }
    }
}
