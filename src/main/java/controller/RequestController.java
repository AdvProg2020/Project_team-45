package controller;

import controller.managers.Printer;
import model.request.*;

public class RequestController implements Printer {
    private static RequestController instance = new RequestController();

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
        // TODO : hatami
        return null;
    }

    public String printDetailedById(String Id) {
        // TODO : hatami
        return null;
    }
}
