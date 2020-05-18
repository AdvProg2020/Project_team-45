package controller;

import controller.managers.Printer;
import model.Market;
import model.request.Request;

import java.util.ArrayList;

public class RequestController implements Printer {
    private static RequestController instance = new RequestController();
    private Market market = Market.getInstance();

    private RequestController() {
    }

    public static RequestController getInstance() {
        return instance;
    }

    public Request createRequestByRequestName(String requestName) {
        return null;
    }

    public void setFieldOfRequest(Request request, String field, String value) {}

    public Request getRequestById(String requestId) {
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
        return "type:"showingRequest.toString();
    }

    @Override
    public Request getItemById(String Id) {
        return market.getRequestById(Id);
    }
}
