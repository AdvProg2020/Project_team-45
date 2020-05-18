package controller.managers;

import controller.InputValidator;

import java.util.HashMap;
import java.util.LinkedHashMap;

public interface Creator extends Manager {
    LinkedHashMap<String, InputValidator> getNecessaryFieldsToCreate();
    LinkedHashMap<String, InputValidator> getOptionalFieldsToCreate();
    void createItem(HashMap<String, String> filledFeatures);
}
