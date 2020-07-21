package client.controller;

import client.controller.managers.Deleter;
import client.network.ClientSocket;
import client.network.MethodStringer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.model.Market;
import server.model.category.Category;
import server.model.category.FinalCategory;
import server.model.category.ParentCategory;
import server.model.product.Product;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryController implements Deleter {
    private static final CategoryController instance = new CategoryController();
    private final Market market;
    private Category activeCategory;
    private boolean isOffMenu;
    private final ProductController productController = ProductController.getInstance();

    private CategoryController() {
        market = Market.getInstance();
        activeCategory = null;
    }

    public static CategoryController getInstance() {
        return instance;
    }

    public Category getActiveCategory() {
        return activeCategory;
    }

    public void removeCategory(Category removingCategory) {
        if (removingCategory.getType().equals("ParentCategory")) {
            ArrayList<Category> subCategories = new ArrayList<>(((ParentCategory) removingCategory).getSubcategories());
            for (Category subcategory : subCategories) {
                removeCategory(subcategory);
            }
        } else {
            ArrayList<Product> removingProducts = new ArrayList<>(removingCategory.getProductsList());
            for (Product product : removingProducts) {
                productController.removeProduct(product);
            }
        }
        market.removeCategoryFromList(removingCategory);
        if (removingCategory.isMain())
            market.removeMainCategory(removingCategory);
        else
            removingCategory.getParent().removeSubcategoryFromList(removingCategory);
    }

    public void createItem(HashMap<String, String> filledFeatures) {
        Category createdCategory;
        ArrayList<String> categorySpecialFeatures;

        categorySpecialFeatures = new ArrayList<>(Arrays.asList(filledFeatures.get("features").trim().split("\n")));
        if (filledFeatures.get("is final?").equals("yes")) {
            createdCategory = new FinalCategory(filledFeatures, categorySpecialFeatures);
        } else createdCategory = new ParentCategory(filledFeatures);
        if (createdCategory.isMain())
            market.addMainCategoryToList(createdCategory);
        else createdCategory.getParent().addSubcategory(createdCategory);
        market.addCategoryToList(createdCategory);
    }

    private Category getCategoryById(String categoryId) {
        return market.getCategoryById(categoryId);
    }

    public boolean categoryNameExists(String keyName) {
        return market.getCategoryByName(keyName) != null;
    }

    public boolean deleteItemById(String Id) {
        Category removingCategory = getItemById(Id);
        if (removingCategory == null)
            return false;
        removeCategory(removingCategory);
        return true;
    }


    public ArrayList<String> getMainCategories() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<ArrayList<String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public ArrayList<String> getActiveCategorySubcategories() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<ArrayList<String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getActiveCategoryDiscountedSubcategories() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<ArrayList<String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void changeIsOffMenuToTrue() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            ClientSocket.getInstance().sendAction(action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changeIsOffMenuToFalse() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            ClientSocket.getInstance().sendAction(action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void clearActiveCategory() {
        activeCategory = null;
        // TODO : bagheri use it
    }

    public void backCategory() {
        if (activeCategory != null) {
            activeCategory = activeCategory.getParent();
        }
        // TODO : bagheri use it
    }

    public ArrayList<Product> getActiveCategoryProductsList() {
        if (!isOffMenu)
            return activeCategory.getProductsList();
        if (activeCategory == null)
            return market.getAllDiscountedProductsList();
        return activeCategory.getInOffProductsList();
    }

    public Product getActiveCategoryProduct(String productId) {
        for (Product product : activeCategory.getProductsList()) {
            if (product.getId().equals(productId))
                return product;
        }
        return null;
    }


    @Override
    public Category getItemById(String Id) {
        return market.getCategoryByName(Id);
    }

    public List<String> getParentCategoriesNames() {
        List<Category> allCategories = Market.getInstance().getAllCategories();
        return allCategories.stream().filter(category -> !category.isFinal()).map(Category::getName).collect(Collectors.toList());
    }

    public void addFeatureToCategory(String editingCategoryId, String newFeature) {
        ((FinalCategory) getCategoryById(editingCategoryId)).addFeature(newFeature);
    }

    public void removeFeatureFromCategory(String editingCategoryId, String removingFeature) {
        ((FinalCategory) getCategoryById(editingCategoryId)).removeFeature(removingFeature);
    }


    public boolean categoryHasFeature(String editingCategoryId, String feature) {
        return ((FinalCategory) getCategoryById(editingCategoryId)).getSpecialFeatures().contains(feature);
    }

    public Set<String> getActiveCategoryCompanies() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<Set<String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<String> getActiveCategoryDiscountedCompanies() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<Set<String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<String> getActiveCategorySellers() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<Set<String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<String> getActiveCategoryDiscountedSellers() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<Set<String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isActiveCategoryFinal() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, boolean.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedHashMap<String, Set<String>> getActiveCategoryFeaturesAndValues() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<LinkedHashMap<String, Set<String>>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> getActiveCategoryProductInfosList() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> getActiveCategoryDiscountedProductInfosList() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getDiscountedMainCategories() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<ArrayList<String>>() {
            }.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setActiveCategoryByName(String name) {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, name);
            ClientSocket.getInstance().sendAction(action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getCategoryName(String categoryId) {
        return getCategoryById(categoryId).getName();
    }

    public boolean categoryIsFinal(String categoryId) {
        return getCategoryById(categoryId).isFinal();
    }

    public ArrayList<String> getCategorySpecialFeatures(String categoryId) {
        return ((FinalCategory) market.getCategoryById(categoryId)).getSpecialFeatures();
    }

    public String getCategoryId(String categoryName) {
//        return (String) MethodStringer.sampleMethod(getClass(), "getCategoryId", categoryName);
        return getItemById(categoryName).getId();
    }

    public String getActiveCategoryName() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, String.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getCategoriesTree() {

        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<ArrayList<String>>(){}.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addSubcategories(int depth, ArrayList<String> current, ParentCategory parent) {
        for (Category subcategory : parent.getSubcategories()) {
            current.add(depth + ":" + subcategory.getName());
            if (!subcategory.isFinal()) {
                addSubcategories(depth + 1, current, (ParentCategory) subcategory);
            }
        }
    }


    //bagheri

    public void editCategoryName(String editingCategoryId, String newName) {
        market.getCategoryById(editingCategoryId).setName(newName);
    }

//    public ArrayList<HashMap<String, String>> testii(String name) throws Throwable {
//        return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(), "testii", name);
//    }
}
