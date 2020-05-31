package model.request;

import model.Market;
import model.Off;
import model.product.Product;
import model.user.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class AddOffRequest extends Request{
    private Seller seller;

    public AddOffRequest(Seller seller, HashMap<String, String> fieldsAndValues) {
        super(fieldsAndValues);
        this.seller = seller;
    }

    @Override
    public void apply() {
        ArrayList<Product> products = extractProducts();
        Date startTime = extractStartTime();
        Date endTime = extractEndTime();
        int discountAmount;
        try {
            discountAmount = Integer.parseInt(fieldsAndValues.get("discountAmount"));
        } catch (NumberFormatException numberFormatException) {
            return;
        }

        Off off = new Off(products, startTime, endTime, discountAmount);
        seller.getListOfOffs().put(off.getId(), off);
        ArrayList<String> productIds = new ArrayList<>();
        productIds.addAll(Arrays.asList(fieldsAndValues.get("productIds(separated by ',')").split("\\s*,\\s*")));
        for (String productId : productIds) {
            Product product = seller.getAvailableProductById(productId);
            if (product != null) {
                product.getSellerInfoForProductByUsername(seller.getUsername()).setOff(off);
            }
        }

        Market.getInstance().getAllOffs().add(off);
    }

    private ArrayList<Product> extractProducts() {
        ArrayList<String> productIds = new ArrayList<>();
        productIds.addAll(Arrays.asList(fieldsAndValues.get("productIds(separated by ',')")
                .split("\\s*,\\s*")));

        ArrayList<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product product = seller.getAvailableProductById(productId);
            if (product != null) {
                products.add(product);
            }
        }
        return products;
    }

    private Date extractStartTime() {
        Date startTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            startTime = simpleDateFormat.parse(fieldsAndValues.get("startTime"));
        } catch (ParseException parseException) {
            return new Date();
        }
        return startTime;
    }

    private Date extractEndTime() {
        Date endTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            endTime = simpleDateFormat.parse(fieldsAndValues.get("endTime"));
        } catch (ParseException parseException) {
            return new Date();
        }
        return endTime;
    }

    @Override
    public String getType() {
        return "add off";
    }

    @Override
    public String toString() {
        return super.toString() + "seller:" + seller.getPersonalInfo().getUsername();
    }

    @Override
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = super.convertToHashMap();
        result.put("seller", seller.getId());
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, Object> theMap) {
        super.setFieldsFromHashMap(theMap);
        seller = (Seller) Market.getInstance().getUserById((String) theMap.get("seller"));

    }
}
