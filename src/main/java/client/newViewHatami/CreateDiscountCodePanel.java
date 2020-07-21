package client.newViewHatami;

import client.controller.CodedDiscountController;
import client.controller.userControllers.AllUsersController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class CreateDiscountCodePanel extends Panel {

    public ValidatorField codeField;
    public ChoiceBox<String> ownerChoiceBox;
    public ValidatorField percentageField;
    public DatePicker startDateField;
    public DatePicker endDateField;
    public Label errorLabel;

    public static String getFxmlFilePath() {
        return "/CreateDiscountCodePanel.fxml";
    }

    @FXML
    public void initialize() {
        setUpChoiceBox();
    }

    private void setUpChoiceBox() {
        List<String> allBuyers = AllUsersController.getInstance().getAllBuyersNames();
        for (String buyerUsername : allBuyers) {
            ownerChoiceBox.getItems().add(buyerUsername);
        }
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }


    public void createDiscountCode() {
        if (!checkFields()) {
            return;
        }
        if (CodedDiscountController.getInstance().discountCodeExists(codeField.getText())) {
            errorLabel.setText("code already exists");
            return;
        }

        HashMap<String, String> discountFeatures = new HashMap<>();
        discountFeatures.put("code", codeField.getText());
        discountFeatures.put("percentage", percentageField.getText());
        discountFeatures.put("owner username", ownerChoiceBox.getValue());
        discountFeatures.put("start date", startDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        discountFeatures.put("end date", endDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        CodedDiscountController.getInstance().createItem(discountFeatures);
        errorLabel.setText("discount created!");
        errorLabel.setTextFill(Color.LIGHTGREEN);
        clearAll();
    }

    private void clearAll() {
        codeField.clear();
        percentageField.clear();
        startDateField.setValue(null);
        endDateField.setValue(null);
        ownerChoiceBox.setValue(null);
    }

    private boolean checkFields() {
        errorLabel.setTextFill(Color.RED);
        if (!codeField.validate()) {
            errorLabel.setText("invalid code");
        } else if (!percentageField.validate()) {
            errorLabel.setText("invalid percentage");
        } else if (ownerChoiceBox.getValue() == null) {
            errorLabel.setText("choose owner");
        } else if (startDateField.getValue() == null) {
            errorLabel.setText("choose start date");
        } else if (endDateField.getValue() == null) {
            errorLabel.setText("choose end date");
        } else if (endDateField.getValue().isBefore(startDateField.getValue())) {
            errorLabel.setText("end date should be after start date");
        } else
            return true;
        return false;
    }
}
