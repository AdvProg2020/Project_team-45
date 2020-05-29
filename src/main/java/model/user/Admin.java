package model.user;

import model.Savable;

import java.util.HashMap;

public class Admin extends User implements Savable {

    public Admin(PersonalInfo personalInfo) {
        super(personalInfo);
    }

    public Admin(String id) {
        super(id);
    }

    @Override
    public String getRole() {
        return "admin";
    }

    @Override
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("personalInfo", personalInfo.convertToHashMap());
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap theMap) {
        personalInfo.setFieldsFromHashMap((HashMap) theMap.get("personalInfo"));
    }
}