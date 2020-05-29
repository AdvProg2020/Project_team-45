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
    public HashMap<String, Object> ConvertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("personalInfo", personalInfo.ConvertToHashMap());
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        personalInfo.setFieldsFromHashMap((HashMap<String, Object>) theMap.get("personalInfo"));
    }
}