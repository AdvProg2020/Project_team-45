package controller.managers;

import java.util.HashMap;

public interface Creator extends Manager {
    HashMap<String, String> getNecessaryFields();
    void createItem(HashMap<String, String> filledFeatures);
}
