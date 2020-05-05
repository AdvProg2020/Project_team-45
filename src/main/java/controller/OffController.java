package controller;

import model.Off;

public class OffController {
    private static OffController instance = new OffController();

    private OffController() {

    }

    public static OffController getInstance() {
        return instance;
    }

    public Off createOff() {
        return null;
    }

    public void setFieldOfOff(Off off, String field, String value) {
    }

    public Off getOffById(String offId) {
        return null;
    }
}
