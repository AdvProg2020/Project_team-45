package client.controller.userControllers;

import client.controller.managers.Deleter;
import client.network.MethodStringer;
import server.model.Market;
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
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "deleteItemById", Id);
        }catch (Exception e) {
            throw e;
        }catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
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
