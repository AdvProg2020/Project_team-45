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
