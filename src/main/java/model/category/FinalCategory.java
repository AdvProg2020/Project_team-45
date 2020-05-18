package model.category;

import model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FinalCategory extends Category {
    private ArrayList<String> specialFeatures;
    private final ArrayList<Product> productsList;

    public FinalCategory(HashMap<String, String> filledFeatures, ArrayList<String> categorySpecialFeatures) {
        super(filledFeatures);
        this.productsList = new ArrayList<>();
        this.specialFeatures = categorySpecialFeatures;
    }

    public ArrayList<String> getSpecialFeatures() {
        return specialFeatures;
    }

    @Override
    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void addProduct(Product newProduct) {
        productsList.add(newProduct);
    }

    public void removeSpecialFeature(String specialFeature) {
    }

    public void removeProduct(Product product) {
        productsList.remove(product);
    }

    @Override
    public ArrayList<Product> getInOffProductsList() {
        ArrayList<Product> discountedProductsList = new ArrayList<Product>();
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
    public void addFeatures(String newFeaturesString) {
        ArrayList<String> newFeatures = (ArrayList<String>) Arrays.asList(newFeaturesString.split("-"));
        this.specialFeatures.addAll(newFeatures);
    }

    @Override
    public void removeFeatures(String removingFeaturesString) {
        ArrayList<String> removingFeatures = (ArrayList<String>) Arrays.asList(removingFeaturesString.split("-"));
        for (String removingFeature : removingFeatures) {
            specialFeatures.remove(removingFeature);
        }
    }
}
