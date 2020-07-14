package controller;

import controller.managers.Editor;
import model.CodedDiscount;
import model.Market;

import java.util.HashMap;

public class CodedDiscountController implements Editor {
    private static final CodedDiscountController instance = new CodedDiscountController();
    private final Market market = Market.getInstance();
    private CodedDiscount currentDiscount;

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
}
