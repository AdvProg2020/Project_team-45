package controller;

import model.request.*;

public class RequestController {
    private MainController mainController;

    public RequestController(MainController mainController) {
        this.mainController = mainController;
    }

    public Request createRequestByRequestName(String requestName) {
        return null;
    }

    public void setFieldOfRequest(Request request, String field, String value) {}

    public Request getRequestById(String requestId) {
        return null;
    }

}
