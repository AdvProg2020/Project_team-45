package client.newViewNedaei.user.seller.off;

import client.newViewHatami.Validator;
import client.newViewHatami.ValidatorField;
import client.newViewNedaei.Panel;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import server.controller.OffController;
import server.controller.userControllers.SellerController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class EditOffPanel extends Panel {

    public TextField startDate;
    public TextField endDate;
    public ValidatorField discount;
    public Label error;

    public static String getFxmlFilePath() {
        return "/EditOffPanel.fxml";
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void sendRequest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        if (startDate.getText().equals("")) {
            fieldsAndValues.put("startTime", "");
        } else {
            try {
                simpleDateFormat.parse(startDate.getText());
                fieldsAndValues.put("startDate", startDate.getText());
            } catch (ParseException parseException) {
                error.setText("invalid start date format");
                return;
            }
        }
        if (endDate.getText().equals("")) {
            fieldsAndValues.put("endTime", "");
        } else {
            try {
                simpleDateFormat.parse(endDate.getText());
                fieldsAndValues.put("endDate", startDate.getText());
            } catch (ParseException parseException) {
                error.setText("invalid end date format");
                return;
            }
        }
        if (!discount.getText().equals("") && !discount.validate()) {
            error.setText("invalid discount amount format");
            return;
        }
        fieldsAndValues.put("discountAmount", discount.getText());
        SellerController.getInstance().createOffEditionRequest(OffController.getInstance().getCurrentOff().getId(),
                fieldsAndValues);
        error.setText("");
    }
}
