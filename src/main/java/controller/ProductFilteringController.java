package controller;

import model.ProductFilters;

import java.util.ArrayList;

public class ProductFilteringController {
    private static ProductFilteringController instance = new ProductFilteringController();

    private ProductFilteringController() {
    }

    public static ProductFilteringController getInstance() {
        return instance;
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
