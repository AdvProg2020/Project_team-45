package controller.managers;

import controller.InputValidator;

import java.util.HashMap;
import java.util.LinkedHashMap;

public interface Editor extends Deleter {
    LinkedHashMap<String, InputValidator> getAvailableFieldsToEdit();
    void editItem(Object editingObject, HashMap<String, String> changedFields);
}
