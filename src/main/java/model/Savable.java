package model;

import java.util.HashMap;

public interface Savable {
    default HashMap<String, String> convertToHashMap() {
        return new HashMap<>();
    }
    default void setFieldsFromHashMap(HashMap<String, String> theMap) {

    }
}
