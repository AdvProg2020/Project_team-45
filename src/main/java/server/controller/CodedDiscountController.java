package server.controller;

import server.controller.managers.Deleter;
import server.model.CodedDiscount;
import server.model.Market;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CodedDiscountController implements Deleter {
    private static final CodedDiscountController instance = new CodedDiscountController();
    private final Market market = Market.getInstance();
    private CodedDiscount currentDiscount; // hotam

    private CodedDiscountController() {

    }

    public static CodedDiscountController getInstance() {
        return instance;
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

    public void createItem(HashMap<String, String> filledFeatures) {
        CodedDiscount creatingDiscount = new CodedDiscount(filledFeatures);
        creatingDiscount.getOwner().addCodedDiscount(creatingDiscount);
        market.addCodedDiscountToList(creatingDiscount);
    }

    @Override
    public CodedDiscount getItemById(String Id) {
        return Market.getInstance().getCodedDiscountByCode(Id);
    }


    public boolean discountCodeExists(String code) {
        return getItemById(code) != null;
    }

    public CodedDiscount getCurrentDiscount() {
        return currentDiscount;
    }

    public void setCurrentDiscount(CodedDiscount currentDiscount) {
        this.currentDiscount = currentDiscount;
    }

    public HashMap<String, String> getDetailsHashMap(String viewingDiscountCode) {
        return market.getCodedDiscountByCode(viewingDiscountCode).convertToHashMap();
    }

    public List<String> getAllDiscountCodes() {
        return market.getAllCodedDiscounts().stream().map(CodedDiscount::getCode).collect(Collectors.toList());
    }

    public void changeDiscountPercentage(String editingDiscountCode, int percentage) {
        market.getCodedDiscountByCode(editingDiscountCode).setPercentage(percentage);
    }

    public void changeDiscountStartDate(String editingDiscountCode, Date newStartDate) {
        market.getCodedDiscountByCode(editingDiscountCode).setStartDate(newStartDate);
    }

    public void changeDiscountEndDate(String editingDiscountCode, Date newEndDate) {
        market.getCodedDiscountByCode(editingDiscountCode).setEndDate(newEndDate);
    }

    public boolean validateDate(String discountCode, Date startDate, Date endDate) {
        if (startDate == null) {
            startDate = market.getCodedDiscountByCode(discountCode).getStartDate();
        }
        if (endDate == null) {
            endDate = market.getCodedDiscountByCode(discountCode).getEndDate();
        }
        return startDate.before(endDate);
    }
}
