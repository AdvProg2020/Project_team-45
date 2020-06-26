package newViewHatami;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.product.Product;
import model.product.ProductSellInfo;
import newViewNedaei.Panel;

public class ViewProductForAdmin extends Panel {
    public Label nameLabel;
    public Label companyLabel;
    public Label categoryLabel;
    public ImageView imageView;
    public Label priceLabel;
    public Label stockLabel;

    private static ProductSellInfo showingProductInfo;

    public static void setShowingProductInfo(ProductSellInfo showingProductInfo) {
        ViewProductForAdmin.showingProductInfo = showingProductInfo;
    }

    public static String getFxmlFilePath() {
        return "/ViewProductForAdmin.fxml";
    }

    @FXML
    public void initialize() {
        Product product = showingProductInfo.getProduct();
        nameLabel.setText(product.getName());
        companyLabel.setText(product.getCompany().getName());
        categoryLabel.setText(product.getCategory().getName());
        try {
            imageView.setImage(new Image(product.getImageAddress()));
        } catch (Exception e) {
            System.err.println("couldn't open image");
            imageView.setImage(new Image("/poker.png"));
        }
        priceLabel.setText(String.valueOf(showingProductInfo.getPrice()));
        stockLabel.setText(String.valueOf(showingProductInfo.getStock()));
    }
}
