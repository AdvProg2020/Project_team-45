package client.controller;

import client.network.MethodStringer;
import server.model.Off;

import java.util.HashMap;
import java.util.List;

public class OffController {
    private static final OffController instance = new OffController();
    private Off currentOff;

    private OffController() {
    }

    public static OffController getInstance() {
        return instance;
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

    public boolean offIdExists(String id) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "offIdExists", id);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }
}
