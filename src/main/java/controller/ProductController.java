package controller;

import controller.managers.Deleter;
import model.Market;
import model.Comment;
import model.Product;
import model.ProductSellInfo;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductController implements Deleter {
    private static final ProductController instance = new ProductController();
    private Market market;
    private MainController mainController;
    private Product activeProduct;


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

    public void setFieldOfProduct(Product product, String field, String value) {
    }

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
        User user = null;
        boolean didUserBuy = false;
        Comment newComment = new Comment(user, activeProduct, title, content, didUserBuy);
        activeProduct.addComment(newComment);
    }

    public boolean isWithInACategory(String productId) {
        return false;
    }

    public void deleteItemById(String Id) throws Exception {
        Product product = market.getProductById(Id);
        if (product == null)
            throw new Exception("wrong Id");
        removeProductFromSellersList(product);
        removeProductFromCategory(product);
        market.removeProductFromAllProductsList(product);
    }

    private void removeProductFromCategory(Product product) {
        product.getCategory().removeProduct(product);
    }

    private void removeProductFromSellersList(Product product) {
        for (ProductSellInfo sellInfo : product.getSellersList()) {
            sellInfo.removeProduct();
        }
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
