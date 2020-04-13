package model;

public class Company {
    private String name;
    private String otherInformation;

    public Company(String name, String otherInformation) {
        this.name = name;
        this.otherInformation = otherInformation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", otherInformation='" + otherInformation + '\'' +
                '}';
    }
}
