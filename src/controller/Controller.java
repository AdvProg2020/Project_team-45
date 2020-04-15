package controller;

import model.*;
import model.log.BuyLog;
import model.log.SellLog;
import model.request.*;
import model.user.AnonymousUser;
import model.user.Buyer;
import model.user.Seller;
import model.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Controller {
    private Market market;
    private AnonymousUser anonymousUser;
    private User user;
    private boolean didLogin;
    private Category category;
    private Product product;
    private Off off;
    private CodedDiscount codedDiscount;
    private Comment comment;
    private ProductFilters productFilters;
    private OffFilters offFilters;

    public Controller() {
        this.market = Market.getInstance();
        this.anonymousUser = new AnonymousUser();
    }

    //================================================================================
    // control user (or anonymous user)
    //================================================================================

    // from past

    public void addUser(String username, String firstName, String lastName, String emailAddress, String phoneNumber, String password) {
    }

    public User loginUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public void deleteUser(User user) {
    }

    public void acceptOrDeclineRequest(String requestId, String statusToSet) {
    }

    // new

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserField(String fieldName, String newValue) {
    }

    public ArrayList<SellLog> getSellerSalesHistory(Seller seller) {
        return null;
    }

    public ArrayList<BuyLog> getBuyerOrders() {
        return null;
    }

    public void rateProduct(Product product, int rate) {
    }

    public ArrayList<CodedDiscount> getUserDiscountCodes(User user) {
        return null;
    }

    public void addProductToCart(Product product) {
    }

    public void makeCommentRequest(String title, String content) {
    }

    //================================================================================
    // control cart
    //================================================================================

    public Cart getCart() {
        return null;
    }

    public ArrayList<Product> getCartProducts() {
        return null;
    }

    public void changeCartProductAmount(Product product, int amount) {
    }

    public void purchaseTheCart() {
    }

    //================================================================================
    // control category
    //================================================================================

    // from past

    public void removeCategory(String name) {
    }

    // new

    public void addCategory(String name, Category parent, ArrayList<String> features) {
    }

    public void addFeatureToCategory(Category category, String feature) {
    }

    public void removeFeatureFromCategory(Category category, String feature) {
    }

    //================================================================================
    // control product
    //================================================================================

    // from past

    public void deleteProduct(String productId) {
    }

    public void getProductBuyers(Product product) {
    }

    // new

    public ArrayList<Comment> getProductComments(Product product) {
        return null;
    }

    //================================================================================
    // control Off
    //================================================================================

    //================================================================================
    // control coded discount
    //================================================================================

    // from past

    public void addCodedDiscount(String discountCode, int discountPercentage, Date startDate, Date endDate) {
    }

    public void editCodedDiscount(String discountCode, int discountPercentage, Date startDate, Date endDate) {
    }

    public void removeCodedDiscount(String discountCode) {
    }

    // new

    public void setCodedDiscount(CodedDiscount codedDiscount) {
        this.codedDiscount = codedDiscount;
    }

    public void setCodedDiscountField(String fieldName, String newValue) {
    }

    public boolean isCodedDiscountValid() {
        return false;
    }

    //================================================================================
    // control filtering
    //================================================================================

    public ArrayList<String> getAvailableFiltersForProducts() {
        return null;
    }

    public void createNewProductFilter() {}

    public void setFieldOfProductFilters(String field, String value) {}

    public void addFilterToProductFilters(String filterType, String filter) {}

    public void removeFilterFromProductFilters(String filterToRemove) {
    }

    public ArrayList<String> getAvailableFiltersForOffs() {
        return null;
    }

    public void createNewOffFilter() {}

    public void setFieldOfOffFilters(String field, String value) {}

    //================================================================================
    // control requests
    //================================================================================

    public SellerRegisterRequest createSellerRegisterRequest() {
        return null;
    }

    public void setFieldOfSellerRegisterRequest(SellerRegisterRequest sellerRegisterRequest, String field, String value) {}

    public AddProductRequest createAddProductRequest() {
        return null;
    }

    public void setFieldOfAddProductRequest(AddProductRequest addProductRequest, String field, String value) {
    }

    public ProductEditionRequest createProductEditionRequest(String productId) {
        return null;
    }

    public void setFieldOfProductEditionRequest(ProductEditionRequest productEditionRequest, String field, String value) {
    }

    public void createRemoveProductRequest(String productId) {}

    public AddOffRequest createAddOffRequest() {
        return null;
    }

    public void setFieldOfAddOffRequest(AddOffRequest addOffRequest, String field, String value) {}

    public OffEditionRequest createOffEditionRequest(String offId) {
        return null;
    }

    public void setFieldOfOffEditionRequest(OffEditionRequest offEditionRequest, String field, String value) {
    }

    //================================================================================
    // from past (can be erased)
    //================================================================================

    public void editCategory(String name, ArrayList<String> specialFeatures, ArrayList<Category> subCategory, ArrayList<Product> productList) {
    }

    public void addCategory(String name, Category parent, ArrayList<String> specialFeatures, ArrayList<Category> subCategory, ArrayList<Product> productList) {
    }

    public ArrayList<Product> sortCategory(Category category, String sortName) {
        return null;
    }

    //================================================================================
    // could not understand!
    //================================================================================

    public HashMap<String, Object> getAllUsersList() {
        return null;
    }

    public HashMap<String, Object> getAllProductsList() {
        return null;
    }

    public HashMap<String, Object> getAllDiscountCodesList() {
        return null;
    }

    public HashMap<String, Object> getAllRequestsList() {
        return null;
    }

    public HashMap<String, Object> getAllCategoriesList() {
        return null;
    }

    public void getAllOffsList() {
    }

}
