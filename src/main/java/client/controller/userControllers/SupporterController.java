package client.controller.userControllers;

import client.network.MethodStringer;

import java.util.ArrayList;
import java.util.HashMap;

public class SupporterController {
    private static final SupporterController instance = new SupporterController();

    private SupporterController() {
    }

    public static SupporterController getInstance() {
        return instance;
    }

    public HashMap<String, ArrayList<String>> getActiveSupporterAllActiveChats() {
        try {
            return (HashMap<String, ArrayList<String>>) MethodStringer.sampleMethod(getClass(),
                    "getActiveSupporterAllActiveChats");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getOnlineSupporters() {
        try {
            return (ArrayList<String>) MethodStringer.sampleMethod(getClass(),
                    "getOnlineSupporters");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void startNewChatForActiveBuyer(String Username) {
        try {
            MethodStringer.sampleMethod(getClass(),"startNewChatForActiveBuyer", Username);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void addMassageForBuyer(String massageText) {
        try {
            MethodStringer.sampleMethod(getClass(),"addMassageForBuyer", massageText);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void addMassageForSupporter(String massageText, String buyerUsername) {
        try {
            MethodStringer.sampleMethod(getClass(),"getSellerCompany", massageText, buyerUsername);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public ArrayList<String> getMessages() {
        try {
            return (ArrayList<String>) MethodStringer.sampleMethod(getClass(),
                    "getMessages");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
