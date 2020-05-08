package model.category;

import model.Product;

import model.Product;

import java.util.ArrayList;

public class FinalCategory extends Category {
    private ArrayList<String> specialFeatures;
    private final ArrayList<Product> productList;

    public FinalCategory(String name, Category parent, ArrayList<String> specialFeatures) {
        super(name, parent);
        this.productList = new ArrayList<Product>();
    }

    public ArrayList<String> getSpecialFeatures() {
        return specialFeatures;
    }

    public ArrayList<Product> getProductList() {
        return productList;
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
}
