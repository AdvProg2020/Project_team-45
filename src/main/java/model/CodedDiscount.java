package model;

import model.user.Buyer;

import java.util.Date;

public class CodedDiscount {
    private String code;
    private Date startDate;
    private Date endDate;
    private int percentage;
    private Buyer owner;

    public CodedDiscount(String code, Date startDate, Date endDate, int percentage, Buyer owner) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
        this.owner = owner;
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
        try {
            return (CodedDiscount) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return  "code:" + code +
                ", startDate:" + startDate +
                ", endDate:" + endDate +
                ", percentage:" + percentage +
                ", owner:" + owner.getPersonalInfo().getUsername();
    }
}
