package controller.managers;

import controller.managers.Deleter;

import java.util.HashMap;

public interface Editor extends Deleter {
    HashMap<String, String> getAvailableFields();
    // TODO : hatami
}
