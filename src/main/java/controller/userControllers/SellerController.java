package controller.userControllers;

import controller.InputValidator;
import controller.managers.Creator;
import model.Company;
import model.Market;
import model.Off;
import model.Product;
import model.log.SellLog;
import model.request.*;
import model.user.PersonalInfo;
import model.user.Seller;
import model.user.User;
import view.hatami.RegisterPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SellerController extends UserController implements Creator {
    private static final SellerController instance = new SellerController();

    private final ArrayList<String> productAvailableFieldsToEdit;
    private final ArrayList<String> existingProductFieldsToCreate;
    private final ArrayList<String> newProductFieldsToCreate;
    private final ArrayList<String> offAvailableFieldsToEdit;
    private final ArrayList<String> offFieldsToCreate;

    private SellerController() {
        super();
        this.productAvailableFieldsToEdit = new ArrayList<>();
        this.productAvailableFieldsToEdit.addAll(Arrays.asList("price", "stock", "offId"));

        this.existingProductFieldsToCreate = new ArrayList<>();
        this.existingProductFieldsToCreate.addAll(Arrays.asList("productId", "price", "stock", "offId('-' for none)"));
        this.newProductFieldsToCreate = new ArrayList<>();
        this.newProductFieldsToCreate.addAll(Arrays.asList("name", "categoryName", "description", "price", "stock"
                , "offId('-' for none)"));

        this.offAvailableFieldsToEdit = new ArrayList<>();
        this.offAvailableFieldsToEdit.addAll(Arrays.asList("startTime", "endTime", "discountAmount"));

        this.offFieldsToCreate = new ArrayList<>();
        this.offFieldsToCreate.addAll(Arrays.asList("productIds(separated by ',')", "startTime", "endTime"
                , "discountAmount"));
    }

    public static SellerController getInstance() {
        return instance;
    }

    // view company info panel

    public String getCompanyDisplayForSeller() {
        Company company = ((Seller) UserController.getActiveUser()).getCompany();
        return "name = '" + company.getName() + "'\n" +
                "otherInformation = '" + company.getOtherInformation() + "'";
    }

    // view sales history panel

    private String getSellLogDisplay(SellLog sellLog) {
        return "logId = '" + sellLog.getMainLog().getLogId() + "'\n" +
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
        ArrayList<SellLog> listOfSellLogs = ((Seller) UserController.getActiveUser()).getListOfSellLogs();
        StringBuilder result = new StringBuilder();
        for (SellLog sellLog : listOfSellLogs) {
            result.append(getSellLogDisplay(sellLog) + "\n");
        }
        if (result.length() > 0) {
            result.replace(result.length() - 1, result.length(), "");
        }
        return result.toString();
    }

    // products managing menu

    public Product getSellerAvailableProductById(String productId) {
        return ((Seller) UserController.getActiveUser()).getAvailableProductById(productId);
    }

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
                "sellCount = " + product.getSellerInfoForProductByUsername(UserController.getActiveUser()
                .getPersonalInfo().getUsername()).getSellCount();
    }

    public String getSellerProductAllBuyersDisplayById(String productId) {
        Product product = getSellerAvailableProductById(productId);
        if (product == null) {
            return null;
        }
        return "" + product.getSellerInfoForProductByUsername(UserController.getActiveUser().getPersonalInfo()
                .getUsername()).getAllBuyers();
    }

    public boolean isProductFieldAvailableToEdit(String productId, String field) {
        return (productAvailableFieldsToEdit.contains(field)
                || getSellerAvailableProductById(productId).getCategoryFeatures().containsKey(field));
    }

    public void createProductEditionRequest(String productId, HashMap<String, String> fieldsAndValues) {
        Market.getInstance().getAllRequests().add(new ProductEditionRequest(productId, ((Seller)UserController.getActiveUser())
                , fieldsAndValues));
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

    public void createAddProductRequest(String mode, Seller seller, HashMap<String, String> fieldsAndValues) {
        Market.getInstance().getAllRequests().add(new AddProductRequest(mode, seller, fieldsAndValues));
    }

    // remove product panel

    public void createRemoveProductRequest(String productId) {
        if (getSellerAvailableProductById(productId) == null) {
            return;
        }
        Market.getInstance().getAllRequests()
                .add(new RemoveProductRequest(((Seller)UserController.getActiveUser()), productId));
    }

    // offs managing menu

    private Off getAvailableOffById(String offId) {
        return ((Seller) UserController.getActiveUser()).getOffByOffId(offId);
    }

    public String getSellerOffDisplayById(String offId) {
        Off off = getAvailableOffById(offId);
        if (off == null) {
            return null;
        }
        return "productList = " + off.getProductsList() + "\n" +
                "startTime = " + off.getStartTime() + "\n" +
                "endTime = " + off.getEndTime() + "\n" +
                "discountAmount = " + off.getDiscountAmount();
    }

    public ArrayList<String> getOffAvailableFieldsToEdit() {
        return offAvailableFieldsToEdit;
    }

    public void createOffEditionRequest(String offId, HashMap<String, String> fieldsAndValues) {
        Off off = getAvailableOffById(offId);
        if (off == null) {
            return;
        }
        Market.getInstance().getAllRequests().add(new OffEditionRequest(((Seller)UserController.getActiveUser()), offId
                , fieldsAndValues));
    }

    public ArrayList<String> getOffFieldsToCreate() {
        return offFieldsToCreate;
    }

    public void createAddOffRequest(HashMap<String, String> fieldsAndValues) {
        Market.getInstance().getAllRequests().add(new AddOffRequest(((Seller)UserController.getActiveUser()), fieldsAndValues));
    }

    // view balance panel

    public int getSellerBalance() {
        return ((Seller) UserController.getActiveUser()).getBalance();
    }

    @Override
    public LinkedHashMap<String, InputValidator> getNecessaryFieldsToCreate() {
        LinkedHashMap<String, InputValidator> necessaryFields = super.getNecessaryFieldsToCreate();
        necessaryFields.put("company name", InputValidator.getSimpleTextValidator());
        necessaryFields.put("company info", InputValidator.getSimpleTextValidator());
        return necessaryFields;
    }

    @Override
    public LinkedHashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {
        filledFeatures.put("username", RegisterPanel.getLastRegisterUsername());
        Seller newSeller = new Seller(new PersonalInfo(filledFeatures), new Company(filledFeatures));
        market.addUserToList(newSeller);
    }

    @Override
    public Seller getItemById(String Id) {
        User user = market.getUserByUsername(Id);
        if (user == null || !user.getRole().equals("seller"))
            return null;
        return (Seller) market.getUserByUsername(Id);
    }
}
