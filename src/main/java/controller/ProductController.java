package controller;

import controller.managers.Deleter;
import controller.userControllers.UserController;
import model.Comment;
import model.Market;
import model.Product;
import model.ProductSellInfo;
import model.category.Category;
import model.request.CommentRequest;
import model.user.Buyer;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductController implements Deleter {
    private static final ProductController instance = new ProductController();
    private final Market market;
    private Product activeProduct;

    private ProductController() {
        this.market = Market.getInstance();
    }

    public static ProductController getInstance() {
        return instance;
    }

    //    public Product createProduct() {
//        return null;
//    }

    public boolean setActiveProductBYProductId(String productId) {
        ArrayList<Product> productsList = CategoryController.getInstance().getActiveCategoryProductsList();
        for (Product product : productsList) {
            if (product.getProductId().equals(productId)) {
                activeProduct = product;
                activeProduct.increaseSeen();
                return true;
            }
        }
        return false;
    }

    public void setFieldOfProduct(Product product, String field, String value) {
    }

    public void getProductBuyers(Product product) {
    }

    public Product getProductByProductId(String productId) {
        return null;
    }

    public HashMap<String, String> getProductDigestInformation() {
        return activeProduct.getDigestInformation();
    }

    public HashMap<String, String> getProductAttributes() {
        return activeProduct.getAttributes();
    }

    public HashMap<String, String> getProductAttributesById(String productId) {
        Product product = CategoryController.getInstance().getActiveCategoryProduct(productId);
        if (product == null)
            return null;
        return product.getAttributes();
    }

    public void addActiveProductToCart() {
        // TODO bagheri
    }

    public boolean selectSellerForActiveProduct(String sellerUsername) {
        // TODO bagheri
        return false;
    }

    public float getAverageScore() {
        return activeProduct.getAverageScore();
    }

    public ArrayList<String> getProductComments() {
        ArrayList<String> productComments = new ArrayList<>();
        for (Comment comment : activeProduct.getApprovedComments()) {
            productComments.add(comment.toString());
        }
        return productComments;
    }

    public void addComment(String title, String content) {
        User activeUser = UserController.getActiveUser();
        boolean didUserBuy = false;
        if (activeUser.getRole().equals("Buyer") && ((Buyer) activeUser).didBuyProduct(activeProduct.getProductId())) {
            didUserBuy = true;
        }
        Comment newComment = new Comment(activeUser, activeProduct, title, content, didUserBuy);
        market.addRequest(new CommentRequest(newComment));
    }

    public boolean deleteItemById(String Id) {
        Product product = getItemById(Id);
        if (product == null)
            return false;
        removeProduct(getItemById(Id));
        return true;
    }

    public void removeProduct(Product product) {
        removeProductFromSellersList(product);
        removeProductFromCategory(product);
        market.removeProductFromAllProductsList(product);
    }

    private void removeProductFromCategory(Product product) {
        product.getCategory().removeProduct(product);
    }

    private void removeProductFromSellersList(Product product) {
        for (ProductSellInfo sellInfo : product.getSellInfosList()) {
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

    public String getDetailStringById(String Id) {
        // not involved yet //
        return null;
    }

    @Override
    public Product getItemById(String Id) {
        return market.getProductById(Id);
    }
}
