package controller;

import model.Comment;
import model.Product;

import java.util.ArrayList;

public class ProductController {
    private MainController mainController;

    public ProductController(MainController mainController) {
        this.mainController = mainController;
    }

    public Product createProduct() {
        return null;
    }

    public void setFieldOfProduct(Product product, String field, String value) {}

    public void deleteProductById(String productId) {
    }

    public void getProductBuyers(Product product) {
    }

    public Product getProductByProductId(String productId) {
        return null;
    }

    public ArrayList<Comment> getProductComments(Product product) {
        return null;
    }
}
