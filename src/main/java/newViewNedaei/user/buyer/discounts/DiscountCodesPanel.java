package newViewNedaei.user.buyer.discounts;

import controller.CodedDiscountController;
import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import model.CodedDiscount;
import model.user.Buyer;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;

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
