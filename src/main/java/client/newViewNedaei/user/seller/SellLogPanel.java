package client.newViewNedaei.user.seller;

import client.controller.userControllers.SellerController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.HashMap;

// nedaei: turned to new format successfully!
public class SellLogPanel extends Panel {
    public Label date;
    public Label id;
    public Label buyer;
    public Label discount;
    public Label finalPrice;
    public Label address;
    public Label phone;
    public Label product;

    public static String getFxmlFilePath() {
        return "/SellLogPanel.fxml";
    }

    @FXML
    public void initialize() {
        HashMap<String, String> sellLog = SellerController.getInstance().getCurrentSellLog();
        date.setText(sellLog.get("date"));
        id.setText(sellLog.get("id"));
        buyer.setText(sellLog.get("buyerUsername"));
        discount.setText(sellLog.get("discountPercentage") + "%");
        finalPrice.setText(sellLog.get("finalPrice"));
        address.setText(sellLog.get("address"));
        phone.setText(sellLog.get("phoneNumber"));
        product.setText(sellLog.get("soldProductId"));
    }
}
