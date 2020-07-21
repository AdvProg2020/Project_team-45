package client.controller;

import client.controller.managers.Manager;
import client.network.MethodStringer;
import server.model.Market;
import server.model.Off;

import java.util.HashMap;
import java.util.List;

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
        // TODO : must be deleted
        return Market.getInstance().getOffById(Id);
    }

    // TODO : edit come here

    public Off getCurrentOff() {
        return currentOff;
    }

    public void setCurrentOff(Off currentOff) {
        this.currentOff = currentOff;
    }

    public HashMap<String, String> getOffInfo(String offId) {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getOffInfo", offId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public List<String> getProductsList(String viewingOffId) {
        try {
            return (List<String>) MethodStringer.sampleMethod(getClass(), "getProductsList", viewingOffId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
