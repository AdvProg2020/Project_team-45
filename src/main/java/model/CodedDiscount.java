package model;

import java.util.Date;

public class CodedDiscount {
    private String code;
    private Date startDate;
    private Date endDate;
    private int percentage;

    public CodedDiscount(String code, Date startDate, Date endDate, int percentage) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
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

    @Override
    public CodedDiscount clone() {
        try {
            return (CodedDiscount) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
