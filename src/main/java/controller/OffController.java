package controller;

import controller.managers.Manager;
import model.Market;
import model.Off;

public class OffController implements Manager {
    private static final OffController instance = new OffController();
    private Off currentOff;

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

    @Override
    public Off getItemById(String Id) {
        return Market.getInstance().getOffById(Id);
    }

    public Off getCurrentOff() {
        return currentOff;
    }

    public void setCurrentOff(Off currentOff) {
        this.currentOff = currentOff;
    }
}
