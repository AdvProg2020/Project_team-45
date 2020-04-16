package controller;

import model.ProductFilters;

import java.util.ArrayList;

public class ProductFilteringController {
    private MainController mainController;

    public ProductFilteringController(MainController mainController) {
        this.mainController = mainController;
    }

    public ArrayList<String> getAvailableFiltersForProducts() {
        return null;
    }

    public ProductFilters createNewProductFilter() {
        return null;
    }

    public void setFieldOfProductFilters(ProductFilters productFilters, String field, String value) {}

    public void addFilterToProductFilters(ProductFilters productFilters, String filterType, String filter) {}

    public void removeFilterFromProductFilters(ProductFilters productFilters, String filterType, String filter) {
    }

}
