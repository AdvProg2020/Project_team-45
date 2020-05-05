package view.hatami;

import controller.AllUsersController;
import view.bagheri.Menu;
import view.bagheri.Panel;

public class UsersManagingMenu extends ManagingMenu {

    public UsersManagingMenu(Menu parent) {
        super("users managing menu", parent);
        this.printer = new AllUsersController();
        submenus.put("view (\\S+)", createOneItemDisplayPanel("user information", printer));
        submenus.put("delete user (\\S+)", getDeleteUserPanel());
        submenus.put("create manager profile", new CreateAdminPanel());
    }


    private Panel getDeleteUserPanel(){
        return new Panel("delete user") {
            @Override
            protected void execute() {
                // TODO
            }
        };
    }

    protected void show() {

    }

    protected void showHelp() {

    }
}

