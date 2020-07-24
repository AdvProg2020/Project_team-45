package client.controller;

import client.network.MethodStringer;

public class BankController {
    private static BankController instance = new BankController();

    private BankController() {
    }

    public static BankController getInstance() {
        return instance;
    }

    public void chargeWallet(Integer parseInt) throws Throwable {
        MethodStringer.sampleMethod(getClass(), "chargeWallet", parseInt);
    }

    public void depositAccount(Integer parseInt) throws Throwable {
        MethodStringer.sampleMethod(getClass(), "depositAccount", parseInt);
    }
}
