package controller.managers;

import java.util.ArrayList;
import java.util.HashMap;

public interface Creator extends Manager {
    HashMap<String, String> getNecessaryFields();
}
