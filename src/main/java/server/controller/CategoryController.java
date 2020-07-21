package server.controller;

import server.controller.managers.Deleter;
import server.model.Market;
import server.model.category.Category;
import server.model.category.FinalCategory;
import server.model.category.ParentCategory;
import server.model.product.Product;
import server.model.product.ProductSellInfo;

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
        ArrayList<String> mainCategoriesName = new ArrayList<>();
        ArrayList<Category> mainCategories = market.getMainCategories();
        for (Category mainCategory : mainCategories) {
            mainCategoriesName.add(mainCategory.getName());
        }
        return mainCategoriesName;
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
                if (productSellInfo.isInOff())
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

    public void setActiveCategoryByName(String name) {
        activeCategory = market.getCategoryByName(name);
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
        return getItemById(categoryName).getId();
    }

    public String getActiveCategoryName() {
        return activeCategory.getName();
    }

    public ArrayList<String> getCategoriesTree() {
        ArrayList<String> output = new ArrayList<>();
        for (Category mainCategory : market.getMainCategories()) {
            output.add(0 + ":" + mainCategory.getName());
            if (!mainCategory.isFinal()) {
                addSubcategories(1, output, (ParentCategory) mainCategory);
            }
        }
        return output;
    }

    private void addSubcategories(int depth, ArrayList<String> current, ParentCategory parent) {
        for (Category subcategory : parent.getSubcategories()) {
            current.add(depth + ":" + subcategory.getName());
            if (!subcategory.isFinal()) {
                addSubcategories(depth+1, current, (ParentCategory) subcategory);
            }
        }
    }



    //bagheri

    public void editCategoryName(String editingCategoryId, String newName) {
        market.getCategoryById(editingCategoryId).setName(newName);
    }

//    public ArrayList<HashMap<String, String>> testii(String name) throws Exception {
//        if (!name.isEmpty()) {
//            throw new Exception("oh!");
//        }
//        ArrayList<HashMap<String, String>> a = new ArrayList<>();
//        HashMap<String, String> b = new HashMap<>();
//        b.put("hatam", "mammad");
//        b.put("OS", "neda");
//        b.put("ghofli", "bagheri");
//        a.add(b);
//        HashMap<String, String> c = new HashMap<>();
//        c.put("hatam", "mammad");
//        c.put("OS", "neda");
//        c.put("ghofli", "bagheri");
//        a.add(c);
//        return a;
//    }
}
