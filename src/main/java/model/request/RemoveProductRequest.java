package model.request;

public class RemoveProductRequest extends Request{
    private String productId;

    public RemoveProductRequest(String productId) {
        super();
        this.productId = productId;
    }

    @Override
    public void apply() {

    }

    @Override
    public String getType() {
        return "remove product";
    }
}
