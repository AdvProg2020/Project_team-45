package controller;

import model.ProductFilters;
import model.user.User;

import java.util.HashMap;

public class MainController {
    private static MainController instance;
    private boolean loggedIn;

    private MainController() {
        this.loggedIn = false;
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public User getActiveUser() {
        return activeUser;
    }
}
