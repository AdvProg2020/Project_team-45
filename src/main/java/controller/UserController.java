package controller;

import model.Company;
import model.log.SellLog;
import model.user.PersonalInfo;
import model.user.Seller;
import model.user.User;

import java.util.ArrayList;

public class UserController {
    private static UserController instance;
    private User activeUser;

    private UserController() {
    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    //================================================================================
    // personal info
    //================================================================================

    public void setPersonalInfoField(String field, String newValue) {
        activeUser.getPersonalInfo().setField(field, newValue);
    }

    public String getPersonalInfoDisplay() {
        PersonalInfo temporaryPersonalInfo = activeUser.getPersonalInfo();
        return "username = '" + temporaryPersonalInfo.getUsername() + "'\n" +
                "first name = '" + temporaryPersonalInfo.getFirstName() + "'\n" +
                "last name = '" + temporaryPersonalInfo.getLastName() + "'\n" +
                "email address = '" + temporaryPersonalInfo.getEmailAddress() + "'\n" +
                "phone number = '" + temporaryPersonalInfo.getPhoneNumber() + "'";
    }

    //================================================================================
    // seller menu operations
    //================================================================================

    public String getCompanyDisplayForSeller() {
        Company temporaryCompany = ((Seller) activeUser).getCompany();
        return "name = '" + temporaryCompany.getName() + "'\n" +
                "other information = '" + temporaryCompany.getOtherInformation() + "'";
    }

    private String getSellLogDisplay(SellLog sellLog) {
        return "log id = '" + sellLog.getLog().getLogId() + "'\n" +
                "date = " + sellLog.getLog().getDate() + "\n" +
                "buyer username = '" + sellLog.getBuyerUsername() + "'\n" +
                "list of products = '" + sellLog.getLog().getListOfProducts() + "'\n" +
                "off amount = '" + sellLog.getOffAmount() + "'\n" +
                "final price = '" + sellLog.getLog().getFinalPrice() + "'\n" +
                "address = '" + sellLog.getLog().getAddress() + "'\n" +
                "phone number = '" + sellLog.getLog().getPhoneNumber() + "'\n" +
                "delivery status = '" + sellLog.getLog().getDeliveryStatus() + "'";
    }

    public String getSalesHistoryDisplayForSeller() {
        ArrayList<SellLog> temporaryListOfSellLogs = ((Seller) activeUser).getListOfSellLogs();
        StringBuilder result = new StringBuilder();
        for (SellLog sellLog : temporaryListOfSellLogs) {
            result.append(getSellLogDisplay(sellLog) + "\n");
        }
        result.replace(result.length()-1, result.length(), "");
        return result.toString();
    }



//    public User createUser() {
//        return null;
//    }
//
//    public void setFieldOfUserOrDownCast(User user, String field, String value) {}
//
//    public User loginUserByUsernameAndPassword(String username, String password) {
//        return null;
//    }
//
//    public User getUserByUsername(String username) {
//        return null;
//    }
//
//    public void deleteUserByUsername(String username) {
//    }
//
//    public void acceptOrDeclineRequest(String requestId, String statusToSet) {
//    }
//
//    public ArrayList<SellLog> getSellerSalesHistory(User user) {
//        return null;
//    }
//
//    public Product getSellerProductById(String productId) {
//        return null;
//    }
//
//    public ArrayList<BuyLog> getBuyerOrders(User user) {
//        return null;
//    }
//
//    public ArrayList<CodedDiscount> getBuyerCodedDiscounts(User user) {
//        return null;
//    }
//
//    public void rateProduct(User user, Product product, int rate) {
//    }
//
//    public void makeCommentRequest(User user, Product product, String title, String content) {
//    }

}
