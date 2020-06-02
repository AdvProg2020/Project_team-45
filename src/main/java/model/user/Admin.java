package model.user;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
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
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("personalInfo", (new Gson()).toJson(personalInfo));
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        personalInfo = (new Gson()).fromJson(theMap.get("personalInfo"), PersonalInfo.class);
    }
}