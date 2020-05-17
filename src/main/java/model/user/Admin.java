package model.user;

import model.request.Request;

import java.util.ArrayList;

public class Admin extends User {

    public Admin(PersonalInfo personalInfo) {
        super(personalInfo);
    }

    public static Request getRequestByRequestId(String requestId) {
        return null;
    }

    @Override
    public String getRole() {
        return "admin";
    }
}