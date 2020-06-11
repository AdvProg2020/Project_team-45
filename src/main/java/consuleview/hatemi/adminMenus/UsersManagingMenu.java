package consuleview.hatemi.adminMenus;

import controller.managers.Deleter;
import controller.managers.Printer;
import controller.userControllers.AdminController;
import controller.userControllers.AllUsersController;
import controller.userControllers.UserController;
import consuleview.ManagingMenu;

public class UsersManagingMenu extends ManagingMenu {
    public UsersManagingMenu() {
        super("users managing menu");
        this.manager = AllUsersController.getInstance();
        this.managingObject = "Users";
        submenus.put("view (\\S+)", createOneItemDisplayPanel("user information", (Printer) manager));
        submenus.put("delete user (\\S+)", createItemDeleterPanel("delete user", (Deleter) manager));
        submenus.put("create manager profile", createItemCreatorPanel("create admin", AdminController.getInstance()));
    }

    @Override
    public void execute() {
        if (!UserController.isAdminLoggedIn()) {
            back();
            return;
        }
        super.execute();
    }

    @Override
    protected void showHelp() {
        super.showHelp();
    }
}