package client.controller;

import client.network.MethodStringer;

public class SortingController {
    private static final SortingController instance = new SortingController();

    private SortingController() {
    }

    public static SortingController getInstance() {
        return instance;
    }

    public boolean setActiveSort(String inputSort) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(),
                    "setActiveSort", inputSort);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }
}