package model;

import model.category.Category;
import model.category.FinalCategory;
import model.category.ParentCategory;
import model.log.Log;
import model.product.Product;
import model.product.ProductSellInfo;
import model.product.Rate;
import model.request.*;
import model.user.Admin;
import model.user.Buyer;
import model.user.Seller;
import model.user.User;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
    }

    private <Type extends IdRecognized & Savable> HashMap<String, HashMap> buildIdToObjectHashMapHashMap(ArrayList<Type> objects) {
        HashMap<String, HashMap> result = new HashMap<>();
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
        market.setMainCategories(buildArrayListOfPrimaryCategories(mainCategories.keySet()));
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
            if (id.startsWith("parent")) {
                category = new ParentCategory(id);
            } else if (id.startsWith("final")) {
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

    }

    private <Type extends Savable> ArrayList<Type> buildArrayListOfObjects(Class<Type> typeClass, HashMap<String, HashMap> hashMap) {
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

    private ArrayList<User> buildArrayListOfUsers(HashMap<String, HashMap> hashMap) {
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

    private ArrayList<Category> buildArrayListOfCategories(HashMap<String, HashMap> hashMap) {
        ArrayList<Category> objects = new ArrayList<>();
        for (String id : set) {
            Category category = null;
            if (id.startsWith("parent")) {
                category = new ParentCategory(id);
            } else if (id.startsWith("final")) {
                category = new FinalCategory(id);
            }
            objects.add(category);
        }
        return objects;
    }

    private ArrayList<Request> buildArrayListOfRequests(HashMap<String, HashMap> hashMap) {
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

}
