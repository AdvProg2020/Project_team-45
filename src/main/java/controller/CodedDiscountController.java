package controller;

import controller.managers.Creator;
import controller.managers.Deleter;
import model.CodedDiscount;

import java.util.HashMap;

public class CodedDiscountController implements Deleter, Creator {
    private static CodedDiscountController instance = new CodedDiscountController();

    private CodedDiscountController() {

    }

    public static CodedDiscountController getInstance() {
        return instance;
    }

    public CodedDiscount getCodedDiscountByCode(String discountCode) {
        return null;
    }

    public void setFieldOfCodedDiscount(CodedDiscount codedDiscount, String field, String value) {
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

    public HashMap<String, String> getNecessaryFields() {
        // TODO : hatami
        return null;
    }
}
