package model;

import model.category.Category;
import model.log.Log;
import model.product.Product;
import model.product.ProductSellInfo;
import model.product.Rate;
import model.request.Request;
import model.user.Seller;
import model.user.User;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

public class MarketCopier { // copies and rebuilds market
    private static MarketCopier instance = new MarketCopier();
    private static Market market = Market.getInstance();
    private HashMap<String, HashMap> allUsers;// configure type by Id
    private HashMap<String, HashMap> requestedSellers;
    private HashMap<String, HashMap> allCodedDiscounts;
    private HashMap<String, HashMap> allCategories;        // configure type by Id
    private HashMap<String, HashMap> mainCategories;       // be made from allCategories
    private HashMap<String, HashMap> allProducts;
    private HashMap<String, HashMap> allLogs;
    private HashMap<String, HashMap> allOffs;
    private HashMap<String, HashMap> allRequests;           // configure type by Id
    private HashMap<String, HashMap> allProductSellInfos; // add news to it
    private HashMap<String, HashMap> allRates;
    private ArrayList<Company> allCompanies;
    private IdKeeper idKeeper;

    private MarketCopier() {
        allLogs = new HashMap<>();
        allOffs = new HashMap<>();
        allUsers = new HashMap<>();
        allCodedDiscounts = new HashMap<>();
        allCategories = new HashMap<>();
        mainCategories = new HashMap<>();
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

    private void copyMarket() {
        allLogs = buildIdToObjectHashMapHashMap(market.getAllLogs());
        allOffs = buildIdToObjectHashMapHashMap(market.getAllOffs());
        allUsers = buildIdToObjectHashMapHashMap(market.getAllUsers());
        allCodedDiscounts = buildIdToObjectHashMapHashMap(market.getAllCodedDiscounts());
        allCategories = buildIdToObjectHashMapHashMap(market.getAllCategories());
        mainCategories = buildIdToObjectHashMapHashMap(market.getMainCategories());
        allProducts = buildIdToObjectHashMapHashMap(market.getAllProducts());
        allRequests = buildIdToObjectHashMapHashMap(market.getAllRequests());
        allProductSellInfos = buildIdToObjectHashMapHashMap(market.getAllProductSellInfos());
        requestedSellers = buildIdToObjectHashMapHashMap(market.getRequestedSellers());
        allRates = buildIdToObjectHashMapHashMap(market.getAllRates());
        allCompanies = market.getAllCompanies();
        idKeeper.updateIds();
    }

    private <Type extends IdRecognized & Savable> HashMap<String, HashMap> buildIdToObjectHashMapHashMap(ArrayList<Type> objects) {
        HashMap<String, HashMap> result = new HashMap<>();
        for (Type object : objects) {
            result.put(object.getId(), object.convertToHashMap());
        }
        return result;
    }

    public void buildMarket() {
        market.setAllLogs(buildArrayListOfObjects(Log.class, allLogs));
        market.setAllOffs(buildArrayListOfObjects(Off.class, allOffs));
        market.setAllUsers(buildArrayListOfObjects(User.class, allUsers));
        market.setAllCodedDiscounts(buildArrayListOfObjects(CodedDiscount.class, allCodedDiscounts));
        market.setAllCategories(buildArrayListOfObjects(Category.class, allCategories));
        market.setMainCategories(buildArrayListOfObjects(Category.class, mainCategories));
        market.setAllProducts(buildArrayListOfObjects(Product.class, allProducts));
        market.setAllRequests(buildArrayListOfObjects(Request.class, allRequests));
        market.setAllProductSellInfos(buildArrayListOfObjects(ProductSellInfo.class, allProductSellInfos));
        market.setRequestedSellers(buildArrayListOfObjects(Seller.class, requestedSellers));
        market.setAllRates(buildArrayListOfObjects(Rate.class, allRates));
        market.setAllCompanies(allCompanies);
        IdKeeper.setInstance(idKeeper);
    }

    private <Type extends Savable> ArrayList<Type> buildArrayListOfObjects(Class<Type> typeClass, HashMap<String, HashMap> hashMap) {
        ArrayList<Type> objects = new ArrayList<>();
        Constructor<Type> typeConstructor = null;
        try {
            typeConstructor = typeClass.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        for (String id : hashMap.keySet()) {
            Type type = null;
            try {
                type = typeConstructor.newInstance(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            type.setFieldsFromHashMap(hashMap.get(id));
            objects.add(type);
        }
        return objects;
    }

}
