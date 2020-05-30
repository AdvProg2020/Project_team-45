package model;

import model.category.Category;
import model.log.Log;
import model.product.Product;
import model.request.Request;
import model.user.User;

import java.util.ArrayList;

public class Market {
    private static Market marketInstance;
    private final ArrayList<User> allUsers;                 // configure type by Id
    private final ArrayList<CodedDiscount> allCodedDiscounts;
    private final ArrayList<Category> allCategories;        // configure type by Id
    private final ArrayList<Category> mainCategories;       // be made from allCategories
    private final ArrayList<Product> allProducts;
    private final ArrayList<Log> allLogs;
    private final ArrayList<Off> allOffs;
    private final ArrayList<Request> allRequests;           // configure type by Id

    private Market() {
        allLogs = new ArrayList<>();
        allOffs = new ArrayList<>();
        allUsers = new ArrayList<>();
        allCodedDiscounts = new ArrayList<>();
        allCategories = new ArrayList<>();
        mainCategories = new ArrayList<>();
        allProducts = new ArrayList<>();
        allRequests = new ArrayList<>();
    }

    public static Market getInstance() {
        if (marketInstance == null)
            marketInstance = new Market();
        return marketInstance;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Off> getAllOffs() {
        return allOffs;
    }

    public ArrayList<Log> getAllLogs() {
        return allLogs;
    }

    public ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public ArrayList<Category> getMainCategories() {
        return mainCategories;
    }

    public ArrayList<CodedDiscount> getAllCodedDiscounts() {
        return allCodedDiscounts;
    }

    public Category getMainCategoryByName(String name) {
        for (Category mainCategory : mainCategories) {
            if (mainCategory.getName().equals(name)) {
                return mainCategory;
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

    public void addUserToList(User user) {
        allUsers.add(user);
    }

    public CodedDiscount getCodedDiscountByCode(String code) {
        for (CodedDiscount codedDiscount : allCodedDiscounts) {
            if (codedDiscount.getCode().equals(code))
                return codedDiscount;
        }
        return null;
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

    public boolean isDiscountCodeValid(String discountCode) {
        return false;
    }

    public Product getProductById(String productId) {
        for (Product product : allProducts) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public Off getOffById(String offId) {
        for (Off off : allOffs) {
            if (off.getOffId().equals(offId)) {
                return off;
            }
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

    public void removeUserFromAllUsers(User user) {
        allUsers.remove(user);
    }

    public void removeProductByProductId(String productId) {
        allProducts.removeIf(product -> product.getProductId().equalsIgnoreCase(productId));
    }

    public void removeCodedDiscountFromList(CodedDiscount codedDiscount) {
        allCodedDiscounts.remove(codedDiscount);
    }

    public void addCategoryToList(Category category) {
        allCategories.add(category);
    }

    public void addMainCategoryToList(Category category) {
        mainCategories.add(category);
    }

    public void removeCategoryFromList(Category category) {
        allCategories.remove(category);
    }

    public void removeOffById(String offId) {
        allOffs.removeIf(off -> off.getOffId().equals(offId));
    }

    public void removeProductFromAllProductsList(Product product) {
        allProducts.remove(product);
    }

    public void removeRequestById(String requestId) {
        for (Request request : allRequests) {
            if (request.getRequestId().equals(requestId)) {
                allRequests.remove(request);
                return;
            }
        }
    }

    public Request getRequestById(String id) {
        for (Request request : allRequests) {
            if (request.getRequestId().equals(id))
                return request;
        }
                return null;
    }

    public void addDiscountToList(CodedDiscount creatingDiscount) {
        allCodedDiscounts.add(creatingDiscount);
    }

    public void addRequest(Request request) {
        this.allRequests.add(request);
    }

    public User getUserById(String id) {
        for (User user : allUsers) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}

