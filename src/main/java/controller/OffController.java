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

    @Override
    public Off getItemById(String Id) {
        return Market.getInstance().getOffById(Id);
    }

    // TODO : edit come here

    public Off getCurrentOff() {
        return currentOff;
    }

    public void setCurrentOff(Off currentOff) {
        this.currentOff = currentOff;
    }
}
