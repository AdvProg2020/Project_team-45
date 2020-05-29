package model.log;

import model.Savable;

import java.util.HashMap;

public class BuyLog implements Savable {
    private Log mainLog;

    public BuyLog() {
    }

    public BuyLog(Log log) {
        this.mainLog = log;
    }

    public Log getMainLog() {
        return mainLog;
    }


    @Override
    public HashMap convertToHashMap() {
        return null;
    }

    @Override
    public void setFieldsFromHashMap(HashMap theMap) {

    }
}
