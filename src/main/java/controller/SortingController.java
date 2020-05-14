package controller;

import java.util.ArrayList;

public class SortingController {
    private static final SortingController instance = new SortingController();

    private SortingController() {

    }

    public static SortingController getInstance() {
        return instance;
    }

    public ArrayList<String> getAvailableSorts() {
        return null;
    }

    public boolean setSort(String sort) {

        return false;
    }

    public String showCurrentSort() {
        return null;
    }

    public void disableCurrentSort() {

    }
}
