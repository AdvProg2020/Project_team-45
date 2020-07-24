package client.controller;

import client.network.MethodStringer;

import java.util.*;

public class CategoryController {
    private static final CategoryController instance = new CategoryController();

    private CategoryController() {
    }

    public static CategoryController getInstance() {
        return instance;
    }

    public void createItem(HashMap<String, String> filledFeatures) {
        try {
            MethodStringer.sampleMethod(getClass(), "createItem", filledFeatures);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public boolean categoryNameExists(String keyName) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "categoryNameExists", keyName);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public boolean deleteItemById(String Id) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "deleteItemById", Id);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> getMainCategories() {
        try {
            return (ArrayList<String>) MethodStringer.sampleMethod(getClass(),
                    "getMainCategories");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getActiveCategorySubcategories() {
        try {
            return (ArrayList<String>) MethodStringer.sampleMethod(getClass(),
                    "getActiveCategorySubcategories");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getActiveCategoryDiscountedSubcategories() {
        try {
            return (ArrayList<String>) MethodStringer.sampleMethod(getClass(),
                    "getActiveCategoryDiscountedSubcategories");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void changeIsOffMenuToTrue() {
        try {
            MethodStringer.sampleMethod(getClass(), "changeIsOffMenuToTrue");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void changeIsOffMenuToFalse() {
        try {
            MethodStringer.sampleMethod(getClass(), "changeIsOffMenuToFalse");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void clearActiveCategory() {
        try {
            MethodStringer.sampleMethod(getClass(), "activeCategory");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void backCategory() {
        try {
            MethodStringer.sampleMethod(getClass(), "backCategory");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public List<String> getParentCategoriesNames() {
        try {
            return (List<String>) MethodStringer.sampleMethod(getClass(),
                    "getParentCategoriesNames");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void addFeatureToCategory(String editingCategoryId, String newFeature) {
        try {
            MethodStringer.sampleMethod(getClass(),
                    "addFeatureToCategory", editingCategoryId, newFeature);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void removeFeatureFromCategory(String editingCategoryId, String removingFeature) {
        try {
            MethodStringer.sampleMethod(getClass(),
                    "removeFeatureFromCategory", editingCategoryId, removingFeature);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public boolean categoryHasFeature(String editingCategoryId, String feature) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(),
                    "categoryHasFeature", editingCategoryId, feature);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public Set<String> getActiveCategoryCompanies() {
        try {
            return (Set<String>) MethodStringer.sampleMethod(getClass(),
                    "getActiveCategoryCompanies");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public Set<String> getActiveCategoryDiscountedCompanies() {
        try {
            return (Set<String>) MethodStringer.sampleMethod(getClass(),
                    "getActiveCategoryDiscountedCompanies");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public Set<String> getActiveCategorySellers() {
        try {
            return (Set<String>) MethodStringer.sampleMethod(getClass(),
                    "getActiveCategorySellers");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public Set<String> getActiveCategoryDiscountedSellers() {
        try {
            return (Set<String>) MethodStringer.sampleMethod(getClass(),
                    "getActiveCategoryDiscountedSellers");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public boolean isActiveCategoryFinal() {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "isActiveCategoryFinal");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public LinkedHashMap<String, Set<String>> getActiveCategoryFeaturesAndValues() {
        try {
            return (LinkedHashMap<String, Set<String>>) MethodStringer.sampleMethod(getClass(),
                    "getActiveCategoryFeaturesAndValues");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> getActiveCategoryProductInfosList() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(),
                    "getActiveCategoryProductInfosList");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> getActiveCategoryDiscountedProductInfosList() {
        try {
            return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(),
                    "getActiveCategoryDiscountedProductInfosList");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getDiscountedMainCategories() {
        try {
            return (ArrayList<String>) MethodStringer.sampleMethod(getClass(),
                    "getDiscountedMainCategories");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void setActiveCategoryByName(String name) {
        try {
            MethodStringer.sampleMethod(getClass(), "setActiveCategoryByName", name);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public String getCategoryName(String categoryId) {
        try {
            return (String) MethodStringer.sampleMethod(getClass(), "getCategoryName", categoryId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public boolean categoryIsFinal(String categoryId) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "categoryIsFinal", categoryId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> getCategorySpecialFeatures(String categoryId) {
        try {
            return (ArrayList<String>) MethodStringer.sampleMethod(getClass(),
                    "getCategorySpecialFeatures", categoryId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String getCategoryId(String categoryName) {
        try {
            return (String) MethodStringer.sampleMethod(getClass(), "getCategoryId", categoryName);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String getActiveCategoryName() {
        try {
            return (String) MethodStringer.sampleMethod(getClass(),
                    "getActiveCategoryName");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getCategoriesTree() {
        try {
            return (ArrayList<String>) MethodStringer.sampleMethod(getClass(),
                    "getCategoriesTree");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void editCategoryName(String editingCategoryId, String newName) {
        try {
            MethodStringer.sampleMethod(getClass(),
                    "editCategoryName", editingCategoryId, newName);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in add product panel

    public String getCategoryTypeByName(String text) {
        try {
            return (String) MethodStringer.sampleMethod(getClass(),
                    "getCategoryTypeByName", text);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

//    public ArrayList<HashMap<String, String>> testii(String name) throws Throwable {
//        return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(), "testii", name);
//    }
}
