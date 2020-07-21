package client.newViewNedaei.user.buyer.discounts;

import client.controller.CodedDiscountController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.HashMap;

// nedaei: turned to new format successfully!
public class DisplayDiscountCodePanel extends Panel {
    public Label code;
    public Label start;
    public Label end;
    public Label percentage;
    public Label owner;

    public static String getFxmlFilePath() {
        return "/DisplayDiscountCodePanel.fxml";
    }

    @FXML
    public void initialize() {
        HashMap<String, String> discount = CodedDiscountController.getInstance().getCurrentDiscount();
        code.setText(discount.get("code"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        start.setText(simpleDateFormat.format(discount.get("startDate")));
        end.setText(simpleDateFormat.format(discount.get("endDate")));
        percentage.setText(discount.get("percentage") + "%");
        owner.setText(discount.get("ownerUsername"));
    }
}
