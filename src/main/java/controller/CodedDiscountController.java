package controller;

import controller.managers.Creator;
import controller.managers.Deleter;
import model.CodedDiscount;
import model.Market;

import java.util.ArrayList;
import java.util.HashMap;

public class CodedDiscountController implements Deleter, Creator {
    private static CodedDiscountController instance = new CodedDiscountController();
    private Market market = Market.getInstance();

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
        ArrayList<CodedDiscount> allCodedDiscounts = market.getAllCodedDiscounts();
        StringBuilder listString = new StringBuilder("code,owner,percentage\n");
        for (CodedDiscount codedDiscount : allCodedDiscounts) {
            String info = codedDiscount.getCode() + "," + codedDiscount.getOwner().getPersonalInfo().getUsername() + "," + codedDiscount.getPercentage() + "\n";
            listString.append(info);
        }
        return listString.toString();
    }

    public String printDetailedById(String Id) {
        // TODO : hatami
        return null;
    }

    @Override
    public HashMap<String, InputValidator> getNecessaryFieldsToCreate() {
        HashMap<String, InputValidator> fields = new HashMap<>();
        // TODO
        return fields;
    }

    @Override
    public HashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {
        // TODO : hatami
    }

    @Override
    public Object getItemById(String Id) {
        return Market.getInstance().getCodedDiscountByCode(Id);
    }
}
