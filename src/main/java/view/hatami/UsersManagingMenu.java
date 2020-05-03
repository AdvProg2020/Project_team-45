package view.hatami;

import view.bagheri.Menu;
import view.bagheri.Panel;

public class UsersManagingMenu extends ManagingMenu {

    public UsersManagingMenu(Menu parent) {
        super("users managing menu", parent);
        submenus.put("view (\\S+)", getUserDisplayPanel());
        submenus.put("delete user (\\S+)", getDeleteUserPanel());
        submenus.put("create manager profile", new CreateAdminPanel());
    }

    private Panel getUserDisplayPanel(){
        return new Panel("User information") {
            @Override
            protected void show() {
                super.show();
                System.out.println(controller.displayUserForAdmin(matcher.group(1)));
            }
        };
    }

    private Panel getDeleteUserPanel(){
        return new Panel("delete user") {
            @Override
            protected void execute() {
                // TODO
            }
        };
    }

    @Override
    protected void show() {

    }

    protected void showHelp() {

    }
}

