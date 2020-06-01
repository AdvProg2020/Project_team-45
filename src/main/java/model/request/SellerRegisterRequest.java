package model.request;

import model.Market;
import model.user.Seller;

import java.util.HashMap;

public class SellerRegisterRequest extends Request {
    private Seller seller;

    public SellerRegisterRequest(Seller seller) {
        super();
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    @Override
    public void apply() {
        Market.getInstance().addUserToList(seller);
        Market.getInstance().removeRequestedSellerFromList(seller);
    }

    @Override
    public String getType() {
        return "seller register";
    }

    @Override
    public String toString() {
        return super.toString() +
                "seller:" + seller.getPersonalInfo().getUsername();
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
        seller = Market.getInstance().getRequestedSellerById((String) theMap.get("seller"));
    }
}
