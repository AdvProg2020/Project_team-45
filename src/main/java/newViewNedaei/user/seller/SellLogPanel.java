package newViewNedaei.user.seller;

import controller.userControllers.SellerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.log.SellLog;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;

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

    @Override
    public void goBack() {
        MenuController.getInstance().goToPanel(SalesHistoryPanel.getFxmlFilePath());
    }
}
