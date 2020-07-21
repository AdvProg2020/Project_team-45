package client.newViewHatami;

import client.controller.ProductController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class ViewProductForAdmin extends Panel {
    public Label nameLabel;
    public Label companyLabel;
    public Label categoryLabel;
    public ImageView imageView;
    public Label priceLabel;
    public Label stockLabel;

    private static String showingProductInfoId;

    public static void setShowingProductInfo(String showingProductInfoId) {
        ViewProductForAdmin.showingProductInfoId = showingProductInfoId;
    }

    public static String getFxmlFilePath() {
        return "/ViewProductForAdmin.fxml";
    }

    @FXML
    public void initialize() {
        HashMap<String, String> information = ProductController.getInstance().getProductAndSellInfo(showingProductInfoId);
        nameLabel.setText(information.get("name"));
        companyLabel.setText(information.get("companyName"));
        categoryLabel.setText(information.get("categoryName"));
        try {
            imageView.setImage(new Image(information.get("imageAddress")));
        } catch (Exception e) {
            System.err.println("couldn't open image");
            imageView.setImage(new Image("/poker.png"));
        }
        priceLabel.setText(information.get("price"));
        stockLabel.setText(information.get("stock"));
    }
}
