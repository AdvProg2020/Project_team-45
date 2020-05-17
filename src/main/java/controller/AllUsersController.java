package controller;

import controller.managers.Deleter;
import model.Market;
import model.Product;
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
            if (user.equals(UserController.getActiveUser()))
                continue;
            String userInfo = user.getPersonalInfo().getUsername() + "," + user.getRole() + "\n";
            switch (user.getRole()) {
                case "admin":
                    adminsString.append(userInfo);
                    break;
                case "buyer":
                    buyersString.append(userInfo);
                    break;
                case "seller":
                    sellersString.append(userInfo);
                    break;
            }
        }
        return "username,role\n" + adminsString + sellersString + buyersString;
    }

    public String printDetailedById(String Id) {
        User user = getItemById(Id);
        if (user == null)
            return null;
        return user.getPersonalInfo().toString();
    }

    public void deleteItemById(String Id) throws Exception {
        User user = getItemById(Id);
        if (user.equals(UserController.getActiveUser()))
            throw new Exception("cannot delete yourself!");
        switch (user.getRole()) {
            case "buyer":
                deleteBuyer((Buyer) user);
                break;
            case "seller":
                deleteSeller((Seller) user);
                break;
            case "admin":
                deleteAdmin((Admin) user);
                break;
            default:
                market.removeUserFromAllUsers(user);
        }

    }

    private void deleteAdmin(Admin admin) {
    }

    private void deleteBuyer(Buyer buyer) {
    }

    private void deleteSeller(Seller seller) {
        removeSellerFromProducts(seller);
        removeSellerFromOffs(seller);
    }

    private void removeSellerFromOffs(Seller seller) {
        for (String offId : seller.getListOfOffs().keySet()) {
            market.removeOffById(offId);
        }
    }

    private void removeSellerFromProducts(Seller seller) {
        for (Product product : seller.getAvailableProducts().keySet()) {
            product.removeSeller(seller);
        }
    }

    @Override
    public User getItemById(String Id) {
        return market.getUserByUsername(Id);
    }
}
