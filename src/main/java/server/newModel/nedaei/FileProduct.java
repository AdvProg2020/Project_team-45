package server.newModel.nedaei;

import server.model.category.FinalCategory;
import server.model.product.Product;

public class FileProduct extends Product {

    public FileProduct(String name, FinalCategory category, String description, String imageAddress, String videoAddress) {
        super(name, category, description, imageAddress, videoAddress);
    }

    public FileProduct(String productId) {
        super(productId);
    }
}
