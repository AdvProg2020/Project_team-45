package controller;

import controller.managers.Creator;

import java.util.HashMap;

public class AdminController implements Creator {
    private static AdminController instance = new AdminController();

    public static AdminController getInstance() {
        return instance;
    }

    private AdminController() {
    }

    public HashMap<String, String> getNecessaryFields() {
        // TODO : hatami
        return null;
    }
}
