package controller;

import controller.managers.Deleter;
import model.Market;
import model.user.User;
import java.util.ArrayList;

public class AllUsersController implements Deleter {

    private Market market;

    private static AllUsersController instance = new AllUsersController();

    public static AllUsersController getInstance() {
        return instance;
    }

    private AllUsersController(){
        this.market = Market.getInstance();
    }

    public String getAllInListAsString() {
        ArrayList<User> allUsers = market.getAllUsers();
        StringBuilder adminsString = new StringBuilder();
        StringBuilder buyersString = new StringBuilder();
        StringBuilder sellersString = new StringBuilder();
        for (User user : allUsers) {
            String userInfo = user.getPersonalInfo().getUsername() + "," + user.getRole() + "\n";
            if (user.getRole().equals("admin"))
                adminsString.append(userInfo);
            else if (user.getRole().equals("buyer"))
                buyersString.append(userInfo);
            else if (user.getRole().equals("seller"))
                sellersString.append(userInfo);
        }
        return "username,role\n" + adminsString + sellersString + buyersString;
    }

    public String printDetailedById(String Id) {
        User user = market.getUserByUsername(Id);
        return user.getPersonalInfo().toString();
    }

    public void deleteItemById(String Id) {
        // TODO : hatami
    }
}
