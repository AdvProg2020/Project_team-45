package controller;

import model.Company;
import model.Product;
import model.log.SellLog;
import model.request.AddProductRequest;
import model.request.ProductEditionRequest;
import model.request.RemoveProductRequest;
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
    private ArrayList<String> existingProductFieldsToCreate;
    private ArrayList<String> newProductFieldsToCreate;

    private UserController() {
        this.productAvailableFieldsToEdit = new ArrayList<String>();
        this.productAvailableFieldsToEdit.addAll(Arrays.asList("price", "stock", "offId"));

        this.existingProductFieldsToCreate = new ArrayList<String>();
        this.existingProductFieldsToCreate.addAll(Arrays.asList("productId", "price", "stock", "offId('-' for none)"));
        this.newProductFieldsToCreate = new ArrayList<String>();
        this.newProductFieldsToCreate.addAll(Arrays.asList("name", "category", "description", "price", "stock"
                , "offId ('-' for none)"));
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
        return "log id = '" + sellLog.getMainLog().getLogId() + "'\n" +
                "date = " + sellLog.getMainLog().getDate() + "\n" +
                "buyerUsername = '" + sellLog.getBuyerUsername() + "'\n" +
                "listOfProducts = '" + sellLog.getMainLog().getSellingProducts() + "'\n" +
                "offAmount = '" + sellLog.getOffAmount() + "'\n" +
                "finalPrice = '" + sellLog.getMainLog().getFinalPrice() + "'\n" +
                "address = '" + sellLog.getMainLog().getAddress() + "'\n" +
                "phoneNumber = '" + sellLog.getMainLog().getPhoneNumber() + "'\n" +
                "deliveryStatus = '" + sellLog.getMainLog().getDeliveryStatus() + "'";
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

    public Product getAvailableProductById(String productId) {
        return ((Seller) activeUser).getAvailableProductById(productId);
    }

    // products managing menu

    public String getSellerProductDisplayById(String productId) {
        Product product = getAvailableProductById(productId);
        if (product == null) {
            return null;
        }
        return "productId = '" + product.getProductId() + "'\n" +
                "name = '" + product.getName() + "'\n" +
                "productStatus = " + product.getProductStatus() + "\n" +
                "minimumPrice = " + product.getMinimumPrice() + "\n" +
                "category = " + product.getCategory().getName() + "\n" +
                "categoryFeatures = " + product.getCategoryFeatures() + "\n" +
                "description = '" + product.getDescription() + "'\n" +
                "averageScore = " + product.getAverageScore() + "\n" +
                "sellCount = " + product.getSellerInfoForProductByUsername(activeUser.getPersonalInfo().getUsername())
                        .getSellCount();
    }

    public String getSellerProductAllBuyersDisplayById(String productId) {
        Product product = getAvailableProductById(productId);
        if (product == null) {
            return null;
        }
        return "" + product.getSellerInfoForProductByUsername(activeUser.getPersonalInfo().getUsername()).getAllBuyers();
    }

    public boolean isProductFieldAvailableToEdit(String productId, String field) {
        return (productAvailableFieldsToEdit.contains(field)
                || getAvailableProductById(productId).getCategoryFeatures().keySet().contains(field));
    }

    public void createProductEditionRequest(String productId, HashMap<String, String> fieldsAndValues) {
        Admin.getListOfRequests().add(new ProductEditionRequest(productId, fieldsAndValues));
    }

    public String getProductAvailableFieldsToEditDisplay() {
        return "" + productAvailableFieldsToEdit;
    }

    // add product panel

    public ArrayList<String> getExistingProductFieldsToCreate() {
        return existingProductFieldsToCreate;
    }

    public ArrayList<String> getNewProductFieldsToCreate() {
        return newProductFieldsToCreate;
    }

    public void createAddProductRequest(HashMap<String, String> fieldsAndValues) {
        Admin.getListOfRequests().add(new AddProductRequest(fieldsAndValues));
    }

    // remove product panel

    public void createRemoveProductRequest(String productId) {
        if (getAvailableProductById(productId) == null) {
            return;
        }
        Admin.getListOfRequests().add(new RemoveProductRequest(productId));
    }

    // view balance panel

    public int getSellerBalance() {
        return ((Seller)activeUser).getBalance();
    }

    //================================================================================
    // 3. buyer menu operations
    //================================================================================


    public boolean logout() {
        return false;
    }
}
