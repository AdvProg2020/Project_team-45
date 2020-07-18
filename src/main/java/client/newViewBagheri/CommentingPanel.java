package client.newViewBagheri;

import client.newViewNedaei.Panel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import client.controller.ProductController;

public class CommentingPanel extends Panel {
    ProductController productController = ProductController.getInstance();
    public TextField title;
    public TextArea content;

    public static String getFxmlFilePath() {
        return "/CommentingPanel.fxml";
    }

    public void addComment() {
        productController.addComment(title.getText(), content.getText());
        goBack();
    }
}
