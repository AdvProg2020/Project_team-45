package controller;

import model.request.*;

public class RequestController {
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

}
