package server.controller;

import client.network.MethodStringer;
import server.controller.managers.Deleter;
import server.controller.userControllers.BuyerController;
import server.controller.userControllers.UserController;
import server.model.Comment;
import server.model.Market;
import server.model.product.Product;
import server.model.product.ProductSellInfo;
import server.model.request.CommentRequest;
import server.model.user.Buyer;
import server.model.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductController implements Deleter {

    // TODO : bagheri check all
    private static final ProductController instance = new ProductController();
    private final Market market;
    private Product activeProduct; // hotam
    private ProductSellInfo activeProductSellInfo; // hotam

    private ProductController() {
        this.market = Market.getInstance();
    }

    public static ProductController getInstance() {
        return instance;
    }

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

    public LinkedHashMap<String, String> getProductDigestInformation() {
        return activeProduct.getDigestInformation();
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
            if (productSellInfo.getStock() > 0 && !productSellInfo.isInAuction()) {
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

    public void setActiveProductById(String productId) {
        activeProduct = market.getProductById(productId);
    }

    public HashMap<String, String> getActiveSellInfo() {
        return activeProductSellInfo.getInformation();
    }

    public void setActiveProductBYProductIdForOff(String productId, String sellInfoId) {
        activeProduct = market.getProductById(productId);
        activeProductSellInfo = market.getProductSellInfoById(sellInfoId);
        activeProduct.increaseSeen();
    }

    public ArrayList<HashMap<String, String>> getActiveProductSimilarProducts() {
        ArrayList<HashMap<String, String>> similarProductsInfo = new ArrayList<>();
        for (Product product : activeProduct.getCategory().getProductsList()) {
            similarProductsInfo.add(product.getProductInfoForProductsList());
        }
        return similarProductsInfo;
    }

    public List<String> getAllProductsNamesList() {
        return market.getAllProducts().stream().map(product -> product.getId() + ":" + product.getName()).collect(Collectors.toList());
    }

    public HashMap<String, String> getProductAndSellInfo(String sellInfoId) {
        HashMap<String, String> information = new HashMap<>();
        ProductSellInfo sellInfo = Market.getInstance().getProductSellInfoById(sellInfoId);
        Product product = sellInfo.getProduct();
        information.put("name", product.getName());
        information.put("companyName", product.getCompany().getName());
        information.put("categoryName", product.getCategory().getName());
        information.put("imageAddress", product.getImageAddress());
        information.put("price", String.valueOf(sellInfo.getFinalPrice()));
        information.put("stock", String.valueOf(sellInfo.getStock()));
        return information;
    }

    public String getActiveProductImageAddress() {
        return activeProduct.getImageAddress();
    }

    public String getActiveProductVideoAddress() {
        return activeProduct.getVideoAddress();
    }
}
