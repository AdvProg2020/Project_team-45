package view;

import Controller.Controller;
import model.User;

public class UI {
    private static User ActiveUser;
    protected String name;
    protected static Controller controller;

    public UI(String name) {
        this.name = name;
    }

    public static void setActiveUser(User activeUser) {
        ActiveUser = activeUser;
    }

    public static void setController(Controller controller) {
        UI.controller = controller;
    }

    public void show(){

    }

    public void execute(){

    }
}
