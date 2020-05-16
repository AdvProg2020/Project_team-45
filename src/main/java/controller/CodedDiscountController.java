package controller;

import controller.managers.Creator;
import controller.managers.Deleter;
import model.CodedDiscount;

import java.util.ArrayList;
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

    public String getAllInListAsString() {
        // TODO : hatami
        return null;
    }

    public String printDetailedById(String Id) {
        // TODO : hatami
        return null;
    }

    @Override
    public ArrayList<String> getNecessaryFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {

    }

    @Override
    public Object getItemById(String Id) {
        return null;
    }
}
