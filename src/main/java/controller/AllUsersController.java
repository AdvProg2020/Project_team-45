package controller;

import controller.managers.Deleter;
import model.Market;
import model.user.Admin;
import model.user.Buyer;
import model.user.Seller;
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
            if (user.equals(activeUser))
                continue;
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

    public String printDetailedById(String Id) throws Exception {
        User user = market.getUserByUsername(Id);
        if (user == null)
            throw new Exception("user doesn't exist");
        return user.getPersonalInfo().toString();
    }

    public void deleteItemById(String Id) throws Exception {
        User user = market.getUserByUsername(Id);
        if (user.equals(activeUser))
            throw new Exception("cannot delete yourself!");
        if (user.getRole().equals("buyer"))
            deleteBuyer((Buyer) user);
        else if (user.getRole().equals("seller"))
            deleteSeller((Seller) user);
        else if (user.getRole().equals("admin"))
            deleteAdmin((Admin) user);
    }

    private void deleteAdmin(Admin admin) {
        market.removeUserFromAllUsers(admin);
    }

    private void deleteSeller(Seller seller) {
        removeFromProducts(seller);
    }

    private void removeFromProducts(Seller seller) {
        seller.
    }

    private void deleteBuyer(Buyer buyer) {
        market.removeUserFromAllUsers(buyer);
    }
}
