package client.controller.userControllers;

import client.controller.CodedDiscountController;
import client.controller.managers.Deleter;
import server.model.CodedDiscount;
import server.model.Market;
import server.model.product.Product;
import server.model.user.Admin;
import server.model.user.Buyer;
import server.model.user.Seller;
import server.model.user.User;

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

    public boolean deleteItemById(String Id) throws Exception {
        User removingUser = getItemById(Id);
        if (removingUser == null)
            return false;
        removeUser(removingUser);
        return true;
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

    public List<String> getAllBuyersNames() {
        List<User> allUsersList = market.getAllUsers();
        return allUsersList.stream()
                .filter(user -> user.getRole().equals("buyer"))
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    @Override
    public User getItemById(String Id) {
        return market.getUserByUsername(Id);
    }

    public String getUsernameById(String userId) {
        return market.getUserById(userId).getUsername();
    }

    public boolean noAdmin() {
        return market.noAdmin();
    }

    public List<String> getAllUsernames() {
        return market.getAllUsers().stream().map(User::getUsername).collect(Collectors.toList());
    }
}
