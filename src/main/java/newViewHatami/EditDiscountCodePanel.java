package newViewHatami;

import controller.CodedDiscountController;
import controller.userControllers.AllUsersController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

public class EditDiscountCodePanel extends Panel {

    public ValidatorField percentageField;
    public DatePicker startDateField;
    public DatePicker endDateField;
    public Label oldPercentage;
    public Label oldStartDate;
    public Label oldEndDate;
    public Label errorLabel;
    public Label codeLabel;
    public Button viewOwnerButton;
    private static String editingDiscountCode;
    private static HashMap<String, String> editingDiscountInfo;

    @FXML
    public void initialize() {

        setUpFields();
        errorLabel.setTextFill(Color.RED);
    }

    private void setUpFields() {
        editingDiscountInfo = CodedDiscountController.getInstance().getDetailsHashMap(editingDiscountCode);
        codeLabel.setText(editingDiscountInfo.get("code"));
        oldPercentage.setText(editingDiscountInfo.get("percentage"));
        oldStartDate.setText(editingDiscountInfo.get("startDate"));
        oldEndDate.setText(editingDiscountInfo.get("endDate"));
    }

    public static void setEditingDiscount(String editingDiscountCode) {
        EditDiscountCodePanel.editingDiscountCode = editingDiscountCode;
    }

    public static String getFxmlFilePath() {
        return "/EditDiscountCodePanel.fxml";
    }


    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void viewOwner() {
        ViewUserPanel.setSelectedUsername(AllUsersController.getInstance().getUsernameById(editingDiscountInfo.get("owner")));
        MenuController.getInstance().goToPanel(ViewUserPanel.getFxmlFilePath());
    }

    public void changePercentage() {
        if (!percentageField.validate()) {
            errorLabel.setText("wrong percentage");
            return;
        }
        CodedDiscountController.getInstance().changeDiscountPercentage(editingDiscountCode, Integer.parseInt(percentageField.getText()));
        setUpFields();
    }

    public void changeStartDate() {
        if (startDateField.getValue() == null) {
            errorLabel.setText("no selected date");
            return;
        }
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date newDate = Date.from(startDateField.getValue().atStartOfDay(defaultZoneId).toInstant());

        if (!CodedDiscountController.getInstance().validateDate(editingDiscountCode, newDate, null)) {
            errorLabel.setText("start date is after end date!");
            return;
        }
        CodedDiscountController.getInstance().changeDiscountStartDate(editingDiscountCode, newDate);
        setUpFields();
    }

    public void changeEndDate() {
        if (endDateField.getValue() == null) {
            errorLabel.setText("no selected date");
            return;
        }
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date newDate = Date.from(endDateField.getValue().atStartOfDay(defaultZoneId).toInstant());
        if (!CodedDiscountController.getInstance().validateDate(editingDiscountCode, null, newDate)) {
            errorLabel.setText("end date is before start date!");
            return;
        }
        CodedDiscountController.getInstance().changeDiscountEndDate(editingDiscountCode, newDate);
        setUpFields();
    }

}
