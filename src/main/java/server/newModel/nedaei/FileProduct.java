package server.newModel.nedaei;

import server.model.category.FinalCategory;
import server.model.product.Product;

public class FileProduct extends Product {
    private String pathInClient;
    private String sellerUserName;

    public FileProduct(String name, FinalCategory category, String description, String imageAddress, String videoAddress, String sellerUsername, String pathInClient) {
        super(name, category, description, imageAddress, videoAddress);
        this.pathInClient = pathInClient;
        this.sellerUserName = sellerUsername;
    }

    public FileProduct(String productId) {
        super(productId);
    }

    public String getPathInClient() {
        return pathInClient;
    }

    public String getSellerUserName() {
        return sellerUserName;
    }
}
