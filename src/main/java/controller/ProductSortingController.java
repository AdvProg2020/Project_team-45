package controller;

import java.util.ArrayList;

public class ProductSortingController {
    private static ProductSortingController instance = new ProductSortingController();

    private ProductSortingController() {

    }

    public static ProductSortingController getInstance() {
        return instance;
    }

    public ArrayList<String> getAvailableSortsForProducts() {
        return null;
    }

    public void setProductSort(String productSort) {
    }

    public void disableCurrentSortForProduct() {}

}
