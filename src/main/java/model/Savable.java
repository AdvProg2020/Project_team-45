package model;

import java.util.HashMap;

public interface Savable {
    HashMap convertToHashMap();
    void setFieldsFromHashMap(HashMap theMap);

}
