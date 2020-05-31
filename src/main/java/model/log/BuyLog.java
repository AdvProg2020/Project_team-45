package model.log;

import model.Market;
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
        HashMap<String, Object> result = new HashMap<>();
        result.put("mainLog", mainLog.getId());
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap theMap) {
        mainLog = Market.getInstance().getLogById((String) theMap.get("mainLog"));
    }
}
