package view;


import controller.MainController;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UIPage {
    protected static MainController controller;
    protected String name;
    protected static Menu activeMenu;
    protected static Scanner scanner;


    protected UIPage(String name) {
        this.name = name;
    }

    //useless{
    protected static void setActiveMenu(Menu activeMenu) {
        UIPage.activeMenu = activeMenu;
    }
    //}

    //Needs thinking{
    public static void setController(MainController controller) {
        if (UIPage.controller == null)
            UIPage.controller = controller;
    }
    //}

    protected static Matcher getMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (!matcher.matches())
            return null;
        return matcher;
    }

    protected void show() {
        System.out.println(this.name + " :\n");
    }

    protected abstract void execute();

    public abstract String getType();

}
