package newViewHatami;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import model.CodedDiscount;
import model.Market;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;

import java.time.ZoneId;
import java.util.Date;

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

    private static CodedDiscount editingDiscount;

    @FXML
    public void initialize() {
        setUpFields();
    }

    private void setUpFields() {
        oldPercentage.setText(String.valueOf(editingDiscount.getPercentage()));
        oldEndDate.setText(editingDiscount.getEndDate().toString());
        oldStartDate.setText(editingDiscount.getStartDate().toString());
        codeLabel.setText(editingDiscount.getCode());
    }

    public static void setEditingDiscount(String editingDiscountCode) {
        EditDiscountCodePanel.editingDiscount = Market.getInstance().getCodedDiscountByCode(editingDiscountCode);
    }

    public static String getFxmlFilePath() {
        return "/EditDiscountCodePanel.fxml";
    }


    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void viewOwner() {
        ViewUserPanel.setSelectedUsername(editingDiscount.getOwner().getUsername());
        MenuController.getInstance().goToPanel(ViewUserPanel.getFxmlFilePath());
    }

    public void changePercentage() {
        if (!percentageField.validate()) {
            errorLabel.setText("wrong percentage");
            return;
        }
        editingDiscount.setPercentage(Integer.parseInt(percentageField.getText()));
    }

    public void changeStartDate() {
        if (startDateField.getValue() == null){
            errorLabel.setText("no selected date");
            return;
        }
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date newDate =  Date.from(startDateField.getValue().atStartOfDay(defaultZoneId).toInstant());
        if (editingDiscount.getEndDate().before(newDate)) {
            errorLabel.setText("start date is after end date!");
            return;
        }
        editingDiscount.setStartDate(newDate);

    }

    public void changeEndDate() {
        if (endDateField.getValue() == null){
            errorLabel.setText("no selected date");
            return;
        }
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date newDate =  Date.from(endDateField.getValue().atStartOfDay(defaultZoneId).toInstant());
        if (editingDiscount.getStartDate().after(newDate)) {
            errorLabel.setText("end date is before start date!");
            return;
        }
        editingDiscount.setEndDate(newDate);

    }
}
