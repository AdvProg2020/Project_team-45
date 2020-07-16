package newModel.nedaei.request;

import model.Market;
import model.product.Product;
import model.product.ProductSellInfo;
import model.user.Seller;

import java.util.HashMap;

public class AddProductRequest extends Request {
    private String mode;
    private ProductSellInfo productSellInfo;
    private Product product;
    private Seller seller;

    public AddProductRequest(String mode, ProductSellInfo productSellInfo) {
        super();
        this.mode = mode;
        this.productSellInfo = productSellInfo;
        product = productSellInfo.getProduct();
        seller = productSellInfo.getSeller();
    }

    public AddProductRequest(String id) {
        super(id);
    }


    public ProductSellInfo getProductSellInfo() {
        return productSellInfo;
    }

    public Seller getSeller() {
        return seller;
    }

    @Override
    public void apply() {
        product = productSellInfo.getProduct();
        seller = productSellInfo.getSeller();
        if (mode.equalsIgnoreCase("new")) {
            product.setCompany(productSellInfo.getSeller().getCompany());
            product.setDefaultSellInfo(productSellInfo);
            product.getCategory().addProduct(product);
            Market.getInstance().getAllProducts().add(product);
        }
        Market.getInstance().addSellInfoToList(productSellInfo);
        product.addSeller(productSellInfo);
        productSellInfo.getSeller().getAvailableProducts().put(product, productSellInfo);
    }

    @Override
    public String getType() {
        return "add product";
    }

    @Override
    public String toString() {
        return super.toString() +
                "seller:" + seller +
                ", mode:" + mode +
                ", product:" + product.getName();
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = super.convertToHashMap();
        result.put("productSellInfo", productSellInfo.getId());
        result.put("mode", mode);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        super.setFieldsFromHashMap(theMap);
        mode = theMap.get("mode");
        productSellInfo = Market.getInstance().getProductSellInfoById(theMap.get("productSellInfo"));
    }

}
