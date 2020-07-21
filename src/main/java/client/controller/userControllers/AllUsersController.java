package client.controller.userControllers;

import client.network.MethodStringer;
import server.model.Market;

import java.util.List;

public class AllUsersController {

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
        try {
            return (List<String>) MethodStringer.sampleMethod(getClass(), "getAllBuyersNames");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String getUsernameById(String userId) {
        try {
            return (String) MethodStringer.sampleMethod(getClass(), "getUsernameById", userId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public boolean noAdmin() {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "noAdmin");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public List<String> getAllUsernames() {
        try {
            return (List<String>) MethodStringer.sampleMethod(getClass(), "getAllUsernames");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
