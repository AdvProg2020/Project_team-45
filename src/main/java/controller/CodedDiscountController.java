package controller;

import controller.managers.Creator;
import controller.managers.Editor;
import model.CodedDiscount;
import model.Market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CodedDiscountController implements Editor, Creator {
    private static CodedDiscountController instance = new CodedDiscountController();
    private Market market = Market.getInstance();

    private CodedDiscountController() {

    }

    public static CodedDiscountController getInstance() {
        return instance;
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
    public LinkedHashMap<String, InputValidator> getNecessaryFieldsToCreate() {
        LinkedHashMap<String, InputValidator> necessaryFields = new LinkedHashMap<>();
        necessaryFields.put("code", InputValidator.getSimpleTextValidator());
        necessaryFields.put("start date", InputValidator.getDateValidator());
        necessaryFields.put("end date", InputValidator.getDateValidator());
        necessaryFields.put("percentage", InputValidator.getPercentageValidator());
        necessaryFields.put("owner username", InputValidator.getExistingBuyerValidator());
        return necessaryFields;
    }

    @Override
    public LinkedHashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {
        CodedDiscount creatingDiscount = new CodedDiscount(filledFeatures);
        creatingDiscount.getOwner().addCodedDiscount(creatingDiscount);
        market.addDiscountToList(creatingDiscount);
    }

    @Override
    public CodedDiscount getItemById(String Id) {
        return Market.getInstance().getCodedDiscountByCode(Id);
    }

    @Override
    public LinkedHashMap<String, InputValidator> getAvailableFieldsToEdit() {
        LinkedHashMap<String, InputValidator> availableFieldsToEdit = new LinkedHashMap<>();
        availableFieldsToEdit.put("start date", InputValidator.getDateValidator());
        availableFieldsToEdit.put("end date", InputValidator.getDateValidator());
        availableFieldsToEdit.put("percentage", InputValidator.getPercentageValidator());
        return availableFieldsToEdit;
    }

    @Override
    public void editItem(Object editingObject, HashMap<String, String> changedFields) {
        CodedDiscount editingCodedDiscount = (CodedDiscount) editingObject;
        if (changedFields.containsKey("start date")){
            editingCodedDiscount.setStartDate(InputValidator.convertStringToDate(changedFields.get("start date")));
        }
        if (changedFields.containsKey("end date")){
            editingCodedDiscount.setEndDate(InputValidator.convertStringToDate(changedFields.get("end date")));
        }
        if (changedFields.containsKey("percentage")){
            editingCodedDiscount.setPercentage(Integer.parseInt(changedFields.get("percentage")));
        }
    }


}
