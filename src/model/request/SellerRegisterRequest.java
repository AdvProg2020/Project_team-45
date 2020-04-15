package model.request;

import model.user.Seller;

public class SellerRegisterRequest extends Request {
    private Seller seller;

    public SellerRegisterRequest(String requestId, Seller seller) {
        super(requestId);
        this.seller = seller;
    }

    @Override
    public void apply() {

    }
}
