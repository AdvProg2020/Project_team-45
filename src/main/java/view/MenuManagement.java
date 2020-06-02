package view;

import com.sun.corba.se.impl.orb.DataCollectorBase;
import controller.DatabaseController;
import controller.userControllers.AdminController;
import model.Market;
import model.MarketCopier;
import view.userMenus.adminMenus.UsersManagingMenu;

import javax.xml.crypto.Data;
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
        Market.getInstance().initialize();
        UIPage.setScanner(new Scanner(System.in));
        while (Market.getInstance().getAllUsers().isEmpty()) {
            runStartingPanel();
        }
        activeMenus.push(MainMenu.getInstance());
        while (!isExit) {
            activeMenus.peek().execute();
        }
        DatabaseController.getInstance().writeToDatabase();
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
