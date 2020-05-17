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

    public boolean deleteItemById(String Id) {
        CodedDiscount removingCodedDiscount = getItemById(Id);
        if (removingCodedDiscount == null)
            return false;
        removeCodedDiscount(removingCodedDiscount);
        return true;
    }

    private void removeCodedDiscount(CodedDiscount removingCodedDiscount) {
        removingCodedDiscount.getOwner().removeCodedDiscountFromList(removingCodedDiscount);
        market.removeCodedDiscountFromList(removingCodedDiscount);
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

    public String getDetailStringById(String Id) {
        CodedDiscount showingCodedDiscount = getItemById(Id);
        if (showingCodedDiscount == null)
            return null;
        return showingCodedDiscount.toString();
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
    public CodedDiscount getItemById(String Id) {
        return Market.getInstance().getCodedDiscountByCode(Id);
    }
}
