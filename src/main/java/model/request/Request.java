package model.request;

import model.Market;

import java.util.HashMap;

public abstract class Request {
    private static Integer newRequestId = 1;
    protected String requestId;
    protected RequestStatus requestStatus;
    protected HashMap<String, String> fieldsAndValues;

    public Request() {

    }

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

    public void accept(){
        apply();
        Market.getInstance().removeRequestById(requestId);
    }

    public void decline() {
        Market.getInstance().removeRequestById(requestId);
    }

    enum RequestStatus{
        WAITING_FOR_CONFIRMATION,
        ACCEPTED,
        DECLINED,
    }

    @Override
    public String toString() {
        return  "request type:" + getType() + "requestId:" + requestId +
                ", requestStatus:" + requestStatus;
    }
}