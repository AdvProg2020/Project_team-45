package controller;

import model.ProductFilters;

import java.util.ArrayList;

public class FilteringController {
    private static final FilteringController instance = new FilteringController();

    private FilteringController() {
    }

    public static FilteringController getInstance() {
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

    public ArrayList<String> getAvailableFilters() {
        return null;
    }

    public ArrayList<String> getCurrentFilters() {
        return null;
    }

    public boolean addFilter(String filter) {

        return false;
    }

    public boolean removeFilter(String filter) {

        return false;
    }


}
