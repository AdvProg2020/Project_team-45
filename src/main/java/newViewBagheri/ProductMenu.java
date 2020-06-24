package newViewBagheri;

import controller.ProductController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class ProductMenu implements Initializable {
    private final ProductController productController = ProductController.getInstance();
    public Label productNameLabel;
    public Label brandNameLabel;
    public Label categoryNameLabel;
    public Text descriptionText;
    public Label scoreLabel;
    public Button ScoringButton;
    public GridPane sellersListPain;
    public GridPane featuresListPain;
    public VBox commentsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addProductInformation();
        addSellersList();
        addFeaturesList();
        addCommentsList();
        addSimilarProductsList();
    }

    private void addProductInformation() {
        HashMap<String, String> productInformation = productController.getProductDigestInformation();
        productNameLabel.setText(productInformation.get("name"));
        brandNameLabel.setText(productInformation.get("companyName"));
        categoryNameLabel.setText(productInformation.get("category"));
        descriptionText.setText(productInformation.get("description"));
        scoreLabel.setText(productInformation.get("averageScore"));
    }

    private void addSellersList() {
        ArrayList<HashMap<String, String>> sellInfos = productController.getActiveProductSellInfos();
        if (sellInfos.isEmpty()) {
            // TODO
            return;
        }
        int i = 0;
        for (HashMap<String, String> sellInfo : sellInfos) {
            addSellerToSellersList(sellInfo, i);
            i++;
        }
    }

    private void addSellerToSellersList(HashMap<String, String> sellInfo, int rowIndex) {
        Label sellerUsername = new Label(sellInfo.get("sellerUsername"));
        Label price = new Label(sellInfo.get("finalPrice"));
        Button addToCart = new Button("Add To Cart");
        addToCart.setOnAction(e -> productController.addActiveProductToCart(sellInfo.get("id")));
        sellersListPain.add(sellerUsername, 0, rowIndex);
        sellersListPain.add(price, 1, rowIndex);
        sellersListPain.add(addToCart, 2, rowIndex);
    }

    private void addFeaturesList() {
        LinkedHashMap<String, String> productAttributes = productController.getProductAttributes();
        int i = 0;
        for (Map.Entry<String, String> attribute : productAttributes.entrySet()) {
            if (attribute.getValue() != null) {
                Label feature = new Label(attribute.getKey());
                Label value = new Label(attribute.getValue());
                featuresListPain.add(feature, 0, i);
                featuresListPain.add(value, 1, i);
                i++;
            }
        }
    }

    private void addCommentsList() {
        for (HashMap<String, String> commentFields : productController.getActiveProductCommentsList()) {
            BorderPane commentPane = new BorderPane();
            Text titleText = new Text(commentFields.get("title"));
            commentPane.setTop(titleText);
            Text contentText = new Text(commentFields.get("content"));
            commentPane.setCenter(contentText);
            commentsList.getChildren().add(commentPane);
        }
    }

    private void addSimilarProductsList() {

    }
}
