package controller;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductController implements Deleter {
    private MainController mainController;
    private Product activeProduct;
    private static ProductController instance = new ProductController();


    public ProductController(MainController mainController) {
        this.mainController = mainController;
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

    public HashMap<String, String> getProductAttributes() {
        return null;
    }

    public HashMap<String, String> getProductAttributesById(String productId) {
        return null;
    }

    public int getAverageScore() {
        return 0;
    }

    public ArrayList<String> getProductComments() {
        return null;
    }

    public void addComment(String title, String content) {

    }

    public void deleteItemById(String Id) {
        // TODO : hatami
    }

    public String printAllInList() {
        // TODO : hatami
        return null;
    }

    public String printDetailedById(String Id) {
        // TODO : hatami
        return null;
    }
}
