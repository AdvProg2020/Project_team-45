package view;

import model.*;
import model.request.ProductEditionRequest;
import model.user.Seller;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ControllerBySMM {
    public void setUserField(User user, String fieldName, String newValue) {
    }

    public void setDiscountCodeField(CodedDiscount codedDiscount, String fieldName, String newValue) {
    }

    public boolean CodedDiscountIsValid() {
    }

    public void addFeatureToCategory(Category category, String feature) {
    }

    public void removeFeatureFromCategory(Category category, String feature) {
    }

    public void addCategory(String name, Category parent, ArrayList<String> features) {
    }

    public void getSellerSalesHistory(Seller seller) {
    }

    public void getProductBuyers(Product product) {
    }

    public ProductEditionRequest createProductEditionRequest(Product product) {
    }

    public void addFieldToProductEditionRequest(ProductEditionRequest request, String field, String Value) {
    }

    public void makeAddingProductRequest() {
    }

    public void createOffEditionRequest() {
    }

    public void createOffAddingRequest() {
    }

    public void addFieldToOffEditionRequest() {
    }

    public void getBuyerCart(User Buyer) {
    }

    public HashMap<String, Object> getCartProducts() {
        return null;
    }

    public void changeCartProductAmount(int amount) {
    }

    public void purchaseTheCart(Cart cart) {
    }

    public HashMap<String, Object> getUserOrders(User user) {
        return null;
    }

    public void rateProduct(Product product, int rate) {
    }

    public ArrayList<CodedDiscount> getUserDiscountCodes(User buyer) {
        return null;
    }

    public void addFilter(ProductFilters filter, String newFilter) {
    }

    public void getAvailableFilters(Category category) {
    }

    public void removeFilter(ProductFilters filter, String filterToRemove) {
    }

    public void addProductToCart(Product product, User user) {
    }

    public void getProductComments(Product product) {
    }

    public void makeCommentRequest() {
    }

    public void getAllOffsList() {
    }


}
