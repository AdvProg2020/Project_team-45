package model.category;

import controller.CategoryController;
import javafx.scene.Parent;
import model.IdRecognized;
import model.Savable;
import model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Category implements Savable,IdRecognized {
    private String name;
    private ParentCategory parent;
    private String id;


    public Category(String name, ParentCategory parent) {
        this.name = name;
        this.parent = parent;
    }

    public Category(HashMap<String, String> filledFeatures) {
        this.setName(filledFeatures.get("name"));
        if (!filledFeatures.get("parent category").equals("NULL"))
            this.setParent((ParentCategory) CategoryController.getInstance().getItemById(filledFeatures.get("parent category")));
        else parent = null;
    }

    public Category(String categoryId) {
        this.id = categoryId;
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

    public boolean isMain() {
        return parent == null;
    }

    public abstract boolean isFinal();

    public String getId() {
        return id;
    }

    @Override
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("parent", parent);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        name = (String) theMap.get("name");
        parent = (ParentCategory) theMap.get("parent");
    }
}