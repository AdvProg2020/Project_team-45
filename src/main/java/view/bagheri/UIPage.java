package view.bagheri;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UIPage {
    protected String name;
    protected static Scanner scanner;
    protected static Matcher matcher;


    protected UIPage(String name) {
        this.name = name;
    }

    public static void setScanner(Scanner scanner) {
        UIPage.scanner = scanner;
    }

    protected static Matcher getMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (!matcher.matches())
            return null;
        return matcher;
    }

    protected void show() {
    }

    protected abstract void execute();

    protected abstract String getType();
}
