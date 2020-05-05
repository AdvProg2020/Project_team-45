package controller;

import java.util.HashMap;

public interface Editor extends Deleter{
    HashMap<String, String> getAvailableFields();
    // TODO : hatami
}
