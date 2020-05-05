package controller;

import model.OffFilters;

import java.util.ArrayList;

public class OffFilteringController {
    private static OffFilteringController instance = new OffFilteringController();

    private OffFilteringController() {

    }

    public static OffFilteringController getInstance() {
        return instance;
    }

    public ArrayList<String> getAvailableFiltersForOffs() {
        return null;
    }

    public OffFilters createNewOffFilter() {
        return null;
    }

    public void setFieldOfOffFilters(OffFilters offFilters, String field, String value) {}

}
