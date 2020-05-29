package model;

import java.util.HashMap;

public interface Savable {
    HashMap<String, Object> ConvertToHashMap();
    void setFieldsFromHashMap(HashMap<String, Object> theMap);

}
