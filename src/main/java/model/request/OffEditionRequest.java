package model.request;

import model.Market;
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

    public OffEditionRequest(String id) {
        super(id);
    }

    @Override
    public void apply() {
        Off off = seller.getOffByOffId(offId);
        if (off == null) {
            return;
        }

        if (fieldsAndValues.containsKey("startTime") && !fieldsAndValues.get("startTime").equals("")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            try {
                off.setStartTime(simpleDateFormat.parse(fieldsAndValues.get("startTime")));
            } catch (ParseException parseException) {
                return;
            }
        } if (fieldsAndValues.containsKey("endTime") && !fieldsAndValues.get("endTime").equals("")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            try {
                off.setEndTime(simpleDateFormat.parse(fieldsAndValues.get("startTime")));
            } catch (ParseException parseException) {
                return;
            }
        } if (fieldsAndValues.containsKey("discountAmount") && !fieldsAndValues.get("discountAmount").equals("")) {
            off.setDiscountAmount(Integer.parseInt(fieldsAndValues.get("discountAmount")));
        }
    }

    @Override
    public String getType() {
        return "edit off";
    }

    @Override
    public String toString() {
        return super.toString()+
                "seller:" + seller.getPersonalInfo().getUsername() +
                ", offId:" + offId;
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = super.convertToHashMap();
        result.put("seller", seller.getId());
        result.put("offId", offId);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        super.setFieldsFromHashMap(theMap);
        seller = (Seller) Market.getInstance().getUserById(theMap.get("seller"));
        offId = theMap.get("offId");
    }
}
