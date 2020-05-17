package model.request;

import java.util.HashMap;

public abstract class Request {
    private static Integer newRequestId = 1;
    protected String requestId;
    protected RequestStatus requestStatus;
    private HashMap<String, String> fieldsAndValues;

    public Request(HashMap<String, String> fieldsAndValues) {
        this.requestId = newRequestId.toString();
        newRequestId++;
        this.fieldsAndValues = fieldsAndValues;
    }

    public String getRequestId() {
        return requestId;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public abstract void apply();

    public abstract String getType();

    enum RequestStatus{
        WAITING_FOR_CONFIRMATION,
        ACCEPTED,
        DECLINED,
    }


}