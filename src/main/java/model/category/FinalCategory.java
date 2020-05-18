package model.category;

import model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FinalCategory extends Category {
    private ArrayList<String> specialFeatures;
    private final ArrayList<Product> productsList;

    public FinalCategory(String name, ParentCategory parent, ArrayList<String> specialFeatures) {
        super(name, parent);
        this.productsList = new ArrayList<Product>();
    }

    public FinalCategory(HashMap<String, String> filledFeatures) {
        super(filledFeatures);
        this.productsList = new ArrayList<>();
        this.specialFeatures = (ArrayList<String>) Arrays.asList(filledFeatures.get("features").split("-"));
    }

    public ArrayList<String> getSpecialFeatures() {
        return specialFeatures;
    }

    @Override
    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void addSpecialFeature(String newSpecialFeature) {

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
}
