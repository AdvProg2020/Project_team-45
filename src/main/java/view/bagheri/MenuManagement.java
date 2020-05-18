package view.bagheri;

import view.nedaei.MainMenu;

import java.util.Scanner;
import java.util.Stack;

public class MenuManagement {
    private static final Stack<Menu> activeMenus = new Stack<>();

    public static void next(Menu activeMenu) {
        activeMenus.push(activeMenu);
    }

    public static void back() {
        if (activeMenus.size()==1) {
            System.out.println();
        } else {
            activeMenus.pop();
        }
    }

    public static void run() {
        UIPage.setScanner(new Scanner(System.in));
        activeMenus.push(MainMenu.getInstance());
        while (true) {
            activeMenus.peek().execute();
        }
    }
}
