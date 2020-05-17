package model;

import java.util.HashMap;

public class Company {
    private String name;
    private String otherInformation;

    public Company(String name) {
        this.name = name;
    }

    public Company(HashMap<String, String> filledFeatures) {
        this.name = filledFeatures.get("company name");
        this.otherInformation = filledFeatures.get("company info");
    }

    public String getName() {
        return name;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

}
