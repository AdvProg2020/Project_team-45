package controller;

import model.OffFilters;

import java.util.ArrayList;

public class OffFilteringController {
    private MainController mainController;

    public OffFilteringController(MainController mainController) {
        this.mainController = mainController;
    }

    public ArrayList<String> getAvailableFiltersForOffs() {
        return null;
    }

    public OffFilters createNewOffFilter() {
        return null;
    }

    public void setFieldOfOffFilters(OffFilters offFilters, String field, String value) {}

}
