package model.request;

import model.Off;

public class OffEditionRequest extends Request{
    private Off off;
    private String offId;

    public OffEditionRequest(String requestId, Off off, String offId) {
        super(requestId);
        this.off = off;
        this.offId = offId;
    }

    @Override
    public void apply() {

    }
}
