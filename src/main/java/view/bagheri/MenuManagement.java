package view.bagheri;

import controller.userControllers.AdminController;
import view.hatami.ManagingMenu;
import view.nedaei.MainMenu;

import java.util.Scanner;
import java.util.Stack;

public abstract class MenuManagement extends ManagingMenu{
    private static final Stack<Menu> activeMenus = new Stack<>();
    private static boolean isExit;

    protected MenuManagement(String name) {
        super();
    }

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

    public static void exit() {
        isExit = true;
    }

    public static void run() {
        UIPage.setScanner(new Scanner(System.in));
        runStartingPanel();
        activeMenus.push(MainMenu.getInstance());
        while (!isExit) {
            activeMenus.peek().execute();
        }
    }

    private static void runStartingPanel(){
        new Panel("first admin register"){

            @Override
            public void execute() {
                System.out.println("creating first admin");
                ManagingMenu.createItemCreatorPanel("create first admin", AdminController.getInstance());
            }
        }.execute();
    }
}
