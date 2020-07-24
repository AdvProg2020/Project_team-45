package server.controller.userControllers;

import client.network.MethodStringer;
import server.controller.OffController;
import server.controller.managers.Manager;
import server.model.Company;
import server.model.Market;
import server.model.Off;
import server.model.category.FinalCategory;
import server.model.log.SellLog;
import server.model.product.Product;
import server.model.product.ProductSellInfo;
import server.model.request.*;
import server.model.user.PersonalInfo;
import server.model.user.Seller;
import server.model.user.User;
import server.newModel.nedaei.FileProduct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class SellerController extends UserController implements Manager {
    private static final SellerController instance = new SellerController();

    private SellLog currentSellLog;

    public static SellerController getInstance() {
        return instance;
    }

    // used in seller menu

    public HashMap<String, String> getSellerCompany() {
        HashMap<String, String> result = new HashMap<>();
        Company company = ((Seller) UserController.getActiveUser()).getCompany();
        result.put("name", company.getName());
        result.put("info", company.getOtherInformation());
        return result;
    }

    public Integer getSellerBalance() {
        return ((Seller) UserController.getActiveUser()).getBalance();
    }

    public Integer getSellerAccountBalance() {
        return ((Seller) UserController.getActiveUser()).getAccountBalance();
    }

    // used in sales history panel

    public ArrayList<HashMap<String, String>> getSellerSellLogs() {
        ArrayList<SellLog> sellLogs = ((Seller) UserController.getActiveUser()).getListOfSellLogs();
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (SellLog sellLog : sellLogs) {
            HashMap<String, String> sellLogDisplay = new HashMap<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            sellLogDisplay.put("date", simpleDateFormat.format(sellLog.getMainLog().getDate()));
            sellLogDisplay.put("id", sellLog.getMainLog().getId());
            sellLogDisplay.put("finalPrice", "" + sellLog.getIncome());
            sellLogDisplay.put("buyerUsername", sellLog.getMainLog().getBuyerUsername());
            result.add(sellLogDisplay);
        }
        return result;
    }

    public void setCurrentSellLog(String id) {
        this.currentSellLog = ((Seller) getActiveUser()).getSellLogById(id);
    }

    // used in sell log panel

    public HashMap<String, String> getCurrentSellLog() {
        HashMap<String, String> result = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        result.put("date", simpleDateFormat.format(currentSellLog.getMainLog().getDate()));
        result.put("id", currentSellLog.getMainLog().getId());
        result.put("buyerUsername", currentSellLog.getMainLog().getBuyerUsername());
        result.put("discountPercentage", "" + currentSellLog.getMainLog().getAppliedDiscountPercentage());
        result.put("finalPrice", "" + currentSellLog.getIncome());
        result.put("address", currentSellLog.getMainLog().getAddress());
        result.put("phoneNumber", currentSellLog.getMainLog().getPhoneNumber());
        result.put("soldProductId", currentSellLog.getSoldProducts().get(0).getProduct().getId());
        return result;
    }

    // used in products managing menu

    public ArrayList<HashMap<String, String>> getSellInfos() {
        ArrayList<ProductSellInfo> productSellInfos = new ArrayList<>(((Seller) UserController.getActiveUser()).getAvailableProducts().values());
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (ProductSellInfo productSellInfo : productSellInfos) {
            HashMap<String, String> sellInfo = new HashMap<>();
            sellInfo.put("name", productSellInfo.getProduct().getName());
            sellInfo.put("id", productSellInfo.getProduct().getId());
            result.add(sellInfo);
        }
        return result;
    }

    // used in add product panel todo: nedaei, fix image and video address

    public void createAddProductRequest(String mode, HashMap<String, String> product, int price, int stock) {
        Product newProduct = null;
        if (mode.equals("existing")) {
            newProduct = market.getProductById(product.get("id"));
        } else if (mode.equals("new")) {
            if (product.get("isFile").equals("true")) {
                newProduct = new FileProduct(product.get("name"), (FinalCategory) market.getCategoryByName(product.get("categoryName")),
                        product.get("description"), "sth", "sth",
                        ((Seller) UserController.getActiveUser()).getUsername(), product.get("filePath"));
            } else {
                newProduct = new Product(product.get("name"), (FinalCategory) market.getCategoryByName(product.get("categoryName")),
                        product.get("description"), "sth", "sth");
            }
        }
        newProduct.setCompany(((Seller) UserController.getActiveUser()).getCompany());
        ProductSellInfo productSellInfo = new ProductSellInfo(newProduct, (Seller) UserController.getActiveUser());
        productSellInfo.setPrice(price);
        productSellInfo.setStock(stock);
        if (mode.equals("new")) {
            newProduct.setDefaultSellInfo(productSellInfo);
        }

        Market.getInstance().getAllProducts().add(newProduct);
        Market.getInstance().getAllProductSellInfos().add(productSellInfo);
        Market.getInstance().getAllRequests().add(new AddProductRequest(mode, productSellInfo));
    }

    // used in edit product panel

    public void createProductEditionRequest(String productId, HashMap<String, String> fieldsAndValues) {
        Market.getInstance().getAllRequests().add(new ProductEditionRequest(productId, ((Seller)UserController.getActiveUser())
                , fieldsAndValues));
    }

    // used in remove product panel todo: nedaei

    public ArrayList<HashMap<String, String>> getAvailableProducts() {
        ArrayList<Product> products = new ArrayList<>(((Seller) UserController.getActiveUser()).getAvailableProducts().keySet());
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (Product product : products) {
            HashMap<String, String> productDisplay = new HashMap<>();
            productDisplay.put("name", product.getName());
            productDisplay.put("id", product.getId());
            result.add(productDisplay);
        }
        return result;
    }

    public void createRemoveProductRequest(String productId) {
        if (getSellerAvailableProductById(productId) == null) {
            return;
        }
        Market.getInstance().getAllRequests()
                .add(new RemoveProductRequest(((Seller)UserController.getActiveUser()), productId));
    }

    // used in offs managing menu

    public ArrayList<HashMap<String, String>> getSellerOffs() {
        ArrayList<Off> offs = new ArrayList<>(((Seller) UserController.getActiveUser()).getListOfOffs().values());
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (Off off : offs) {
            HashMap<String, String> offDisplay = new HashMap<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            offDisplay.put("id", off.getId());
            offDisplay.put("discountAmount", "" + off.getDiscountAmount());
            offDisplay.put("startTime", simpleDateFormat.format(off.getStartTime()));
            offDisplay.put("endTime", simpleDateFormat.format(off.getEndTime()));
            result.add(offDisplay);
        }
        return result;
    }

    // used in add off panel

    public void createAddOffRequest(ArrayList<String> products, HashMap<String, String> off) {
        ArrayList<Product> offProducts = new ArrayList<>();
        for (String id : products) {
            offProducts.add(market.getProductById(id));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Off newOff = new Off(offProducts, simpleDateFormat.parse(off.get("start")), simpleDateFormat.parse("end"),
                    Integer.parseInt(off.get("discount")));
            Market.getInstance().getAllRequests().add(new AddOffRequest(((Seller)UserController.getActiveUser()), newOff));
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
    }

    // used in edit off panel

    public void createOffEditionRequest(HashMap<String, String> fieldsAndValues) {
        Off off = getAvailableOffById(OffController.getInstance().getCurrentOff().getId());
        if (off == null) {
            return;
        }
        Market.getInstance().getAllRequests().add(new OffEditionRequest(((Seller)UserController.getActiveUser()), off.getId()
                , fieldsAndValues));
    }

    // other...

    public Product getSellerAvailableProductById(String productId) {
        return ((Seller) UserController.getActiveUser()).getAvailableProductById(productId);
    }

    private Off getAvailableOffById(String offId) {
        return ((Seller) UserController.getActiveUser()).getOffByOffId(offId);
    }

    // i don't know!

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
}