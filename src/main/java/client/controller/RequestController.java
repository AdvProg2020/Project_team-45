package client.controller;

import client.controller.managers.Manager;
import server.model.Market;
import server.model.request.Request;

import java.util.List;
import java.util.stream.Collectors;

public class RequestController implements Manager {
    private static final RequestController instance = new RequestController();
    private final Market market = Market.getInstance();

    // TODO : bring apply here?

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

    public List<String> getAllRequestsIds() {
        return market.getAllRequests().stream().map(Request::getId).collect(Collectors.toList());
    }
}
