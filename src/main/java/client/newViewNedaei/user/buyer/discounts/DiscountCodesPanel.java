package client.newViewNedaei.user.buyer.discounts;

import client.controller.CodedDiscountController;
import client.controller.userControllers.BuyerController;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import com.google.gson.internal.LinkedTreeMap;
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
        ArrayList codes = BuyerController.getInstance().getListOfCodedDiscounts();
        for (Object code : codes) {
            Hyperlink hyperlink = new Hyperlink(new HashMap<String, String>((LinkedTreeMap)code).get("code") + " -> " + new HashMap<String, String>((LinkedTreeMap)code).get("percentage") + "%");
            hyperlink.setOnAction(event -> {
                CodedDiscountController.getInstance().setCurrentDiscountById(new HashMap<String, String>((LinkedTreeMap)code).get("id"));
                MenuController.getInstance().goToPanel(DisplayDiscountCodePanel.getFxmlFilePath());
            });
            discounts.getItems().add(hyperlink);
        }
    }
}
