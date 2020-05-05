package controller;

import model.CodedDiscount;

public class CodedDiscountController implements Deleter {
    private static CodedDiscountController instance = new CodedDiscountController();

    private CodedDiscountController() {

    }

    public static CodedDiscountController getInstance() {
        return instance;
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

    public void deleteItemById(String Id) {
        // TODO : hatami
    }

    public String printAllInList() {
        // TODO : hatami
        return null;
    }

    public String printDetailedById(String Id) {
        // TODO : hatami
        return null;
    }
}
