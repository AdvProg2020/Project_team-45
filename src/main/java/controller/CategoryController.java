package controller;

import controller.managers.Creator;
import controller.managers.Editor;
import model.Market;
import model.Product;
import model.category.Category;
import model.category.FinalCategory;
import model.category.ParentCategory;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryController implements Editor, Creator {
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
        if (removingCategory.getType().equals("ParentCategory"))
            for (Category subcategory : ((ParentCategory) removingCategory).getSubcategories()) {
                removeCategory(subcategory);
            }
        else for (Product product : new ArrayList<Product>(removingCategory.getProductsList())) {
            productController.removeProduct(product);
        }
        market.removeCategoryFromList(removingCategory);
        if (removingCategory.isMain())
            market.getMainCategories().remove(removingCategory);
        else
            removingCategory.getParent().removeSubcategoryFromList(removingCategory);
    }

    public void setParentOfCategory(Category category, String parentName) {
    }

    public HashMap<String, InputValidator> getAvailableFieldsToEdit() {
        HashMap<String, InputValidator> availableFieldsToEdit = new HashMap<>();
        availableFieldsToEdit.put("new features", InputValidator.getCategoryFeaturesValidator());
        availableFieldsToEdit.put("removing features", InputValidator.getCategoryFeaturesValidator());
        availableFieldsToEdit.put("name", InputValidator.getSimpleTextValidator());
        return availableFieldsToEdit;
    }

    @Override
    public void editItem(Object editingObject, HashMap<String, String> changedFields) {
        Category editingCategory = (Category) editingObject;
        if (changedFields.containsKey("name")) {
            editingCategory.setName(changedFields.get("name"));
        }
        if (changedFields.containsKey("new features")) {
            editingCategory.addFeatures(changedFields.get("new features"));
        }
        if (changedFields.containsKey("removing features")) {
            editingCategory.removeFeatures(changedFields.get("removing features"));
        }
    }

    public boolean deleteItemById(String Id) {
        Category removingCategory = getItemById(Id);
        if (removingCategory == null)
            return false;
        removeCategory(removingCategory);
        return true;
    }

    public String getAllInListAsString() {
        ArrayList<Category> allCategories = market.getAllCategories();
        StringBuilder listString = new StringBuilder("name,parent\n");
        for (Category category : allCategories) {
            String categoryInfo = category.getName() + "," + category.getParent().getName() + "\n";
            listString.append(categoryInfo);
        }
        return listString.toString();
    }

    public String getDetailStringById(String Id) {
        // not involved yet //
        return null;
    }


    public ArrayList<String> getMainCategories() {
        ArrayList<String> mainCategoriesName = new ArrayList<String>();
        ArrayList<Category> mainCategories = market.getMainCategories();
        for (Category mainCategory : mainCategories) {
            mainCategoriesName.add(mainCategory.getName());
        }
        return mainCategoriesName;
    }

    public ArrayList<String> getSubcategoriesByNameCategory(String categoryName) {
        ArrayList<String> subcategoriesName = new ArrayList<String>();
        Category mainCategory = market.getMainCategoryByName(categoryName);
        if (mainCategory == null) {
            return null;
        }
        if (mainCategory.getType().equals("ParentCategory")) {
            ArrayList<Category> subcategories = ((ParentCategory) mainCategory).getSubcategories();
            for (Category subcategory : subcategories) {
                subcategoriesName.add(subcategory.getName());
            }
        }
        return subcategoriesName;
    }

    public ArrayList<String> getActiveCategoryProducts() {
        ArrayList<String> output = new ArrayList<>();
        ArrayList<Product> activeCategoryProducts = activeCategory.getProductsList();
        activeCategoryProducts = FilteringController.getInstance().filteringProducts(activeCategoryProducts);
        SortingController.getInstance().sortingProducts(activeCategoryProducts);
        for (Product product : activeCategoryProducts) {
            output.add(product.toString());
        }
        return output;
    }

    public ArrayList<String> getActiveCategoryDiscountedProducts() {
        ArrayList<String> output = new ArrayList<>();
        ArrayList<Product> activeCategoryProducts;
        if (activeCategory == null) {
            activeCategoryProducts = market.getAllDiscountedProductsList();
        } else {
            activeCategoryProducts = activeCategory.getInOffProductsList();
        }
        activeCategoryProducts = FilteringController.getInstance().filteringProducts(activeCategoryProducts);
        SortingController.getInstance().sortingProducts(activeCategoryProducts);
        for (Product product : activeCategoryProducts) {
            output.addAll(product.getOffsInfo());
        }
        return output;
    }

    public ArrayList<String> getActiveCategorySubcategories() {
        ArrayList<String> subcategoriesName = new ArrayList<>();
        if (activeCategory.getType().equals("ParentCategory")) {
            ArrayList<Category> subcategories = ((ParentCategory) activeCategory).getSubcategories();
            for (Category subcategory : subcategories) {
                subcategoriesName.add(subcategory.getName());
            }
        }
        return subcategoriesName;
    }

    public ArrayList<String> getActiveCategoryDiscountedSubcategories() {
        ArrayList<String> subcategoriesName = new ArrayList<>();
        if (activeCategory.getType().equals("ParentCategory")) {
            ArrayList<Category> subcategories = ((ParentCategory) activeCategory).getSubcategories();
            for (Category subcategory : subcategories) {
                if (subcategory.hasInOffProduct()) {
                    subcategoriesName.add(subcategory.getName());
                }
            }
        }
        return subcategoriesName;
    }

    public void changeIsOffMenuToTrue() { //name?!
        isOffMenu = true;
    }

    public void changeIsOffMenuToFalse() { //name?!
        isOffMenu = false;
    }

    public boolean setActiveCategoryByName(String name) {
        Category category = market.getCategoryByName(name);
        if (category != null && (category.getParent() == activeCategory || (activeCategory == null && category.getParent().getParent() == null))) {
            activeCategory = category;
            return true;
        }
        return false;
        // TODO bagheri
    }

    public void backCategory() {
        if (activeCategory != null) {
            activeCategory = activeCategory.getParent();
        }
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
            if (product.getProductId().equals(productId))
                return product;
        }
        return null;
    }

    public HashMap<String, InputValidator> getNecessaryFieldsToCreate() {
        HashMap<String, InputValidator> necessaryFields = new HashMap<>();
        necessaryFields.put("name", InputValidator.getSimpleTextValidator());
        necessaryFields.put("features", InputValidator.getCategoryFeaturesValidator());
        necessaryFields.put("parent category", new InputValidator("\\w+", "an existing category name", CategoryController.getInstance()));
        necessaryFields.put("is final?", new InputValidator("yes|no", "yes or no"));
        return necessaryFields;
    }

    @Override
    public HashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {
        Category createdCategory;
        if (filledFeatures.get("is final?").equals("yes")) {
            createdCategory = new FinalCategory(filledFeatures);
        } else createdCategory = new ParentCategory(filledFeatures);
        createdCategory.getParent().addSubcategory(createdCategory);
        market.addCategoryToList(createdCategory);
    }

    @Override
    public Category getItemById(String Id) {
        return market.getCategoryByName(Id);
    }
}
