package model;

import java.util.HashMap;

public interface Savable {
    HashMap ConvertToHashMap();
    void setFieldsFromHashMap(HashMap theMap);

}
