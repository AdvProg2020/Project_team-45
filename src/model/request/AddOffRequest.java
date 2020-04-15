package model.request;

import model.Off;

public class AddOffRequest extends Request{
    private Off off;

    public AddOffRequest(String requestId, Off off) {
        super(requestId);
        this.off = off;
    }

    @Override
    public void apply() {

    }
}
