package client.controller;

import client.network.MethodStringer;
import server.model.CodedDiscount;
import server.model.Market;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CodedDiscountController {
    private static final CodedDiscountController instance = new CodedDiscountController();
    private final Market market = Market.getInstance();
    private CodedDiscount currentDiscount;

    private CodedDiscountController() {
    }

    public static CodedDiscountController getInstance() {
        return instance;
    }

    public boolean deleteItemById(String Id) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "deleteItemById", Id);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public void createItem(HashMap<String, String> filledFeatures) {
        try {
            MethodStringer.sampleMethod(getClass(), "createItem", filledFeatures);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public boolean discountCodeExists(String code) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "discountCodeExists", code);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    // used in display discount panel

    public HashMap<String, String> getCurrentDiscount() {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getCurrentDiscount");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void setCurrentDiscountById(String currentDiscountId) {
        try {
            MethodStringer.sampleMethod(getClass(), "setCurrentDiscountById", currentDiscountId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public HashMap<String, String> getDetailsHashMap(String viewingDiscountCode) {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getDetailsHashMap", viewingDiscountCode);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public List<String> getAllDiscountCodes() {
        try {
            return (List<String>) MethodStringer.sampleMethod(getClass(), "getAllDiscountCodes");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void changeDiscountPercentage(String editingDiscountCode, int percentage) {
        try {
            MethodStringer.sampleMethod(getClass(), "changeDiscountPercentage", editingDiscountCode, percentage);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void changeDiscountStartDate(String editingDiscountCode, Date newStartDate) {
        try {
            MethodStringer.sampleMethod(getClass(), "changeDiscountStartDate", editingDiscountCode, newStartDate);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void changeDiscountEndDate(String editingDiscountCode, Date newEndDate) {
        try {
            MethodStringer.sampleMethod(getClass(), "changeDiscountEndDate", editingDiscountCode, newEndDate);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public boolean validateDate(String discountCode, Date startDate, Date endDate) {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "validateDate", discountCode, startDate, endDate);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }
}
