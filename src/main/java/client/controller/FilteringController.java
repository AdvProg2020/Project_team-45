package client.controller;

import client.network.MethodStringer;

public class FilteringController {
    private static final FilteringController instance = new FilteringController();

    private FilteringController() {
    }

    public static FilteringController getInstance() {
        return instance;
    }

    public boolean addFilter(String type, String value) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(),
                    "addFilter", type, value);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public void removeFilter(String type, String value) {
        try {
            MethodStringer.sampleMethod(getClass(), "removeFilter", type, value);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public boolean removeFilter(String type) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "removeFilter", type);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public void clearFilters() {
        try {
            MethodStringer.sampleMethod(getClass(), "clearFilters");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}