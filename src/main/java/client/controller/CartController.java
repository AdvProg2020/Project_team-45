package client.controller;

import client.network.MethodStringer;

public class CartController {
    private static final CartController instance = new CartController();

    public static CartController getInstance() {
        return instance;
    }

    private CartController() {
    }

    public void resetCart() {
        try {
            MethodStringer.sampleMethod(getClass(), "resetCart");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
