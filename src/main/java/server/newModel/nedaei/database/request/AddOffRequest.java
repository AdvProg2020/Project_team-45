package server.newModel.nedaei.database.request;

import com.google.gson.Gson;
import server.model.Market;
import server.model.Off;
import server.model.product.Product;
import server.model.user.Seller;

import java.util.HashMap;

public class AddOffRequest extends Request {
    private Seller seller;
    private Off off;

    public AddOffRequest(Seller seller, Off off) {
        super();
        this.seller = seller;
        this.off = off;
    }

    public AddOffRequest(String id) {
        super(id);
    }

    @Override
    public void apply() {
        seller.getListOfOffs().put(off.getId(), off);
        for (Product product : off.getProductsList()) {
            System.out.println("" + product + seller);
            product.getSellerInfoForProductByUsername(seller.getUsername()).setOff(off);
        }
        Market.getInstance().getAllOffs().add(off);
    }

    public Off getOff() {
        return off;
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
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = super.convertToHashMap();
        result.put("seller", seller.getId());
        result.put("off", (new Gson()).toJson(off));

        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        super.setFieldsFromHashMap(theMap);
        seller = (Seller) Market.getInstance().getUserById(theMap.get("seller"));
        off = (new Gson()).fromJson(theMap.get("off"), Off.class);
//        get requested off
    }
}
