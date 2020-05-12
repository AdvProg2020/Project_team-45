package controller;

import controller.managers.Deleter;

public class AllUsersController implements Deleter {

    private static AllUsersController instance = new AllUsersController();

    public static AllUsersController getInstance() {
        return instance;
    }

    private AllUsersController(){
    }

    public String printAllInList() {
        // TODO : hatami
        return null;
    }

    public String printDetailedById(String Id) {
        // TODO : hatami
        return null;
    }

    public void deleteItemById(String Id) {
        // TODO : hatami
    }
}
