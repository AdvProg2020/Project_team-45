package client.controller;

import client.controller.managers.Manager;
import client.network.MethodStringer;
import server.model.Market;
import server.model.request.Request;

import java.util.HashMap;
import java.util.List;

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
        // TODO : must be removed
        return market.getRequestById(Id);
    }

    public List<String> getAllRequestsIds() {
        try {
            return (List<String>) MethodStringer.sampleMethod(getClass(), "getAllRequestsIds");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> getRequestInfo(String requestId) {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getRequestInfo", requestId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> getRequestFieldsAndValues(String requestId) {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getRequestFieldsAndValues", requestId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> getRequestsCommentInfo(String requestId) {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getRequestsCommentInfo", requestId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String getSellerId(String requestId) {
        try {
            return (String) MethodStringer.sampleMethod(getClass(), "getSellerId", requestId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String getOffId(String requestId) {
        try {
            return (String) MethodStringer.sampleMethod(getClass(), "getOffId", requestId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String getProductId(String requestId) {
        try {
            return (String) MethodStringer.sampleMethod(getClass(), "getProductId", requestId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
