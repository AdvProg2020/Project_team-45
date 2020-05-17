package model.user;

import model.request.Request;

import java.util.ArrayList;

public class Admin extends User {
    private static ArrayList<Request> listOfRequests = new ArrayList<>();

    public Admin(PersonalInfo personalInfo) {
        super(personalInfo);
    }

    public static ArrayList<Request> getListOfRequests() {
        return null;
    }

    public static Request getRequestByRequestId(String requestId) {
        return null;
    }

    @Override
    public String getRole() {
        return "admin";
    }
}