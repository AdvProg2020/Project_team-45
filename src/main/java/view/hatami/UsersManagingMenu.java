package view.hatami;

import controller.AllUsersController;
import controller.Deleter;

public class UsersManagingMenu extends ManagingMenu {
    public UsersManagingMenu() {
        super("users managing menu");
        this.printer = AllUsersController.getInstance();
        submenus.put("view (\\S+)", createOneItemDisplayPanel("user information", printer));
        submenus.put("delete user (\\S+)", createItemDeleterPanel("delete user", (Deleter) printer));
        submenus.put("create manager profile", new CreateAdminPanel());
    }

    protected void showHelp() {

    }
}

