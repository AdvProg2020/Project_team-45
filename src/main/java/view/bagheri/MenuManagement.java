package view.bagheri;

import controller.userControllers.AdminController;
import model.Market;
import view.hatami.ManagingMenu;
import view.hatami.RegisterPanel;
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
        if (!Market.getInstance().doesHaveAdmin()) {
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
                System.out.println("username");
                String input = scanner.nextLine();
                RegisterPanel.setLastRegisterUsername(input);
                ManagingMenu.createItemCreatorPanel("create first admin", AdminController.getInstance()).execute();
            }
        }.execute();
    }
}
