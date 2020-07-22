package server.newModel.bagheri;

import server.newModel.nedaei.database.IdRecognized;

public class Company extends IdRecognized {
    private String name;
    private String otherInformation;

    public Company(String name, String otherInformation) {
        this.name = name;
        this.otherInformation = otherInformation;
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
