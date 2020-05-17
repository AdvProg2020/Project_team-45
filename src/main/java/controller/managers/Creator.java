package controller.managers;

import controller.InputValidator;

import java.util.HashMap;

public interface Creator extends Manager {
    HashMap<String, InputValidator> getNecessaryFieldsToCreate();
    HashMap<String, InputValidator> getOptionalFieldsToCreate();
    void createItem(HashMap<String, String> filledFeatures);
}
