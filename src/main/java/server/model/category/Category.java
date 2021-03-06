package server.model.category;

import server.controller.CategoryController;
import server.model.IdKeeper;
import server.model.IdRecognized;
import server.model.Market;
import server.model.Savable;
import server.model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Category extends IdRecognized implements Savable {
    private String name;
    private ParentCategory parent;


    public Category(String name, ParentCategory parent) {
        this.id = getType() + IdKeeper.getInstance().getCategoriesNewId();
        this.name = name;
        this.parent = parent;
    }


    public Category(HashMap<String, String> filledFeatures) {
        this.id = getType() + IdKeeper.getInstance().getCategoriesNewId();
        this.setName(filledFeatures.get("name"));
        if (!filledFeatures.get("parent category").equals("NULL"))
            this.setParent((ParentCategory) CategoryController.getInstance().getItemById(filledFeatures.get("parent category")));
        else parent = null;
    }

    public static ParentCategory getNullCategory(){
        return new ParentCategory("NULL", null);
    }

    public Category(String categoryId) {
        this.id = categoryId;
    }

    @Override
    public String getId() {
        return id;
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

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("name", name);
        if (parent == null) {
            result.put("parent", null);
        } else {
            result.put("parent", parent.getId());
        }
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        name = theMap.get("name");
        String parentId = theMap.get("parent");
        if (parentId == null) {
            parent = null;
        } else {
            parent = (ParentCategory) Market.getInstance().getCategoryById(parentId);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}