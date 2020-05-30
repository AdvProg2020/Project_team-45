package model;

import java.util.HashMap;

public interface Savable {
    HashMap<String, Object> convertToHashMap();
    void setFieldsFromHashMap(HashMap<String, Object> theMap);

}
