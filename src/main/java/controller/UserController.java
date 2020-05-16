package controller;

import model.*;
import model.log.BuyLog;
import model.log.Log;
import model.log.SellLog;
import model.request.*;
import model.user.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UserController {
    private static final UserController instance = new UserController();
    private User activeUser;
    private boolean loggedIn;

    // seller:
    private ArrayList<String> productAvailableFieldsToEdit;
    private ArrayList<String> existingProductFieldsToCreate;
    private ArrayList<String> newProductFieldsToCreate;
    private ArrayList<String> offAvailableFieldsToEdit;
    private ArrayList<String> offFieldsToCreate;

    // buyer:
    private ArrayList<String> buyLogInformationToReceive;
    private Log log;

    private UserController() {
        this.productAvailableFieldsToEdit = new ArrayList<>();
        this.productAvailableFieldsToEdit.addAll(Arrays.asList("price", "stock", "offId"));

        this.existingProductFieldsToCreate = new ArrayList<>();
        this.existingProductFieldsToCreate.addAll(Arrays.asList("productId", "price", "stock", "offId('-' for none)"));
        this.newProductFieldsToCreate = new ArrayList<>();
        this.newProductFieldsToCreate.addAll(Arrays.asList("name", "category", "description", "price", "stock"
                , "offId ('-' for none)"));

        this.offAvailableFieldsToEdit = new ArrayList<>();
        this.offAvailableFieldsToEdit.addAll(Arrays.asList("startTime", "endTime", "discountAmount"));

        this.offFieldsToCreate = new ArrayList<>();
        this.offFieldsToCreate.addAll(Arrays.asList("productsList(separated by ',')", "startTime", "endTime"
                , "discountAmount"));

        this.buyLogInformationToReceive = new ArrayList<>();
        this.buyLogInformationToReceive.addAll(Arrays.asList("address", "phoneNumber"));
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
                "buyerUsername = '" + sellLog.getMainLog().getBuyerUsername() + "'\n" +
                "listOfProducts = '" + sellLog.getMainLog().getSellingProducts() + "'\n" +
                "appliedDiscount = '" + sellLog.getMainLog().getAppliedDiscountPercentage() + "%'\n" +
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

    private Product getSellerAvailableProductById(String productId) {
        return ((Seller) activeUser).getAvailableProductById(productId);
    }

    // products managing menu

    public String getSellerProductDisplayById(String productId) {
        Product product = getSellerAvailableProductById(productId);
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
        Product product = getSellerAvailableProductById(productId);
        if (product == null) {
            return null;
        }
        return "" + product.getSellerInfoForProductByUsername(activeUser.getPersonalInfo().getUsername()).getAllBuyers();
    }

    public boolean isProductFieldAvailableToEdit(String productId, String field) {
        return (productAvailableFieldsToEdit.contains(field)
                || getSellerAvailableProductById(productId).getCategoryFeatures().keySet().contains(field));
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
        if (getSellerAvailableProductById(productId) == null) {
            return;
        }
        Admin.getListOfRequests().add(new RemoveProductRequest(productId));
    }

    // offs managing menu

    private Off getAvailableOffById(String offId) {
        return ((Seller)activeUser).getOffByOffId(offId);
    }

    public String getSellerOffDisplayById(String offId) {
        Off off = getAvailableOffById(offId);
        if (off == null) {
            return null;
        }
        return "productList = " + off.getProductsList() + "\n" +
                "starTime = " + off.getStartTime() + "\n" +
                "endTime = " + off.getEndTime() + "\n" +
                "discountAmount = " + off.getDiscountAmount();
    }

    public void createOffEditionRequest(String offId, HashMap<String, String> fieldsAndValues) {
        Off off = getAvailableOffById(offId);
        if (off == null) {
            return;
        }
        Admin.getListOfRequests().add(new OffEditionRequest(offId, fieldsAndValues));
    }

    public ArrayList<String> getOffAvailableFieldsToEdit() {
        return offAvailableFieldsToEdit;
    }

    public ArrayList<String> getOffFieldsToCreate() {
        return offFieldsToCreate;
    }

    public void createAddOffRequest(HashMap<String, String> fieldsAndValues) {
        Admin.getListOfRequests().add(new AddOffRequest(fieldsAndValues));
    }

    // view balance panel

    public int getSellerBalance() {
        return ((Seller)activeUser).getBalance();
    }

    //================================================================================
    // 3. buyer menu operations
    //================================================================================

    // cart managing menu

    public HashMap<String, Integer> getCartProductsList() {
        return  ((Buyer)activeUser).getCart().getProductsDisplay();
    }

    public void increaseCartProductById(String productId) {
        Cart cart = ((Buyer)activeUser).getCart();
        cart.changeProductAmountById(productId, 1);
    }

    public void decreaseCartProductById(String productId) {
        Cart cart = ((Buyer)activeUser).getCart();
        cart.changeProductAmountById(productId, -1);
    }

    public int getCartTotalPrice() {
        return ((Buyer)activeUser).getCart().getTotalPrice();
    }

    // purchase panel

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public ArrayList<String> getBuyLogInformationToReceive() {
        return buyLogInformationToReceive;
    }

    public void createNewLog(HashMap<String, String> fieldsAndValues) {
        ArrayList<ProductSellInfo> sellingProducts = ((Buyer)activeUser).getCart().getProductSellInfos();
        log = new Log(sellingProducts, activeUser.getPersonalInfo().getUsername()
                , fieldsAndValues.get("address"), fieldsAndValues.get("phoneNumber"));
    }

    public boolean isDiscountCodeValid(String discountCode) {
        return Market.getInstance().isDiscountCodeValid(discountCode);
    }

    public void applyDiscountCode(String discountCode) {
        log.setAppliedDiscount(Market.getInstance().getCodedDiscountByCode(discountCode));
    }

    public boolean canPurchase() {
        Buyer buyer = (Buyer)activeUser;
        return buyer.getCart().getTotalPrice() <= buyer.getBalance();
    }

    public void purchase() {
        Buyer buyer = (Buyer)activeUser;
        // TODO: nedaei
    }

    // orders managing menu

    public String getBuyerBuyLogDisplayById(String logId) {
        BuyLog buyLog = ((Buyer)activeUser).getBuyLogById(logId);
        if (buyLog == null) {
            return null;
        }
        return
    }

    public boolean logout() {
        return false;
    }
}
