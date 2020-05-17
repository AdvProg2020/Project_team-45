package model.request;

import model.Market;
import model.Off;
import model.Product;
import model.ProductSellInfo;
import model.category.Category;
import model.category.FinalCategory;
import model.user.Seller;

import java.util.HashMap;

public class AddProductRequest extends Request {
    private Seller seller;
    private String mode;
    private Product product;

    public AddProductRequest(String mode, Seller seller, HashMap<String, String> fieldsAndValues) {
        super(fieldsAndValues);
        this.seller = seller;
        this.mode = mode;
    }

    @Override
    public void apply() {
        if (mode.equalsIgnoreCase("existing")) {
            product = Market.getInstance().getProductById(fieldsAndValues.get("productId"));
            if (product == null) {
                return;
            }
        } else if (mode.equalsIgnoreCase("new")){
            Category category = Market.getInstance().getCategoryByName(fieldsAndValues.get("categoryName"));
            if (!category.getType().equalsIgnoreCase("FinalCategory")) {
                return;
            }
            product = new Product(fieldsAndValues.get("name"), ((FinalCategory)category)
                    , fieldsAndValues.get("description"));
            product.getSellersList().put(seller, new ProductSellInfo(product, seller));
            ((FinalCategory)category).addProduct(product);
            Market.getInstance().getAllProducts().add(product);
        }

        ProductSellInfo productSellInfo = product.getSellerInfoForProductByUsername(seller.getPersonalInfo().getUsername());
        if (fieldsAndValues.containsKey("price")) {
            productSellInfo.setPrice(Integer.parseInt(fieldsAndValues.get("price")));
        } if (fieldsAndValues.containsKey("stock")) {
            productSellInfo.setStock(Integer.parseInt(fieldsAndValues.get("stock")));
        } if (fieldsAndValues.containsKey("offId('-' for none)")) {
            Off off = Market.getInstance().getOffById(fieldsAndValues.get("offId"));
            if (off != null) {
                productSellInfo.setOff(off);
            }
        }

        seller.getAvailableProducts().put(product, productSellInfo);
    }

    @Override
    public String getType() {
        return "add product";
    }

}
