package controller.userControllers;

import controller.InputValidator;
import controller.managers.Manager;
import model.Company;
import model.Market;
import model.Off;
import model.log.SellLog;
import model.product.Product;
import model.product.ProductSellInfo;
import model.request.*;
import model.user.PersonalInfo;
import model.user.Seller;
import model.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SellerController extends UserController implements Manager {
    private static final SellerController instance = new SellerController();

    private final LinkedHashMap<String, InputValidator> newProductFieldsToCreate;
    private final ArrayList<String> offFieldsToCreate;
    private SellLog currentSellLog;

    private SellerController() {
        super();
        this.newProductFieldsToCreate = makeNewProductFieldsToCreate();

        this.offFieldsToCreate = new ArrayList<>();
        this.offFieldsToCreate.addAll(Arrays.asList("productIds(separated by ',')", "startTime", "endTime"
                , "discountAmount"));
    }

    private LinkedHashMap<String, InputValidator> makeNewProductFieldsToCreate() {
        LinkedHashMap<String, InputValidator> fields = new LinkedHashMap<>();
        fields.put("name", InputValidator.getSimpleTextValidator());
        fields.put("categoryName", InputValidator.getFinalCategoryValidator());
        fields.put("description", InputValidator.getSimpleTextValidator());
        fields.put("price", InputValidator.getPriceValidator());
        fields.put("stock", InputValidator.getPriceValidator());
        return fields;
    }

    private LinkedHashMap<String, InputValidator> makeExistingFieldsToCreate() {
        LinkedHashMap<String, InputValidator> fields = new LinkedHashMap<>();
        fields.put("price", InputValidator.getPriceValidator());
        fields.put("stock", InputValidator.getPriceValidator());
        return fields;
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
        return "logId = '" + sellLog.getMainLog().getId() + "'\n" +
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
        return "productId = '" + product.getId() + "'\n" +
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


    public void createProductEditionRequest(String productId, HashMap<String, String> fieldsAndValues) {
        Market.getInstance().getAllRequests().add(new ProductEditionRequest(productId, ((Seller)UserController.getActiveUser())
                , fieldsAndValues));
    }

    // add product panel


    public LinkedHashMap<String, InputValidator> getNewProductFieldsToCreate() {
        return newProductFieldsToCreate;
    }

    public void createAddProductRequest(String mode, Product product, int price, int stock) {
        product.setCompany(((Seller) getActiveUser()).getCompany());
        ProductSellInfo productSellInfo = new ProductSellInfo(product, (Seller) getActiveUser());
        productSellInfo.setPrice(price);
        productSellInfo.setStock(stock);
        if (mode.equals("new")) {
            product.setDefaultSellInfo(productSellInfo);
        }
        Market.getInstance().getAllProducts().add(product);
        Market.getInstance().getAllProductSellInfos().add(productSellInfo);
        Market.getInstance().getAllRequests().add(new AddProductRequest(mode, productSellInfo));
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

    public void createAddOffRequest(Off off) {
        Market.getInstance().getAllRequests().add(new AddOffRequest(((Seller)UserController.getActiveUser()), off));
    }

    // view balance panel

    public int getSellerBalance() {
        return ((Seller) UserController.getActiveUser()).getBalance();
    }


    public void createItem(HashMap<String, String> filledFeatures, String username) {
        filledFeatures.put("username", username);
        Seller newSeller = new Seller(new PersonalInfo(filledFeatures), new Company(filledFeatures));
        SellerRegisterRequest registerRequest  = new SellerRegisterRequest(newSeller);
        market.addRequest(registerRequest);
        market.addRequestedSeller(newSeller);
    }

    @Override
    public Seller getItemById(String Id) {
        User user = market.getUserByUsername(Id);
        if (user == null || !user.getRole().equals("seller"))
            return null;
        return (Seller) market.getUserByUsername(Id);
    }

    public Company getSellerCompany() {
        return ((Seller) UserController.getActiveUser()).getCompany();
    }

    public SellLog getCurrentSellLog() {
        return currentSellLog;
    }

    public void setCurrentSellLog(String id) {
        this.currentSellLog = ((Seller) getActiveUser()).getSellLogById(id);
    }
}
