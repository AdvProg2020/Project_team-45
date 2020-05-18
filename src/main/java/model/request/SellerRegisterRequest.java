package model.request;

import model.user.Seller;

public class SellerRegisterRequest extends Request {
    private Seller seller;

    public SellerRegisterRequest(String requestId, Seller seller) {
        super(requestId);
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    @Override
    public void apply() {

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
