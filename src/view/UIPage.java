package view;

import controller.Controller;
import model.user.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UIPage {
    protected static User ActiveUser;
    protected static Controller controller;

    protected String name;
    protected static Menu activeMenu;

    protected UIPage(String name) {
        this.name = name;
    }

    protected static void setActiveUser(User activeUser) {
        ActiveUser = activeUser;
    }

    public static void setController(Controller controller) {
        if (UIPage.controller == null)
            UIPage.controller = controller;
    }

    public static Matcher getMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (!matcher.matches())
            return null;
        return matcher;
    }

    protected void show() {
        System.out.println(this.name + " :\n");
    }

    protected abstract void execute();


}
