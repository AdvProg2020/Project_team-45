package model;

import controller.InputValidator;
import controller.userControllers.BuyerController;
import model.user.Buyer;

import java.util.Date;
import java.util.HashMap;

public class CodedDiscount extends IdRecognized implements Savable {
    private String code;
    private Date startDate;
    private Date endDate;
    private int percentage;
    private Buyer owner;

    public CodedDiscount(HashMap<String, String> filledFeatures) {
        this.id = "" + IdKeeper.getInstance().getCodedDiscountsNewId();
        this.code = filledFeatures.get("code");
        this.startDate = InputValidator.convertStringToDate(filledFeatures.get("start date"));
        this.endDate = InputValidator.convertStringToDate(filledFeatures.get("end date"));
        this.percentage = Integer.parseInt(filledFeatures.get("percentage"));
        this.owner = BuyerController.getInstance().getItemById(filledFeatures.get("owner username"));
    }

    public CodedDiscount(CodedDiscount toClone) {
        this.id = toClone.id;
        this.code = toClone.code;
        this.startDate = toClone.startDate;
        this.endDate = toClone.endDate;
        this.percentage = toClone.percentage;
        this.owner = toClone.owner;
    }

    public CodedDiscount(String id) {
        this.id = id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getCode() {
        return code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Buyer getOwner() {
        return owner;
    }

    @Override
    public CodedDiscount clone() {
        return new CodedDiscount(this);
    }

    @Override
    public String toString() {
        return  "code:" + code +
                ", startDate:" + startDate +
                ", endDate:" + endDate +
                ", percentage:" + percentage +
                ", owner:" + owner.getPersonalInfo().getUsername();
    }

    @Override
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("startDate", startDate);
        result.put("endDate", endDate);
        result.put("percentage", percentage);
        result.put("owner", owner.getId());
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        startDate = (Date) theMap.get("startDate");
        endDate = (Date) theMap.get("endDate");
        percentage = (int) theMap.get("percentage");
        owner = (Buyer) Market.getInstance().getUserById((String) theMap.get("owner"));
    }

    @Override
    public String getId() {
        return id;
    }
}
