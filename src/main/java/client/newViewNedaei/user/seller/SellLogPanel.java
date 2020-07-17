package client.newViewNedaei.user.seller;

import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import server.controller.userControllers.SellerController;
import server.model.log.SellLog;

import java.text.SimpleDateFormat;

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
        SellLog sellLog = SellerController.getInstance().getCurrentSellLog();
        date.setText(new SimpleDateFormat("yyyy/MM/dd").format(sellLog.getMainLog().getDate()));
        id.setText(sellLog.getMainLog().getId());
        buyer.setText(sellLog.getMainLog().getBuyerUsername());
        discount.setText(sellLog.getMainLog().getAppliedDiscountPercentage() + "%");
        finalPrice.setText("" + sellLog.getMainLog().getFinalPrice());
        address.setText(sellLog.getMainLog().getAddress());
        phone.setText(sellLog.getMainLog().getPhoneNumber());
        product.setText(sellLog.getSoldProducts().get(0).getId());
    }

}
