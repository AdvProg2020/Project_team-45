package controller;

import controller.managers.Printer;
import model.Market;
import model.request.Request;

import java.util.ArrayList;

public class RequestController implements Printer {
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

    public String getAllInListAsString() {
        ArrayList<Request> allRequests = market.getAllRequests();
        StringBuilder listString = new StringBuilder("Id,action,status\n");
        for (Request request : allRequests) {
            String info = request.getRequestId() + "," + request.getType() + "," + request.getRequestStatus() + "\n";
            listString.append(info);
        }
        return listString.toString();
    }

    public String getDetailStringById(String Id) {
        Request showingRequest = getItemById(Id);
        if (showingRequest == null)
            return null;
        return showingRequest.toString();
    }

    @Override
    public Request getItemById(String Id) {
        return market.getRequestById(Id);
    }
}
