package model.request;

import model.Off;
import model.user.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class OffEditionRequest extends Request{
    private Seller seller;
    private String offId;

    public OffEditionRequest(Seller seller, String offId, HashMap<String, String> fieldsAndValues) {
        super(fieldsAndValues);
        this.seller = seller;
        this.offId = offId;
    }


    @Override
    public void apply() {
        Off off = seller.getOffByOffId(offId);
        if (off == null) {
            return;
        }

        if (fieldsAndValues.containsKey("startTime")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");
            try {
                off.setStartTime(simpleDateFormat.parse(fieldsAndValues.get("startTime")));
            } catch (ParseException parseException) {
                return;
            }
        } if (fieldsAndValues.containsKey("endTime")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");
            try {
                off.setEndTime(simpleDateFormat.parse(fieldsAndValues.get("startTime")));
            } catch (ParseException parseException) {
                return;
            }
        } if (fieldsAndValues.containsKey("discountAmount")) {
            off.setDiscountAmount(Integer.parseInt(fieldsAndValues.get("discountAmount")));
        }
    }

    @Override
    public String getType() {
        return "edit off";
    }
}
