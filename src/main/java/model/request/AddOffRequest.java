package model.request;

import model.Market;
import model.Off;
import model.Product;
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
        seller.getListOfOffs().put(off.getOffId(), off);
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");
        try {
            startTime = simpleDateFormat.parse(fieldsAndValues.get("startTime"));
        } catch (ParseException parseException) {
            return new Date();
        }
        return startTime;
    }

    private Date extractEndTime() {
        Date endTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");
        try {
            endTime = simpleDateFormat.parse(fieldsAndValues.get("startTime"));
        } catch (ParseException parseException) {
            return new Date();
        }
        return endTime;
    }

    @Override
    public String getType() {
        return "add off";
    }
}
