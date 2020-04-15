package model.user;

import model.request.Request;

import java.util.ArrayList;
import java.util.Date;

public class Admin extends User {
    private static ArrayList<Request> listOfRequests;

    public Admin(String username, String firstName, String lastName, String emailAddress,
                 String phoneNumber, String password, String role, ArrayList<Request> listOfRequests) {
        super(username, firstName, lastName, emailAddress, phoneNumber, password, role);
        this.listOfRequests = listOfRequests;
    }

    public void addAdmin(String username, String firstName, String lastName, String emailAddress,
                         String phoneNumber, String password) {
    }

//    public void deleteUserByUsername(String username) {
//
//    }
//
//    public void removeProductByProductId(String productId) {
//
//    }

    public void editCodedDiscountByDiscountCode(String discountCode, Date startDate, Date endDate) {

    }

    public void removeCodedDiscountByDiscountCode(String discountCode) {

    }

//    public void editCategoryByName(String name, CategoryInfo ... ?) {
//
//    }

//    public void addCategory(CategoryInfo ... ?) {
//
//    }

//    public void removeCategoryByName(String name) {
//
//    }

    public ArrayList<Request> getListOfRequests() {
        return null;
    }

    public Request getRequestByRequestId(String requestId) {
        return null;
    }

    public void acceptRequest(String requestId) {

    }

    public void declineRequest(String requestId) {

    }

    @Override
    public String getRole() {
        return "Admin";
    }
}
