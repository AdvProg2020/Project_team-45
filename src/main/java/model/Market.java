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

public class Market {
    private static Market marketInstance;
    private ArrayList<User> allUsers;// configure type by Id
    private ArrayList<Seller> requestedSellers;
    private ArrayList<CodedDiscount> allCodedDiscounts;
    private ArrayList<Category> allCategories;        // configure type by Id
    private ArrayList<Category> mainCategories;       // be made from allCategories
    private ArrayList<Product> allProducts;
    private ArrayList<Log> allLogs;
    private ArrayList<Off> allOffs;
    private ArrayList<Request> allRequests;           // configure type by Id
    private ArrayList<ProductSellInfo> allProductSellInfos; // add news to it
    private ArrayList<Rate> allRates;
    private ArrayList<Company> allCompanies;

    private Market() {
        allUsers = new ArrayList<>();
        requestedSellers = new ArrayList<>();
        allCodedDiscounts = new ArrayList<>();
        allCategories = new ArrayList<>();
        mainCategories = new ArrayList<>();
        allProducts = new ArrayList<>();
        allLogs = new ArrayList<>();
        allOffs = new ArrayList<>();
        allRequests = new ArrayList<>();
        allProductSellInfos = new ArrayList<>();
        allRates = new ArrayList<>();
        allCompanies = new ArrayList<>();
    }

    public static Market getInstance() {
        if (marketInstance == null)
            marketInstance = new Market();
        return marketInstance;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Seller> getRequestedSellers() {
        return requestedSellers;
    }

    public ArrayList<CodedDiscount> getAllCodedDiscounts() {
        return allCodedDiscounts;
    }

    public ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public ArrayList<Category> getMainCategories() {
        return mainCategories;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public ArrayList<Log> getAllLogs() {
        return allLogs;
    }

    public ArrayList<Off> getAllOffs() {
        return allOffs;
    }

    public ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public ArrayList<ProductSellInfo> getAllProductSellInfos() {
        return allProductSellInfos;
    }

    public ArrayList<Rate> getAllRates() {
        return allRates;
    }

    public ArrayList<Company> getAllCompanies() {
        return allCompanies;
    }

    public ArrayList<Product> getAllDiscountedProductsList() {
        ArrayList<Product> allDiscountedProductsList = new ArrayList<Product>();
        for (Product product : allProducts) {
            if (product.isInOff()) {
                allDiscountedProductsList.add(product);
            }
        }
        return allDiscountedProductsList;
    }

    public User getUserById(String id) {
        for (User user : allUsers) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public Seller getRequestedSellerById(String sellerId) {
        for (Seller requestedSeller : requestedSellers) {
            if (requestedSeller.getId().equals(sellerId))
                return requestedSeller;
        }
        return null;
    }

    public Category getCategoryById(String categoryId) {
        for (Category category : allCategories) {
            if (category.getId().equals(categoryId)) {
                return category;
            }
        }
        return null;
    }

    public Product getProductById(String productId) {
        for (Product product : allProducts) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public Log getLogById(String id) {
        for (Log log : allLogs) {
            if (log.getId().equals(id))
                return log;
        }
        return null;
    }

    public Off getOffById(String offId) {
        for (Off off : allOffs) {
            if (off.getId().equals(offId)) {
                return off;
            }
        }
        return null;
    }

    public Request getRequestById(String id) {
        for (Request request : allRequests) {
            if (request.getId().equals(id))
                return request;
        }
        return null;
    }

    public ProductSellInfo getProductSellInfoById(String id) {
        for (ProductSellInfo productSellInfo : allProductSellInfos) {
            if (productSellInfo.getId().equals(id))
                return productSellInfo;
        }
        return null;
    }

    public Rate getRateById(String id) {
        for (Rate rate : allRates) {
            if (rate.getId().equals(id)) {
                return rate;
            }
        }
        return null;
    }

    public Company getCompanyByName(String name) {
        for (Company company : allCompanies) {
            if (company.getName().equals(name)) {
                return company;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getPersonalInfo().getUsername().equals(username))
                return user;
        }
        return null;
    }

    public CodedDiscount getCodedDiscountByCode(String code) {
        for (CodedDiscount codedDiscount : allCodedDiscounts) {
            if (codedDiscount.getCode().equals(code))
                return codedDiscount;
        }
        return null;
    }

    public Category getCategoryByName(String name) {
        for (Category category : allCategories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }

    public Category getMainCategoryByName(String name) {
        for (Category mainCategory : mainCategories) {
            if (mainCategory.getName().equals(name)) {
                return mainCategory;
            }
        }
        return null;
    }

    public void addUserToList(User user) {
        allUsers.add(user);
    }

    public void addRequestedSeller(Seller newSeller) {
        requestedSellers.add(newSeller);
    }

    public void addCodedDiscountToList(CodedDiscount creatingDiscount) {
        allCodedDiscounts.add(creatingDiscount);
    }

    public void addCategoryToList(Category category) {
        allCategories.add(category);
    }

    public void addMainCategoryToList(Category category) {
        mainCategories.add(category);
    }

    public void addRequest(Request request) {
        this.allRequests.add(request);
    }

    public void addSellInfoToList(ProductSellInfo sellInfo) {
        allProductSellInfos.add(sellInfo);
    }

    public void removeUserFromAllUsers(User user) {
        allUsers.remove(user);
    }

    public void removeRequestedSellerFromList(Seller seller) {
        requestedSellers.remove(seller);
    }

    public void removeCodedDiscountFromList(CodedDiscount codedDiscount) {
        allCodedDiscounts.remove(codedDiscount);
    }

    public void removeCategoryFromList(Category category) {
        allCategories.remove(category);
    }

    public void removeProductFromAllList(Product product) {
        allProducts.remove(product);
    }

    public void removeProductByProductId(String productId) {
        allProducts.removeIf(product -> product.getId().equalsIgnoreCase(productId));
    }

    public void removeOffById(String offId) {
        allOffs.removeIf(off -> off.getId().equals(offId));
    }

    public void removeRequestById(String requestId) {
        for (Request request : allRequests) {
            if (request.getId().equals(requestId)) {
                allRequests.remove(request);
                return;
            }
        }
    }

    public boolean isDiscountCodeValid(String discountCode) {
        return false;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public void setRequestedSellers(ArrayList<Seller> requestedSellers) {
        this.requestedSellers = requestedSellers;
    }

    public void setAllCodedDiscounts(ArrayList<CodedDiscount> allCodedDiscounts) {
        this.allCodedDiscounts = allCodedDiscounts;
    }

    public void setAllCategories(ArrayList<Category> allCategories) {
        this.allCategories = allCategories;
    }

    public void setMainCategories(ArrayList<Category> mainCategories) {
        this.mainCategories = mainCategories;
    }

    public void setAllProducts(ArrayList<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public void setAllLogs(ArrayList<Log> allLogs) {
        this.allLogs = allLogs;
    }

    public void setAllOffs(ArrayList<Off> allOffs) {
        this.allOffs = allOffs;
    }

    public void setAllRequests(ArrayList<Request> allRequests) {
        this.allRequests = allRequests;
    }

    public void setAllProductSellInfos(ArrayList<ProductSellInfo> allProductSellInfos) {
        this.allProductSellInfos = allProductSellInfos;
    }

    public void setAllRates(ArrayList<Rate> allRates) {
        this.allRates = allRates;
    }

    public void setAllCompanies(ArrayList<Company> allCompanies) {
        this.allCompanies = allCompanies;
    }

    public CodedDiscount getCodedDiscountById(String id) {
        for (CodedDiscount discount : allCodedDiscounts) {
            if (discount.getId().equals(id)) {
                return discount;
            }
        }
        return null;
    }

    public Category getMainCategoryById(String id) {
        for (Category mainCategory : mainCategories) {
            if (mainCategory.getId().equals(id)) {
                return mainCategory;
            }
        }
        return null;
    }

}

