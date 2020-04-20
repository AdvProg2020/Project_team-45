package controller;

import model.CodedDiscount;

import java.util.Date;

public class CodedDiscountController {
    private MainController mainController;

    public CodedDiscountController(MainController mainController) {
        this.mainController = mainController;
    }

    public CodedDiscount getCodedDiscountByCode(String discountCode) {
        return null;
    }

    public CodedDiscount createCodedDiscount() {
        return null;
    }

    public void setFieldOfCodedDiscount(CodedDiscount codedDiscount, String field, String value) {}

    public void editCodedDiscount(String discountCode, CodedDiscount codedDiscount) {
    }

    public void removeCodedDiscount(String discountCode) {
    }

    public boolean isDiscountCodeValid(String code) {
        return false;
    }
}
