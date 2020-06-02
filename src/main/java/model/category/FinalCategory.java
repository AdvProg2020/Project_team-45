package model.category;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Market;
import model.product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FinalCategory extends Category {
    private final ArrayList<String> specialFeatures;
    private final ArrayList<Product> productsList;

    public FinalCategory(String name, ParentCategory parent, ArrayList<String> specialFeatures) {
        super(name, parent);
        this.specialFeatures = specialFeatures;
        this.productsList = new ArrayList<>();
    }

    public FinalCategory(HashMap<String, String> filledFeatures, ArrayList<String> categorySpecialFeatures) {
        super(filledFeatures);
        this.productsList = new ArrayList<>();
        this.specialFeatures = categorySpecialFeatures;
    }

    public FinalCategory(String categoryId) {
        super(categoryId);
        this.specialFeatures = new ArrayList<>();
        this.productsList = new ArrayList<>();
    }

    public ArrayList<String> getSpecialFeatures() {
        return specialFeatures;
    }

    @Override
    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void addSpecialFeature(String newSpecialFeature) {
        specialFeatures.add(newSpecialFeature);
    }

    public void addProduct(Product newProduct) {
        productsList.add(newProduct);
    }

    public void removeSpecialFeature(String specialFeature) {
        specialFeatures.remove(specialFeature);
    }

    public void removeProduct(Product product) {
        productsList.remove(product);
    }

    @Override
    public ArrayList<Product> getInOffProductsList() {
        ArrayList<Product> discountedProductsList = new ArrayList<>();
        for (Product product : productsList) {
            if (product.isInOff()) {
                discountedProductsList.add(product);
            }
        }
        return discountedProductsList;
    }

    @Override
    public boolean hasInOffProduct() {
        for (Product product : productsList) {
            if (product.isInOff()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getType() {
        return "FinalCategory";
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    public void addFeatures(String newFeaturesString) {
        ArrayList<String> newFeatures = (ArrayList<String>) Arrays.asList(newFeaturesString.split("-"));
        this.specialFeatures.addAll(newFeatures);
    }

    public void removeFeatures(String removingFeaturesString) {
        ArrayList<String> removingFeatures = (ArrayList<String>) Arrays.asList(removingFeaturesString.split("-"));
        for (String removingFeature : removingFeatures) {
            specialFeatures.remove(removingFeature);
        }
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = super.convertToHashMap();
        result.put("specialFeatures", (new Gson()).toJson(specialFeatures));
        ArrayList<String> productsId = new ArrayList<>();
        for (Product product : productsList) {
            productsId.add(product.getId());
        }
        result.put("productsList", (new Gson()).toJson(productsId));
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        Market market = Market.getInstance();
        super.setFieldsFromHashMap(theMap);
        specialFeatures.addAll((new Gson()).fromJson(theMap.get("specialFeatures"), new TypeToken<ArrayList<String>>(){}.getType()));
        ArrayList<String> productsId = (new Gson()).fromJson(theMap.get("productsList"), new TypeToken<ArrayList<String>>(){}.getType());
        for (String productId : productsId) {
            productsList.add(market.getProductById(productId));
        }
    }
}
