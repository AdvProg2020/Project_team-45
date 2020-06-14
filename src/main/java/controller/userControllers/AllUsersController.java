package controller.userControllers;

import controller.CodedDiscountController;
import controller.managers.Deleter;
import model.CodedDiscount;
import model.Market;
import model.product.Product;
import model.user.Admin;
import model.user.Buyer;
import model.user.Seller;
import model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllUsersController implements Deleter {

    private final Market market;

    private static final AllUsersController instance = new AllUsersController();

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

    public String getDetailStringById(String Id) {
        User showingUser = getItemById(Id);
        if (showingUser == null)
            return null;
        return showingUser.getPersonalInfo().toString();
    }

    public boolean deleteItemById(String Id) throws Exception {
        User removingUser = getItemById(Id);
        if (removingUser == null)
            return false;
        removeUser(removingUser);
        return true;
    }

    @Override
    public boolean justRequests() {
        return false;
    }

    private void removeUser(User removingUser) throws Exception {
        if (removingUser.equals(UserController.getActiveUser()))
            throw new Exception("cannot delete yourself!");
        switch (removingUser.getRole()) {
            case "buyer":
                deleteBuyer((Buyer) removingUser);
                break;
            case "seller":
                deleteSeller((Seller) removingUser);
                break;
            case "admin":
                deleteAdmin((Admin) removingUser);
                break;
        }
        market.removeUserFromAllUsers(removingUser);
    }

    private void deleteAdmin(Admin admin) {
        // no action needed yet //
    }

    private void deleteBuyer(Buyer buyer) {
        deleteBuyerAllDiscounts(buyer);
    }

    private void deleteSeller(Seller seller) {
        removeSellerFromProducts(seller);
        removeSellerFromOffs(seller);
    }

    private void deleteBuyerAllDiscounts(Buyer buyer) {
        for (CodedDiscount codedDiscount : buyer.getListOfCodedDiscounts()) {
            CodedDiscountController.getInstance().deleteItemById(codedDiscount.getCode());
        }
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

    public List<Buyer> getAllBuyers() {
        List<User> allUsersList = market.getAllUsers();
        return allUsersList.stream()
                .filter(user -> user.getRole().equals("buyer"))
                .map(buyer -> (Buyer) buyer)
                .collect(Collectors.toList());
    }

    @Override
    public User getItemById(String Id) {
        return market.getUserByUsername(Id);
    }
}
