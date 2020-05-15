package model.request;

public abstract class Request {
    private static Integer newRequestId = 1;
    protected String requestId;
    protected RequestStatus requestStatus;

    public Request() {
        this.requestId = newRequestId.toString();
        newRequestId++;
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

    enum RequestStatus{
        WAITING_FOR_CONFIRMATION,
        ACCEPTED,
        DECLINED,
    }


}