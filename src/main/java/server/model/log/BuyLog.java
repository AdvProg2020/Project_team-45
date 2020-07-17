package server.model.log;

import server.model.Market;
import server.model.Savable;

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
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("mainLog", mainLog.getId());
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        mainLog = Market.getInstance().getLogById(theMap.get("mainLog"));
    }
}