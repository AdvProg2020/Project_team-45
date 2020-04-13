package model;

import java.util.Date;

public class CodedDiscount {
    private String discountCode;
    private Date startDate;
    private Date endDate;

    public CodedDiscount(String discountCode, Date startDate, Date endDate) {
        this.discountCode = discountCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CodedDiscount{" +
                "discountCode='" + discountCode + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
