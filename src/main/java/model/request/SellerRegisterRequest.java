package model.request;

import model.Market;
import model.user.Seller;

public class SellerRegisterRequest extends Request {
    private final Seller seller;

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
}
