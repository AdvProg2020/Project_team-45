package view;

public class UsersManagingMenu extends ManagingMenu {

    public UsersManagingMenu(String name, Menu parent) {
        managingObjects = controller.getUsersMap();
    }


    private void deleteUser(String username) {
    }

    private void createAdmin() {
    }
}

