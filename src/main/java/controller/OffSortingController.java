package controller;

import java.util.ArrayList;

public class OffSortingController {
    private static OffSortingController instance = new OffSortingController();

    private OffSortingController() {
    }

    public static OffSortingController getInstance() {
        return instance;
    }

    public ArrayList<String> getAvailableSortsForOffs() {
        return null;
    }

    public void setOffSort(String offSort) {
    }

    public void disableCurrentSortForOff() {}

}
