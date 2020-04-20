package model.request;

public class RemoveProductRequest extends Request{
    private String productId;

    public RemoveProductRequest(String requestId, String productId) {
        super(requestId);
        this.productId = productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public void apply() {

    }
}
