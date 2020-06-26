package controller;

import controller.managers.Deleter;
import controller.userControllers.BuyerController;
import controller.userControllers.UserController;
import model.Comment;
import model.Market;
import model.product.Product;
import model.product.ProductSellInfo;
import model.request.CommentRequest;
import model.user.Buyer;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ProductController implements Deleter {
    private static final ProductController instance = new ProductController();
    private final Market market;
    private Product activeProduct;
    private ProductSellInfo activeProductSellInfo;

    private ProductController() {
        this.market = Market.getInstance();
    }

    public static ProductController getInstance() {
        return instance;
    }

    //    public Product createProduct() {
//        return null;
//    }

    public boolean setActiveProductBYProductIdForCategory(String productId) {
        ArrayList<Product> productsList = CategoryController.getInstance().getActiveCategoryProductsList();
        return setActiveProductByListAndId(productsList, productId);
    }

    public boolean setActiveProductBYProductIdForCart(String productId) {
        ArrayList<Product> productsList = BuyerController.getInstance().getBuyer().getCart().getCartProducts();
        return setActiveProductByListAndId(productsList, productId);
    }

    private boolean setActiveProductByListAndId(ArrayList<Product> productsList, String productId) {
        for (Product product : productsList) {
            if (product.getId().equals(productId)) {
                activeProduct = product;
                activeProduct.increaseSeen();
                activeProductSellInfo = activeProduct.getDefaultSellInfo();
                return true;
            }
        }
        return false;
    }

    public void getProductBuyers(Product product) {
    }

    public LinkedHashMap<String, String> getProductDigestInformation() {
        return activeProduct.getDigestInformation();
    }

    public LinkedHashMap<String, String> getProductAttributes() {
        return activeProduct.getAttributes();
    }

    public LinkedHashMap<String, String> getProductAttributesById(String productId) {
        Product product = CategoryController.getInstance().getActiveCategoryProduct(productId);
        if (product == null)
            return null;
        return product.getAttributes();
    }

    public void addActiveProductToCart() {
        CartController.getInstance().addProductToCart(activeProduct, activeProductSellInfo);
    }

    public boolean selectSellerForActiveProduct(String sellerUsername) {
        ProductSellInfo sellInfo = activeProduct.getSellerInfoForProductByUsername(sellerUsername);
        if (sellInfo == null)
            return false;
        activeProductSellInfo = sellInfo;
        return true;
    }

    public float getAverageScore() {
        return activeProduct.getAverageScore();
    }

    public ArrayList<String> getProductComments() {
        ArrayList<String> productComments = new ArrayList<>();
        for (Comment comment : activeProduct.getApprovedComments()) {
            productComments.add(comment.showComment());
        }
        return productComments;
    }

    public void addComment(String title, String content) {
        User activeUser = UserController.getActiveUser();
        boolean didUserBuy = false;
        if (activeUser.getRole().equals("Buyer") && ((Buyer) activeUser).didBuyProduct(activeProduct.getId())) {
            didUserBuy = true;
        }
        Comment newComment = new Comment(activeUser, activeProduct, title, content, didUserBuy);
        activeProduct.addComment(newComment);
        market.addRequest(new CommentRequest(newComment));
    }

    public boolean deleteItemById(String Id) {
        Product product = getItemById(Id);
        if (product == null)
            return false;
        removeProduct(getItemById(Id));
        return true;
    }

    @Override
    public boolean justRequests() {
        return false;
    }

    public void removeProduct(Product product) {
        removeProductFromSellersList(product);
        removeProductFromCategory(product);
        market.removeProductFromAllList(product);
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
            String productInfo = product.getId() + "," + product.getName() + "\n";
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

    public ProductSellInfo getActiveProductSellInfo() {
        return activeProductSellInfo;
    }

    public void setActiveProductSellInfo(ProductSellInfo activeProductSellInfo) {
        this.activeProductSellInfo = activeProductSellInfo;
    }

    public Product getActiveProduct() {
        return activeProduct;
    }

    public void setActiveProduct(Product activeProduct) {
        this.activeProduct = activeProduct;
    }

    //bagheri
    public ArrayList<HashMap<String, String>> getActiveProductCommentsList() {
        ArrayList<HashMap<String, String>> productComments = new ArrayList<>();
        for (Comment comment : activeProduct.getApprovedComments()) {
            HashMap<String, String> commentFields = new HashMap<>();
            commentFields.put("title", comment.getTitle());
            commentFields.put("content", comment.getContent());
            productComments.add(commentFields);
        }
        return productComments;
    }

    public ArrayList<HashMap<String, String>> getActiveProductSellInfos() {
        ArrayList<HashMap<String, String>> productSellInfos = new ArrayList<>();
        for (ProductSellInfo productSellInfo : activeProduct.getSellInfosList()) {
            if (productSellInfo.getStock() > 0) {
                HashMap<String, String> sellInfo = new HashMap<>();
                sellInfo.put("id", productSellInfo.getId());
                sellInfo.put("sellerUsername", productSellInfo.getSeller().getUsername());
                sellInfo.put("price", "" + productSellInfo.getPrice());
                sellInfo.put("finalPrice", "" + productSellInfo.getFinalPrice());
                productSellInfos.add(sellInfo);
            }
        }
        return productSellInfos;
    }

    public void addActiveProductToCart(String productSellInfoId) {
        CartController.getInstance().addProductToCart(activeProduct, market.getProductSellInfoById(productSellInfoId));
    }

    public LinkedHashMap<String, String> getActiveProductFeatures() {
        return activeProduct.getCategoryFeatures();
    }

    public String getActiveProductImageAddress() {
        return activeProduct.getImageAddress();
    }
    //bahgeri
}
