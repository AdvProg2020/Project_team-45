package model.category;

import model.Product;

import java.util.ArrayList;

public class FinalCategory extends Category {
    private ArrayList<String> specialFeatures;
    private final ArrayList<Product> productsList;

    public FinalCategory(String name, Category parent, ArrayList<String> specialFeatures) {
        super(name, parent);
        this.productsList = new ArrayList<Product>();
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

    }

    public boolean removeSpecialFeature(String specialFeature) {
        return false;
    }

    public boolean removeProduct(Product removeProduct) {
        return false;
    }

    @Override
    public ArrayList<Product> getDiscountedProductsList() {
        ArrayList<Product> discountedProductsList = new ArrayList<Product>();
        for (Product product : productsList) {
            if (product.isInOff()) {
                discountedProductsList.add(product);
            }
        }
        return discountedProductsList;
    }

    @Override
    public boolean isDiscounted() {
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
