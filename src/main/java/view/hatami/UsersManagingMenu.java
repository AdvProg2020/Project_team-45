package view.hatami;

import controller.AllUsersController;
import controller.AdminController;
import controller.managers.Deleter;
import controller.managers.Printer;

public class UsersManagingMenu extends ManagingMenu {
    public UsersManagingMenu() {
        super("users managing menu");
        this.manager = AllUsersController.getInstance();
        submenus.put("view (\\S+)", createOneItemDisplayPanel("user information", (Printer) manager));
        submenus.put("delete user (\\S+)", createItemDeleterPanel("delete user", (Deleter) manager));
        submenus.put("create manager profile", createItemCreatorPanel("create admin", AdminController.getInstance()));
    }

    protected void showHelp() {

    }
}

