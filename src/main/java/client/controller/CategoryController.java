package client.controller;

import client.network.MethodStringer;

import java.util.*;
import java.util.stream.Collectors;

public class CategoryController {
    private static final CategoryController instance = new CategoryController();

    private CategoryController() {
    }

    public static CategoryController getInstance() {
        return instance;
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
        return getCategoryById(categoryId).getName();
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
                    "getCategorySpecialFeatures");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String getCategoryId(String categoryName) {
//        return (String) MethodStringer.sampleMethod(getClass(), "getCategoryId", categoryName);
        return getItemById(categoryName).getId();
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

    private void addSubcategories(int depth, ArrayList<String> current, ParentCategory parent) {
        for (Category subcategory : parent.getSubcategories()) {
            current.add(depth + ":" + subcategory.getName());
            if (!subcategory.isFinal()) {
                addSubcategories(depth + 1, current, (ParentCategory) subcategory);
            }
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

//    public ArrayList<HashMap<String, String>> testii(String name) throws Throwable {
//        return (ArrayList<HashMap<String, String>>) MethodStringer.sampleMethod(getClass(), "testii", name);
//    }
}
