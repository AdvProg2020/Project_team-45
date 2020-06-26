package controller;

import consuleview.UIPage;
import consuleview.hatemi.adminMenus.CategoriesManagingMenu;
import controller.managers.Creator;
import controller.managers.Editor;
import model.Company;
import model.Market;
import model.category.Category;
import model.category.FinalCategory;
import model.category.ParentCategory;
import model.product.Product;
import model.product.ProductSellInfo;

import java.util.*;
import java.util.stream.Collectors;

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
        else {
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

    public void setParentOfCategory(Category category, String parentName) {
    }

    public LinkedHashMap<String, InputValidator> getAvailableFieldsToEdit() {
        LinkedHashMap<String, InputValidator> availableFieldsToEdit = new LinkedHashMap<>();
        availableFieldsToEdit.put("name", InputValidator.getSimpleTextValidator());
        return availableFieldsToEdit;
    }

    @Override
    public void editItem(Object editingObject, HashMap<String, String> changedFields) {
        Category editingCategory = (Category) editingObject;
        if (changedFields.containsKey("name")) {
            editingCategory.setName(changedFields.get("name"));
        }
        if (editingCategory.getType().equals("FinalCategory")) {
        }
    }

    public void editCategoryName(Category editingCategory, String newName) {
            editingCategory.setName(newName);
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

    @Override
    public boolean justRequests() {
        return false;
    }

    public String getAllInListAsString() {
        ArrayList<Category> allCategories = market.getAllCategories();
        StringBuilder listString = new StringBuilder("name,parent,is final?\n");
        String categoryInfo;
        for (Category category : allCategories) {
            if (category.isMain())
                 categoryInfo = category.getName() + "," + "IS MAIN" + "," + category.isFinal() + "\n";
            else categoryInfo = category.getName() + "," + category.getParent().getName() + "," + category.isFinal() + "\n";
            listString.append(categoryInfo);
        }
        return listString.toString();
    }

    public String getDetailStringById(String Id) {
        // not involved yet //
        return null;
    }


    public ArrayList<String> getMainCategories() {
        ArrayList<String> mainCategoriesName = new ArrayList<>();
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
        if (category != null && (category.getParent() == activeCategory
                || (activeCategory == null && category.getParent().getParent() == null))) {
            activeCategory = category;
            return true;
        }
        return false;
        // TODO bagheri
    }

    public void clearActiveCategory() {
        activeCategory = null;
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
            if (product.getId().equals(productId))
                return product;
        }
        return null;
    }

    public LinkedHashMap<String, InputValidator> getNecessaryFieldsToCreate() {
        LinkedHashMap<String, InputValidator> necessaryFields = new LinkedHashMap<>();
        necessaryFields.put("parent category", InputValidator.getCategoryParentValidator());
        necessaryFields.put("is final?", new InputValidator("yes|no", "yes or no"));
        return necessaryFields;
    }

    @Override
    public LinkedHashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {
        Category createdCategory;
        ArrayList<String> categorySpecialFeatures;
        if (!filledFeatures.containsKey("name")) {
            filledFeatures.put("name", UIPage.getMatcher().group(1));
            categorySpecialFeatures = CategoriesManagingMenu.getCategoryFeatures();
        } else {
            categorySpecialFeatures = new ArrayList<String>(Arrays.asList(filledFeatures.get("features").trim().split("\n")));
        }
        if (filledFeatures.get("is final?").equals("yes")) {
            createdCategory = new FinalCategory(filledFeatures, categorySpecialFeatures);
        } else createdCategory = new ParentCategory(filledFeatures);
        if (createdCategory.isMain())
            market.addMainCategoryToList(createdCategory);
        else createdCategory.getParent().addSubcategory(createdCategory);
        market.addCategoryToList(createdCategory);
    }

    @Override
    public Category getItemById(String Id) {
        return market.getCategoryByName(Id);
    }

    public List<ParentCategory> getParentCategories() {
        List<Category> allCategories = Market.getInstance().getAllCategories();
        return  allCategories.stream().filter(category -> !category.isFinal()).map(category -> (ParentCategory) category).collect(Collectors.toList());

    }

    public List<FinalCategory> getFinalCategories() {
        List<Category> allCategories = Market.getInstance().getAllCategories();
        return  allCategories.stream().filter(Category::isFinal).map(category -> (FinalCategory) category).collect(Collectors.toList());
    }

    public void addFeatureToCategory(FinalCategory editingCategory, String newFeature) {
        editingCategory.addFeature(newFeature);
    }

    public void removeFeatureFromCategory(FinalCategory editingCategory, String removingFeature) {
        editingCategory.removeFeature(removingFeature);
    }


    public boolean categoryHasFeature(FinalCategory editingCategory, String feature) {
        return editingCategory.getSpecialFeatures().contains(feature);
    }


    //bagheri
    public Set<String> getActiveCategoryCompanies() {
        Set<String> categoriesName = new HashSet<>();
        for (Product product : activeCategory.getProductsList()) {
            categoriesName.add(product.getCompany().getName());
        }
        return categoriesName;
    }

    public Set<String> getActiveCategoryDiscountedCompanies() {
        Set<String> categoriesName = new HashSet<>();
        for (Product product : activeCategory.getInOffProductsList()) {
            categoriesName.add(product.getCompany().getName());
        }
        return categoriesName;
    }

    public Set<String> getActiveCategorySellers() {
        Set<String> sellersUsername = new HashSet<>();
        for (Product product : activeCategory.getProductsList()) {
            for (ProductSellInfo productSellInfo : product.getSellInfosList()) {
                sellersUsername.add(productSellInfo.getSeller().getUsername());
            }
        }
        return sellersUsername;
    }

    public Set<String> getActiveCategoryDiscountedSellers() {
        Set<String> sellersName = new HashSet<>();
        for (Product product : activeCategory.getInOffProductsList()) {
            for (ProductSellInfo productSellInfo : product.getSellInfosList()) {
                sellersName.add(productSellInfo.getSeller().getUsername());
            }
        }
        return sellersName;
    }

    public boolean isActiveCategoryFinal() {
         return activeCategory.isFinal();
    }

    public LinkedHashMap<String, Set<String>> getActiveCategoryFeaturesAndValues() {
        LinkedHashMap<String, Set<String>> featuresAndValues = new LinkedHashMap<>();
        if (activeCategory.isFinal()) {
            ArrayList<String> specialFeatures = ((FinalCategory) activeCategory).getSpecialFeatures();
            for (String specialFeature : specialFeatures) {
                featuresAndValues.put(specialFeature, new HashSet<>());
            }
            for (Product product : activeCategory.getProductsList()) {
                LinkedHashMap<String, String> productCategoryFeatures = product.getCategoryFeatures();
                for (String specialFeature : specialFeatures) {
                    String value = productCategoryFeatures.get(specialFeature);
                    if (value != null)
                        featuresAndValues.get(specialFeature).add(value);
                }
            }
        }
        return featuresAndValues;
    }

    public ArrayList<HashMap<String, String>> getActiveCategoryProductInfosList() {
        ArrayList<HashMap<String, String>> output = new ArrayList<>();
        ArrayList<Product> activeCategoryProducts = activeCategory.getProductsList();
        activeCategoryProducts = FilteringController.getInstance().filteringProducts(activeCategoryProducts);
        SortingController.getInstance().sortingProducts(activeCategoryProducts);
        for (Product product : activeCategoryProducts) {
            output.add(product.getProductInfoForProductsList());
        }
        return output;
    }

    public ArrayList<HashMap<String, String>> getActiveCategoryDiscountedProductInfosList() {
        ArrayList<HashMap<String, String>> output = new ArrayList<>();
        ArrayList<Product> activeCategoryProducts;
        if (activeCategory == null) {
            activeCategoryProducts = market.getAllDiscountedProductsList();
        } else {
            activeCategoryProducts = activeCategory.getInOffProductsList();
        }
        activeCategoryProducts = FilteringController.getInstance().filteringProducts(activeCategoryProducts);
        SortingController.getInstance().sortingProducts(activeCategoryProducts);
        for (Product product : activeCategoryProducts) {
            output.addAll(product.getProductOffInfoForProductsList());
        }
        return output;
    }

    public ArrayList<String> getDiscountedMainCategories() {
        ArrayList<String> mainCategoriesName = new ArrayList<>();
        ArrayList<Category> mainCategories = market.getMainCategories();
        for (Category mainCategory : mainCategories) {
            if (mainCategory.hasInOffProduct())
                mainCategoriesName.add(mainCategory.getName());
        }
        return mainCategoriesName;
    }
    //bagheri
}
