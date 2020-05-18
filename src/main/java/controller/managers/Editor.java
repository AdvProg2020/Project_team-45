package controller.managers;

import controller.InputValidator;

import java.util.HashMap;

public interface Editor extends Deleter {
    HashMap<String, InputValidator> getAvailableFieldsToEdit();
    void editItem(Object editingObject, HashMap<String, String> changedFields);
}
