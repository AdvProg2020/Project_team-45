package controller;

import controller.managers.Deleter;
import model.Market;
import model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductController implements Deleter {
    private Market market;
    private MainController mainController;
    private Product activeProduct;
    private static final ProductController instance = new ProductController();


    public ProductController(MainController mainController) {
        this.mainController = mainController;
        this.market = Market.getInstance();
    }

    private ProductController() {
    }

    public static ProductController getInstance() {
        return instance;
    }

    //    public Product createProduct() {
//        return null;
//    }

    public void setFieldOfProduct(Product product, String field, String value) {}

    public void deleteProductById(String productId) {
    }

    public void getProductBuyers(Product product) {
    }

    public Product getProductByProductId(String productId) {
        return null;
    }

    public HashMap<String, String> getProductDigestInformation() {
        return null;
    }

    public HashMap<String, String> getProductAttributes() {
        return null;
    }

    public HashMap<String, String> getProductAttributesById(String productId) {
        return null;
    }

    public void addActiveProductToCart() {

    }

    public boolean selectSellerForActiveProduct(String sellerUsername) {

        return false;
    }

    public int getAverageScore() {
        return 0;
    }

    public ArrayList<String> getProductComments() {
        return null;
    }

    public void addComment(String title, String content) {

    }

    public boolean isWithInACategory(String productId) {
        return false;
    }

    public void deleteItemById(String Id) {
        Product product = market.getProductById(Id);
    }

    public String getAllInListAsString() {
        ArrayList<Product> allProducts = market.getAllProducts();
        StringBuilder output = new StringBuilder();
        output.append("product Id,product name\n");
        for (Product product : allProducts) {
            String productInfo = product.getProductId() + "," + product.getName() + "\n";
            output.append(productInfo);
        }
        return output.toString();
    }

    public String printDetailedById(String Id) {
        // not involved yet //
        return null;
    }
}
