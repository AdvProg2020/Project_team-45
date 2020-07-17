package server.model;

import server.model.category.Category;
import server.model.category.FinalCategory;
import server.model.category.ParentCategory;
import server.model.log.Log;
import server.model.product.Product;
import server.model.product.ProductSellInfo;
import server.model.product.Rate;
import server.model.request.*;
import server.model.user.Admin;
import server.model.user.Buyer;
import server.model.user.Seller;
import server.model.user.User;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MarketCopier {
    private static MarketCopier instance = new MarketCopier();
    private static final Market market = Market.getInstance();
    private HashMap<String, HashMap<String, String>> allUsers;
    private HashMap<String, HashMap<String, String>> requestedSellers;
    private HashMap<String, HashMap<String, String>> allCodedDiscounts;
    private HashMap<String, HashMap<String, String>> allCategories;
//    private HashMap<String, HashMap<String, String>> mainCategories;
    private HashMap<String, HashMap<String, String>> allProducts;
    private HashMap<String, HashMap<String, String>> allLogs;
    private HashMap<String, HashMap<String, String>> allOffs;
    private HashMap<String, HashMap<String, String>> allRequests;
    private HashMap<String, HashMap<String, String>> allProductSellInfos;
    private HashMap<String, HashMap<String, String>> allRates;
    private ArrayList<Company> allCompanies;
    private final IdKeeper idKeeper;

    private MarketCopier() {
        allLogs = new HashMap<>();
        allOffs = new HashMap<>();
        allUsers = new HashMap<>();
        allCodedDiscounts = new HashMap<>();
        allCategories = new HashMap<>();
//        mainCategories = new HashMap<>();
        allProducts = new HashMap<>();
        allRequests = new HashMap<>();
        allProductSellInfos = new HashMap<>();
        requestedSellers = new HashMap<>();
        allRates = new HashMap<>();
        allCompanies = new ArrayList<>();
        idKeeper = IdKeeper.getInstance();
    }

    public static MarketCopier getInstance() {
        return instance;
    }

    public static void setInstance(MarketCopier instance) {
        MarketCopier.instance = instance;
    }

    public void copyMarket() {
        allLogs = buildIdToObjectHashMapHashMap(market.getAllLogs());
        allOffs = buildIdToObjectHashMapHashMap(market.getAllOffs());
        allUsers = buildIdToObjectHashMapHashMap(market.getAllUsers());
        allCodedDiscounts = buildIdToObjectHashMapHashMap(market.getAllCodedDiscounts());
        allCategories = buildIdToObjectHashMapHashMap(market.getAllCategories());
//        mainCategories = buildIdToObjectHashMapHashMap(market.getMainCategories());
        allProducts = buildIdToObjectHashMapHashMap(market.getAllProducts());
        allRequests = buildIdToObjectHashMapHashMap(market.getAllRequests());
        allProductSellInfos = buildIdToObjectHashMapHashMap(market.getAllProductSellInfos());
        requestedSellers = buildIdToObjectHashMapHashMap(market.getRequestedSellers());
        allRates = buildIdToObjectHashMapHashMap(market.getAllRates());
        allCompanies = market.getAllCompanies();
    }

    private <Type extends IdRecognized & Savable> HashMap<String, HashMap<String, String>> buildIdToObjectHashMapHashMap(ArrayList<Type> objects) {
        HashMap<String, HashMap<String, String>> result = new HashMap<>();
        for (Type object : objects) {
            result.put(object.getId(), object.convertToHashMap());
        }
        return result;
    }

    public void buildMarketWithIds() {
        market.setAllLogs(buildArrayListOfPrimaryObjects(Log.class, allLogs.keySet()));
        market.setAllOffs(buildArrayListOfPrimaryObjects(Off.class, allOffs.keySet()));
        market.setAllUsers(buildArrayListOfPrimaryUsers(allUsers.keySet()));
        market.setAllCodedDiscounts(buildArrayListOfPrimaryObjects(CodedDiscount.class, allCodedDiscounts.keySet()));
        market.setAllCategories(buildArrayListOfPrimaryCategories(allCategories.keySet()));
//        market.setMainCategories(buildArrayListOfPrimaryCategories(mainCategories.keySet()));
        market.setAllProducts(buildArrayListOfPrimaryObjects(Product.class, allProducts.keySet()));
        market.setAllRequests(buildArrayListOfPrimaryRequests(allRequests.keySet()));
        market.setAllProductSellInfos(buildArrayListOfPrimaryObjects(ProductSellInfo.class, allProductSellInfos.keySet()));
        market.setRequestedSellers(buildArrayListOfPrimaryObjects(Seller.class, requestedSellers.keySet()));
        market.setAllRates(buildArrayListOfPrimaryObjects(Rate.class, allRates.keySet()));
        market.setAllCompanies(allCompanies);
        IdKeeper.setInstance(idKeeper);
    }

    private <Type extends Savable> ArrayList<Type> buildArrayListOfPrimaryObjects(Class<Type> typeClass, Set<String> set) {
        ArrayList<Type> objects = new ArrayList<>();
        Constructor<Type> typeConstructor = null;
        try {
            typeConstructor = typeClass.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        for (String id : set) {
            Type type = null;
            try {
                type = typeConstructor.newInstance(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            objects.add(type);
        }
        return objects;
    }

    private ArrayList<User> buildArrayListOfPrimaryUsers(Set<String> set) {
        ArrayList<User> objects = new ArrayList<>();
        for (String id : set) {
            User user = null;
            if (id.startsWith("admin")) {
                user = new Admin(id);
            } else if (id.startsWith("buyer")) {
                user = new Buyer(id);
            } else if (id.startsWith("seller")) {
                user = new Seller(id);
            }
            objects.add(user);
        }
        return objects;
    }

    private ArrayList<Category> buildArrayListOfPrimaryCategories(Set<String> set) {
        ArrayList<Category> objects = new ArrayList<>();
        for (String id : set) {
            Category category = null;
            if (id.startsWith("ParentCategory")) {
                category = new ParentCategory(id);
            } else if (id.startsWith("FinalCategory")) {
                category = new FinalCategory(id);
            }
            objects.add(category);
        }
        return objects;
    }

    private ArrayList<Request> buildArrayListOfPrimaryRequests(Set<String> set) {
        ArrayList<Request> objects = new ArrayList<>();
        for (String id : set) {
            Request request = null;
            if (id.startsWith("add off")) {
                request = new AddOffRequest(id);
            } else if (id.startsWith("add product")) {
                request = new AddProductRequest(id);
            } else if (id.startsWith("new comment")) {
                request = new CommentRequest(id);
            } else if (id.startsWith("edit off")) {
                request = new OffEditionRequest(id);
            } else if (id.startsWith("edit product")) {
                request = new ProductEditionRequest(id);
            } else if (id.startsWith("remove product")) {
                request = new RemoveProductRequest(id);
            } else if (id.startsWith("seller register")) {
                request = new SellerRegisterRequest(id);
            }
            objects.add(request);
        }
        return objects;
    }

    public void buildMarketWithHashMaps() {
        buildUsersWithHashMaps();
        buildRequestedSellersWithHashMaps();
        buildDiscountsWithHashMaps();
        buildCategoriesWithHashMaps();
        buildMainCategoriesWithHashMaps();
        buildLogsWithHashMaps();
        buildOffsWithHashMaps();
        buildRequestsWithHashMaps();
        buildProductSellInfosWithHashMaps();
        buildProductsWithHashMaps();
        buildRatesWithHashMaps();
    }

    private void buildUsersWithHashMaps() {
        for (String id : allUsers.keySet()) {
            User user = Market.getInstance().getUserById(id);
            user.setFieldsFromHashMap(allUsers.get(id));
        }
    }

    private void buildRequestedSellersWithHashMaps() {
        for (String id : requestedSellers.keySet()) {
            Seller seller = Market.getInstance().getRequestedSellerById(id);
            seller.setFieldsFromHashMap(requestedSellers.get(id));
        }
    }

    private void buildDiscountsWithHashMaps() {
        for (String id : allCodedDiscounts.keySet()) {
            CodedDiscount discount = Market.getInstance().getCodedDiscountById(id);
            discount.setFieldsFromHashMap(allCodedDiscounts.get(id));
        }
    }

    private void buildCategoriesWithHashMaps() {
        for (String id : allCategories.keySet()) {
            Category category = Market.getInstance().getCategoryById(id);
            category.setFieldsFromHashMap(allCategories.get(id));
        }
    }

    private void buildMainCategoriesWithHashMaps() {
        for (String id : allCategories.keySet()) {
            Category category = Market.getInstance().getCategoryById(id);
            if (category.isMain()) {
                market.addMainCategoryToList(category);
            }
        }
    }

    private void buildProductsWithHashMaps() {
        for (String id : allProducts.keySet()) {
            Product product = Market.getInstance().getProductById(id);
            product.setFieldsFromHashMap(allProducts.get(id));
        }
    }

    private void buildLogsWithHashMaps() {
        for (String id : allLogs.keySet()) {
            Log log = Market.getInstance().getLogById(id);
            log.setFieldsFromHashMap(allLogs.get(id));
        }
    }

    private void buildOffsWithHashMaps() {
        for (String id : allOffs.keySet()) {
            Off off = Market.getInstance().getOffById(id);
            off.setFieldsFromHashMap(allOffs.get(id));
        }
    }

    private void buildRequestsWithHashMaps() {
        for (String id : allRequests.keySet()) {
            Request request = Market.getInstance().getRequestById(id);
            request.setFieldsFromHashMap(allRequests.get(id));
        }
    }

    private void buildProductSellInfosWithHashMaps() {
        for (String id : allProductSellInfos.keySet()) {
            ProductSellInfo sellInfo = Market.getInstance().getProductSellInfoById(id);
            sellInfo.setFieldsFromHashMap(allProductSellInfos.get(id));
        }
    }

    private void buildRatesWithHashMaps() {
        for (String id : allRates.keySet()) {
            Rate rate = Market.getInstance().getRateById(id);
            rate.setFieldsFromHashMap(allRates.get(id));
        }
    }

}
