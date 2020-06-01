package model;

import model.category.Category;
import model.log.Log;
import model.product.Product;
import model.product.ProductSellInfo;
import model.product.Rate;
import model.request.Request;
import model.user.Seller;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class MarketCopier { // copies and rebuilds market
    private static MarketCopier instance = new MarketCopier();
    private HashMap<String, User> allUsers;// configure type by Id
    private HashMap<String, Seller> requestedSellers;
    private HashMap<String, CodedDiscount> allCodedDiscounts;
    private HashMap<String, Category> allCategories;        // configure type by Id
    private HashMap<String, Category> mainCategories;       // be made from allCategories
    private HashMap<String, Product> allProducts;
    private HashMap<String, Log> allLogs;
    private HashMap<String, Off> allOffs;
    private HashMap<String, Request> allRequests;           // configure type by Id
    private HashMap<String, ProductSellInfo> allProductSellInfos; // add news to it
    private HashMap<String, Rate> allRates;
    private HashMap<String, Company> allCompanies;
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
        allCompanies = new HashMap<>();
        idKeeper = IdKeeper.getInstance();
    }

    public static MarketCopier getInstance() {
        return instance;
    }

    public static void setInstance(MarketCopier instance) {
        MarketCopier.instance = instance;
    }

    private void copyMarket() {
        Market market = Market.getInstance();
        allLogs = createIdToIdRecognizedHashmap(market.getAllLogs());
        allOffs = createIdToIdRecognizedHashmap(market.getAllOffs());
        allUsers = createIdToIdRecognizedHashmap(market.getAllUsers());
        allCodedDiscounts = createIdToIdRecognizedHashmap(market.getAllCodedDiscounts());
        allCategories = createIdToIdRecognizedHashmap(market.getAllCategories());
        mainCategories = createIdToIdRecognizedHashmap(market.getMainCategories());
        allProducts = createIdToIdRecognizedHashmap(market.getAllProducts());
        allRequests = createIdToIdRecognizedHashmap(market.getAllRequests());
        allProductSellInfos = createIdToIdRecognizedHashmap(market.getAllProductSellInfos());
        requestedSellers = createIdToIdRecognizedHashmap(market.getRequestedSellers());
        allRates = createIdToIdRecognizedHashmap(market.getAllRates());
        allCompanies = createIdToIdRecognizedHashmap(market.getAllCompanies());
        idKeeper.updateIds();
    }

    private <Type extends IdRecognized> HashMap<String, Type> createIdToIdRecognizedHashmap(ArrayList<Type> idRecognizedObjects) {
        HashMap<String, Type> result = new HashMap<>();
        for (Type idRecognized : idRecognizedObjects) {
            result.put(idRecognized.getId(), idRecognized);
        }
        return result;
    }

    public void buildMarket() {

    }

}
