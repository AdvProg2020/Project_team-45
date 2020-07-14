package controller;

import controller.managers.Manager;
import model.Market;
import model.request.Request;

public class RequestController implements Manager {
    private static final RequestController instance = new RequestController();
    private final Market market = Market.getInstance();

    private RequestController() {
    }

    public static RequestController getInstance() {
        return instance;
    }

    public Request createRequestByRequestName(String requestName) {
        return null;
    }

    @Override
    public Request getItemById(String Id) {
        return market.getRequestById(Id);
    }
}
