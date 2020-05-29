package view;

import controller.userControllers.AdminController;
import model.Market;

import java.util.Scanner;
import java.util.Stack;

public abstract class MenuManagement {
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
        while (Market.getInstance().getAllUsers().isEmpty()) {
            runStartingPanel();
            Market.getInstance().setHasAdmin();
        }
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
                UsersManagingMenu.createItemCreatorPanel("create admin", AdminController.getInstance()).execute();
            }
        }.execute();
    }
}
