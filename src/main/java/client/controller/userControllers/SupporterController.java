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

    public ArrayList<ArrayList<String>> getActiveUserAllActiveChats() {
        try {
            return (ArrayList<ArrayList<String>>) MethodStringer.sampleMethod(getClass(),
                    "getSellerCompany");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }


}
