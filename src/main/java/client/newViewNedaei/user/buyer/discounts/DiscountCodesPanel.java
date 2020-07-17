package client.newViewNedaei.user.buyer.discounts;

import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import server.controller.CodedDiscountController;
import server.controller.userControllers.UserController;
import server.model.CodedDiscount;
import server.model.user.Buyer;

import java.util.ArrayList;

public class DiscountCodesPanel extends Panel {
    public ListView<Hyperlink> discounts;

    public static String getFxmlFilePath() {
        return "/DiscountCodesPanel.fxml";
    }

    @FXML
    public void initialize() {
        ArrayList<CodedDiscount> codes = ((Buyer) UserController.getActiveUser()).getListOfCodedDiscounts();
        for (CodedDiscount code : codes) {
            Hyperlink hyperlink = new Hyperlink(code.getCode() + " -> " + code.getPercentage() + "%");
            hyperlink.setOnAction(event -> {
                CodedDiscountController.getInstance().setCurrentDiscount(code);
                MenuController.getInstance().goToPanel(DisplayDiscountCodePanel.getFxmlFilePath());
            });
            discounts.getItems().add(hyperlink);
        }
    }
}
