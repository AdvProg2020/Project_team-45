package model;

public class Company {
    private String name;
    private String otherInformation;

    public Company(String name) {
        this.name = name;
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
