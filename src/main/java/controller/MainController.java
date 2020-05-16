package controller;

import model.user.User;

public class MainController {
    private static MainController instance;
    private boolean loggedIn;
    static User activeUser;

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
