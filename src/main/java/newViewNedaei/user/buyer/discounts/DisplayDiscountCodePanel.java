package newViewNedaei.user.buyer.discounts;

import controller.CodedDiscountController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.CodedDiscount;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;

import java.text.SimpleDateFormat;

public class DisplayDiscountCodePanel extends Panel {
    public Label code;
    public Label start;
    public Label end;
    public Label percentage;
    public Label owner;

    public static String getFxmlFilePath() {
        return "/DisplayDiscountCodePanel.fxml";
    }

    @Override
    public void goBack() {
        MenuController.getInstance().goToPanel(DiscountCodesPanel.getFxmlFilePath());
    }

    @FXML
    public void initialize() {
        CodedDiscount discount = CodedDiscountController.getInstance().getCurrentDiscount();
        code.setText(discount.getCode());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        start.setText(simpleDateFormat.format(discount.getStartDate()));
        end.setText(simpleDateFormat.format(discount.getEndDate()));
        percentage.setText("" + discount.getPercentage());
        owner.setText(discount.getOwner().getUsername());
    }
}