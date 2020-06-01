package model.request;

import model.IdKeeper;
import model.IdRecognized;
import model.Market;
import model.Savable;

import java.util.HashMap;

public abstract class Request extends IdRecognized implements Savable {
    protected RequestStatus requestStatus;
    protected HashMap<String, String> fieldsAndValues;

    public Request() {
        this.id = getType() + IdKeeper.getInstance().getRequestsNewId();
    }

    public Request(HashMap<String, String> fieldsAndValues) {
        this.id = getType() + IdKeeper.getInstance().getRequestsNewId();
        this.fieldsAndValues = fieldsAndValues;
    }

    public Request(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
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
        Market.getInstance().removeRequestById(id);
    }

    public void decline() {
        Market.getInstance().removeRequestById(id);
    }

    enum RequestStatus{
        WAITING_FOR_CONFIRMATION,
        ACCEPTED,
        DECLINED,
    }

    @Override
    public String toString() {
        return  "request type:" + getType() + "requestId:" + id +
                ", requestStatus:" + requestStatus;
    }

    @Override
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("requestStatus", requestStatus);
        result.put("fieldsAndValues", fieldsAndValues);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        id = (String) theMap.get("id");
        requestStatus = (RequestStatus) theMap.get("requestStatus");
        fieldsAndValues = (HashMap<String, String>) theMap.get("fieldsAndValues");
    }
}