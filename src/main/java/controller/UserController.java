package controller;

import model.Company;
import model.Product;
import model.log.SellLog;
import model.request.ProductEditionRequest;
import model.user.Admin;
import model.user.PersonalInfo;
import model.user.Seller;
import model.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UserController {
    private static final UserController instance = new UserController();
    private User activeUser;
    // seller:
    private ArrayList<String> productAvailableFieldsToEdit;

    private UserController() {
        String[] temp = {"price", "stock", "off"}; // off can be changed by entering new off id
        this.productAvailableFieldsToEdit.addAll(Arrays.asList(temp));
    }

    public static UserController getInstance() {
        return instance;
    }

    //================================================================================
    // 1. personal info
    //================================================================================

    public void setPersonalInfoField(String field, String newValue) {
        PersonalInfo personalInfo = activeUser.getPersonalInfo();
        if (field.equalsIgnoreCase("firstName")) {
            personalInfo.setFirstName(newValue);
        } else if (field.equalsIgnoreCase("lastName")) {
            personalInfo.setLastName(newValue);
        } else if (field.equalsIgnoreCase("emailAddress")) {
            personalInfo.setEmailAddress(newValue);
        } else if (field.equalsIgnoreCase("phoneNumber")) {
            personalInfo.setPhoneNumber(newValue);
        } else if (field.equalsIgnoreCase("password")) {
            personalInfo.setPassword(newValue);
        } // TODO: else -> throw Exception...
    }

    public String getPersonalInfoDisplay() {
        PersonalInfo personalInfo = activeUser.getPersonalInfo();
        return "username = '" + personalInfo.getUsername() + "'\n" +
                "firstName = '" + personalInfo.getFirstName() + "'\n" +
                "lastName = '" + personalInfo.getLastName() + "'\n" +
                "emailAddress = '" + personalInfo.getEmailAddress() + "'\n" +
                "phoneNumber = '" + personalInfo.getPhoneNumber() + "'";
    }

    //================================================================================
    // 2. seller menu operations
    //================================================================================

    public String getCompanyDisplayForSeller() {
        Company company = ((Seller) activeUser).getCompany();
        return "name = '" + company.getName() + "'\n" +
                "otherInformation = '" + company.getOtherInformation() + "'";
    }

    private String getSellLogDisplay(SellLog sellLog) {
        return "log id = '" + sellLog.getLog().getLogId() + "'\n" +
                "date = " + sellLog.getLog().getDate() + "\n" +
                "buyerUsername = '" + sellLog.getBuyerUsername() + "'\n" +
                "listOfProducts = '" + sellLog.getLog().getListOfProducts() + "'\n" +
                "offAmount = '" + sellLog.getOffAmount() + "'\n" +
                "finalPrice = '" + sellLog.getLog().getFinalPrice() + "'\n" +
                "address = '" + sellLog.getLog().getAddress() + "'\n" +
                "phoneNumber = '" + sellLog.getLog().getPhoneNumber() + "'\n" +
                "deliveryStatus = '" + sellLog.getLog().getDeliveryStatus() + "'";
    }

    public String getSalesHistoryDisplayForSeller() {
        ArrayList<SellLog> listOfSellLogs = ((Seller) activeUser).getListOfSellLogs();
        StringBuilder result = new StringBuilder();
        for (SellLog sellLog : listOfSellLogs) {
            result.append(getSellLogDisplay(sellLog) + "\n");
        }
        result.replace(result.length()-1, result.length(), "");
        return result.toString();
    }

    // 2.1. products managing menu

    public String getSellerProductDisplayById(String productId) {
        Product product = ((Seller) activeUser).getAvailableProductById(productId);
        if (product == null) {  // TODO: throw exception
            return null;
        }
        return "productId = '" + product.getProductId() + "'\n" +
                "name = '" + product.getName() + "'\n" +
                "company = " + product.getCompany().getName() + "\n" +
                "productStatus = " + product.getProductStatus() + "\n" +
                "minimumPrice = " + product.getMinimumPrice() + "\n" +
                "category = " + product.getCategory().getName() + "\n" +
                "categoryFeatures = " + product.getCategoryFeatures() + "\n" +
                "description = '" + product.getDescription() + "'\n" +
                "averageScore = " + product.getAverageScore() + "\n" +
                "sellCount = " + product.getSellerInfoForProductByUsername(activeUser.getPersonalInfo().getUsername())
                        .getSellCount();
    }

    public String getSellerProductAllBuyersDisplayById(String productId) { // TODO: throw exception
        return "" + ((Seller) activeUser).getAvailableProductById(productId)
                .getSellerInfoForProductByUsername(activeUser.getPersonalInfo().getUsername()).getAllBuyers();
    }

    public void createProductEditionRequest(String productId, HashMap<String, String> fieldsAndValues) {
        Admin.getListOfRequests().add(new ProductEditionRequest(productId, fieldsAndValues)); // TODO: throw exception
    }

    public String getProductAvailableFieldsToEditDisplay() {
        return "" + productAvailableFieldsToEdit;
    }

    //================================================================================
    // buyer menu operations
    //================================================================================

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

    public boolean logout() {
        return false;
    }
}
