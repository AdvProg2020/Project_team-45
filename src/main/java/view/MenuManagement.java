package view;

import java.util.Scanner;

public class MenuManagement {
    private static Menu activeMenu;

    public static void setActiveMenu(Menu activeMenu) {
        MenuManagement.activeMenu = activeMenu;
    }

    public static void run() {
        UIPage.setScanner(new Scanner(System.in));
        activeMenu = MainMenu.getInstance;
        while (true) {
            activeMenu.execute();
        }
    }
}
