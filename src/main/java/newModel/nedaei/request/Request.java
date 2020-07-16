package newModel.nedaei.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.IdKeeper;
import model.IdRecognized;
import model.Market;
import model.Savable;

import java.util.HashMap;

public abstract class Request extends IdRecognized implements Savable {
    protected RequestStatus requestStatus;
    protected HashMap<String, String> fieldsAndValues;

    public Request() {
        requestStatus = RequestStatus.WAITING_FOR_CONFIRMATION;
        this.id = getType() + IdKeeper.getInstance().getRequestsNewId();
    }

    public Request(HashMap<String, String> fieldsAndValues) {
        this.id = getType() + IdKeeper.getInstance().getRequestsNewId();
        this.fieldsAndValues = fieldsAndValues;
        requestStatus = RequestStatus.WAITING_FOR_CONFIRMATION;
    }

    public HashMap<String, String> getFieldsAndValues() {
        return fieldsAndValues;
    }

    public Request(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getRequestStatus() {
        return requestStatus.toString();
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public abstract void apply();

    public abstract String getType();

    public void accept(){
        apply();
        Market.getInstance().removeRequestById(id);
        requestStatus = RequestStatus.ACCEPTED;
    }

    public void decline() {
        Market.getInstance().removeRequestById(id);
        requestStatus = RequestStatus.DECLINED;
    }

    @Override
    public String toString() {
        return  "request type:" + getType() + "requestId:" + id +
                ", requestStatus:" + requestStatus;
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("requestStatus", (new Gson()).toJson(requestStatus));
        result.put("fieldsAndValues", (new Gson()).toJson(fieldsAndValues));
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        requestStatus = (new Gson()).fromJson(theMap.get("requestStatus"), RequestStatus.class);
        fieldsAndValues = (new Gson()).fromJson(theMap.get("fieldsAndValues"), new TypeToken<HashMap<String, String>>(){}.getType());
    }

    enum RequestStatus{
        WAITING_FOR_CONFIRMATION,
        ACCEPTED,
        DECLINED;

        @Override
        public String toString() {
            return super.toString();
        }
    }
}