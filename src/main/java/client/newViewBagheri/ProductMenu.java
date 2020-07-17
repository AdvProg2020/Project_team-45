package client.newViewBagheri;

import client.newViewNedaei.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import server.controller.ProductController;
import server.controller.userControllers.UserController;

import java.net.URL;
import java.nio.file.Paths;
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
    public Label errorLabelForAddComment;
    public MediaView mediaView;
    public GridPane SimilarProductsListPain;

    public static String getFxmlFilePath() {
        return "/ProductMenu.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addProductInformation();
        addImage();
        addVideo();
        addDefaultSellerInfo();
        addSellersList();
        addFeaturesList();
        addCommentsList();
        addSimilarProductsList();
    }

    private void addProductInformation() {
        HashMap<String, String> productInformation = productController.getProductDigestInformation();
        productNameLabel.setText(productInformation.get("name"));
        brandNameLabel.setText("brand: " + productInformation.get("companyName"));
        categoryNameLabel.setText("category: " + productInformation.get("category"));
        descriptionText.setText(productInformation.get("description"));
        scoreLabel.setText(productInformation.get("averageScore"));
    }

    private void addImage() {
//        productImageView.setImage(new Image(convertPhotoPath(productController.getActiveProduct().getImageAddress())));
    }

    private void addVideo() {
        String path = productController.getActiveProduct().getVideoAddress();
        if (path == null)
            return;
        Media media = new Media(Paths.get(path).toUri().toString());
        mediaView.setMediaPlayer(new MediaPlayer(media));
        mediaView.getMediaPlayer().setOnEndOfMedia(mediaView.getMediaPlayer().getOnRepeat());
    }

    private void addDefaultSellerInfo() {
        HashMap<String, String> defaultSellerInfo = productController.getActiveSellInfo();
        sellerNameLabel.setText("seller: " + defaultSellerInfo.get("sellerUsername"));
        originalPriceLabel.setText("price: " + defaultSellerInfo.get("originalPrice"));
        if (!defaultSellerInfo.get("discountPercent").equals("0")) {
//            originalPriceLabel.;
            discountPercent.setText("discountPercent: " + defaultSellerInfo.get("discountPercent") + "%");
            discountPercent.setVisible(true);
            finalPriceLabel.setText("finalPrice: " + defaultSellerInfo.get("finalPrice"));
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

    public void addNewComment() {
        if (UserController.isLoggedIn())
            menuController.goToPanel(CommentingPanel.getFxmlFilePath());
        else
            errorLabelForAddComment.setVisible(true);
    }

    private void addSimilarProductsList() {
        int i = 0;
        for (HashMap<String, String> similarProductInfo : productController.getActiveProductSimilarProducts()) {
            SimilarProductsListPain.add(createProductInfoVBox(similarProductInfo), i % 5, i / 5);
            i++;
        }
    }

    private VBox createProductInfoVBox(HashMap<String, String> productInfo) {
        VBox productInfoVBox = new VBox();
        productInfoVBox.setBorder(new Border(new BorderStroke(Color.BROWN,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        int sizePrefWidth = 195;
        productInfoVBox.setPrefWidth(sizePrefWidth);
        productInfoVBox.setPrefHeight(350.0);
        ImageView productImageView = new ImageView(new Image(productInfo.get("imageAddress")));
        productImageView.setOnMouseClicked(e -> goToProduct(productInfo.get("id")));
        productImageView.setPreserveRatio(true);
        productImageView.setFitWidth(190.0);
        productImageView.setFitHeight(250.0);
        BorderPane imagePane = new BorderPane(productImageView);
        BorderPane.setAlignment(productImageView, Pos.CENTER);
        imagePane.setPrefWidth(sizePrefWidth);
        imagePane.setPrefHeight(250.0);
        // TODO: productImageView.setFitWidth();
        // TODO: add pane and centering image
        int labelSize = 30;
        Label productName = new Label(productInfo.get("name"));
        productName.setOnMouseClicked(e -> goToProduct(productInfo.get("id")));
        setLabelStyle(productName, sizePrefWidth, labelSize);
        Label productScore = new Label("score: " + productInfo.get("averageScore") + " out 0f 5");
        setLabelStyle(productScore, sizePrefWidth, labelSize);
        String productPrice = productInfo.get("price");
        Label productPriceLabel = new Label();
        setLabelStyle(productPriceLabel, sizePrefWidth, labelSize);
        if (productPrice.equals("unavailable")) {
            productPriceLabel.setText(productPrice);
        } else {
            productPriceLabel.setText("price: " + productPrice);
        }
        productInfoVBox.getChildren().addAll(imagePane, productName, productScore, productPriceLabel);
        return productInfoVBox;
    }



    public void videoClicked(MouseEvent mouseEvent) {
        mediaView.getMediaPlayer().play();
    }

    public void puaseVideo(ActionEvent actionEvent) {
        mediaView.getMediaPlayer().pause();
    }

    private void setLabelStyle(Label label, int prefWidth, int prefHeight) {
        label.setPrefWidth(prefWidth);
        label.setPrefHeight(prefHeight);
        label.setAlignment(Pos.CENTER);
    }

    private void goToProduct(String productId) {
        productController.setActiveProductBYProductIdForCategory(productId);
        menuController.goToMenu(ProductMenu.getFxmlFilePath());
    }
}
