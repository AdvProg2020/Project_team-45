package view;


import controller.MainController;
import model.user.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UIPage {
    protected static User activeUser;
    protected static model.user.AnonymousUser anonymousUser;
    protected static MainController controller;
    protected String name;
    protected static Menu activeMenu;
    protected java.util.Scanner scanner;


    protected UIPage(String name) {
        this.name = name;
    }

    protected static void setActiveMenu(Menu activeMenu) {
        UIPage.activeMenu = activeMenu;
    }

    protected static void setActiveUser(User activeUser) {
        activeUser = activeUser;
    }

    public static void setController(MainController controller) {
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
