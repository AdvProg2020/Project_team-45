package client.controller.userControllers;

import client.network.MethodStringer;

import java.util.ArrayList;

public class SupporterController {
    private static final SupporterController instance = new SupporterController();

    private SupporterController() {
    }

    public static SupporterController getInstance() {
        return instance;
    }

    public ArrayList<ArrayList<String>> getActiveSupporterAllActiveChats() {
        try {
            return (ArrayList<ArrayList<String>>) MethodStringer.sampleMethod(getClass(),
                    "getSellerCompany");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getOnlineSupporter() {
        return null;
    }

    public void startNewChatForActiveUser(String Username) {

    }

    public void addMassageForBuyer(String massageText) {

    }

    public void addMassageForSupporter() {

    }
}
