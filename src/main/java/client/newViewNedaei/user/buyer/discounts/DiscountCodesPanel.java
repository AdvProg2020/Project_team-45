package client.newViewNedaei.user.buyer.discounts;

import client.controller.CodedDiscountController;
import client.controller.userControllers.BuyerController;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.HashMap;

// nedaei: turned to new format successfully!
public class DiscountCodesPanel extends Panel {
    public ListView<Hyperlink> discounts;

    public static String getFxmlFilePath() {
        return "/DiscountCodesPanel.fxml";
    }

    @FXML
    public void initialize() {
        ArrayList<HashMap<String, String>> codes = BuyerController.getInstance().getListOfCodedDiscounts();
        for (HashMap<String, String> code : codes) {
            Hyperlink hyperlink = new Hyperlink(code.get("code") + " -> " + code.get("percentage") + "%");
            hyperlink.setOnAction(event -> {
                CodedDiscountController.getInstance().setCurrentDiscountById(code.get("id"));
                MenuController.getInstance().goToPanel(DisplayDiscountCodePanel.getFxmlFilePath());
            });
            discounts.getItems().add(hyperlink);
        }
    }
}
