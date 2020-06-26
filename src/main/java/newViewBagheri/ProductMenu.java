package newViewBagheri;

import controller.ProductController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import newViewNedaei.MenuController;

import java.net.URL;
import java.util.*;

public class ProductMenu implements Initializable {
    private final ProductController productController = ProductController.getInstance();
    private final MenuController menuController = MenuController.getInstance();
    public Label productNameLabel;
    public Label brandNameLabel;
    public Label categoryNameLabel;
    public Text descriptionText;
    public Label scoreLabel;
    public Button ScoringButton;
    public ImageView productImageView;
    public Label sellerNameLabel;
    public Label originalPriceLabel;
    public Label discountPercent;
    public Label finalPriceLabel;
    public GridPane sellersListPain;
    public GridPane featuresListPain;
    public VBox commentsList;

    public static String getFxmlFilePath() {
        return "/ProductMenu.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addProductInformation();
        addImage();
        addDefaultSellerInfo();
        addSellersList();
        addFeaturesList();
        addCommentsList();
        addSimilarProductsList();
    }

    private void addProductInformation() {
        HashMap<String, String> productInformation = productController.getProductDigestInformation();
        productNameLabel.setText(productInformation.get("name"));
        brandNameLabel.setText("brand:" + productInformation.get("companyName"));
        categoryNameLabel.setText("category:" + productInformation.get("category"));
        descriptionText.setText(productInformation.get("description"));
        scoreLabel.setText(productInformation.get("averageScore"));
    }

    private void addImage() {
//        productImageView.setImage(new Image(convertPhotoPath(productController.getActiveProduct().getImageAddress())));
    }

    private void addDefaultSellerInfo() {
        HashMap<String, String> defaultSellerInfo = productController.getActiveSellInfo();
        sellerNameLabel.setText("seller: " + defaultSellerInfo.get("sellerUsername"));
        originalPriceLabel.setText(defaultSellerInfo.get("originalPrice"));
        if (!defaultSellerInfo.get("discountPercent").equals("0")) {
//            originalPriceLabel.;
            discountPercent.setText(defaultSellerInfo.get("discountPercent") + "%");
            discountPercent.setVisible(true);
            finalPriceLabel.setText(defaultSellerInfo.get("finalPrice"));
            finalPriceLabel.setVisible(true);
        }
    }

    public void addDefaultSellerToCart() {
        productController.addActiveProductToCart();
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
        LinkedHashMap<String, String> productAttributes = productController.getActiveProductFeatures();
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

    public void goToCommentingPanel() {
        menuController.goToPanel(CommentingPanel.getFxmlFilePath());
    }

    private void addSimilarProductsList() {

    }

    private String convertPhotoPath(String path) {
        return "/photos/" + path.substring(path.lastIndexOf("\\")+1);
    }
}
