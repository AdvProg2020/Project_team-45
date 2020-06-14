package newViewHatami;

import controller.userControllers.AdminController;
import controller.userControllers.BuyerController;
import controller.userControllers.SellerController;
import controller.userControllers.UserController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static javafx.scene.paint.Color.RED;

public class UserRegisterPane {
    public static String getFxmlFilePath() {
        return "/UserRegisterPane.fxml";
    }

    public GridPane personalInfoFieldsPane;
    public GridPane sellerCompanyInfoPane;
    public Button registerButton;


    public PasswordValidatorField newPasswordField;
    public PasswordValidatorField repeatPasswordField;
    public ValidatorField newUsernameField;
    public ValidatorField firstNameField;
    public ValidatorField lastNameField;
    public ValidatorField emailField;
    public ValidatorField phoneNumberField;
    public ValidatorField companyNameField;
    public TextArea companyDescriptionField;

    public Label registerErrorLabel;
    public Button createAdminButton;
    private ArrayList<Validator> personalInfoFields;

    public ChoiceBox<String> roleSelectionChoiceBox;

    @FXML
    public void initialize() {
        setPersonalInfoFields();
        makeRegisterRoleSelectionChoiceBox();
    }

    private void setPersonalInfoFields() {
        personalInfoFields = new ArrayList<>();
        personalInfoFields.add(newUsernameField);
        personalInfoFields.add(newPasswordField);
        personalInfoFields.add(repeatPasswordField);
        personalInfoFields.add(firstNameField);
        personalInfoFields.add(lastNameField);
        personalInfoFields.add(emailField);
        personalInfoFields.add(phoneNumberField);

    }

    public void makeRegisterRoleSelectionChoiceBox() {
        roleSelectionChoiceBox.setItems(FXCollections.observableArrayList("customer", "seller"));
        roleSelectionChoiceBox.setValue("customer");
    }

    public void changeRegisterRole() {
        sellerCompanyInfoPane.setVisible(!roleSelectionChoiceBox.getValue().equals("customer"));
    }

    public void createAdmin() {
        if (!checkPersonalInfoFields("admin"))
            return;
        LinkedHashMap<String, String> registerFields = getRegisterInfoHashMap();
        registerFields.put("username", newUsernameField.getText());
        registerErrorLabel.setTextFill(Color.LIGHTGREEN);
        AdminController.getInstance().createItem(registerFields);
        registerErrorLabel.setText("admin profile created");
        for (Validator personalInfoField : personalInfoFields) {
            ((TextField) personalInfoField).clear();
        }
    }

    public void doRegister() {
        if (!checkPersonalInfoFields(roleSelectionChoiceBox.getValue()))
            return;
        LinkedHashMap<String, String> registerFields = getRegisterInfoHashMap();
        registerErrorLabel.setTextFill(Color.LIGHTGREEN);
        if (roleSelectionChoiceBox.getValue().equals("seller")) {
            SellerController.getInstance().createItem(registerFields, newUsernameField.getText());
            registerErrorLabel.setText("register request sent");
        } else {
            BuyerController.getInstance().createItem(registerFields, newUsernameField.getText());
            registerErrorLabel.setText("registered successful");
        }
        for (Validator personalInfoField : personalInfoFields) {
            ((TextField) personalInfoField).clear();
        }
        companyNameField.clear();
        companyDescriptionField.clear();
    }

    private LinkedHashMap<String, String> getRegisterInfoHashMap() {
        LinkedHashMap<String, String> registerFields = new LinkedHashMap<>();
        registerFields.put("password", newPasswordField.getText());
        registerFields.put("first name", firstNameField.getText());
        registerFields.put("last name", lastNameField.getText());
        registerFields.put("email address", emailField.getText());
        registerFields.put("phone number", phoneNumberField.getText());
        if (roleSelectionChoiceBox.getValue().equals("seller")) {
            registerFields.put("company name", companyNameField.getText());
            registerFields.put("company info", companyDescriptionField.getText());
        }
        return registerFields;
    }

    private boolean checkPersonalInfoFields(String userRole) {
        registerErrorLabel.setTextFill(RED);
        if (!RegisterFieldsAreValid(userRole)) {
            registerErrorLabel.setText("pay attention to valid formats");
            return false;
        }
        try {
            if (UserController.getInstance().usernameExists(newUsernameField.getText())) {
                registerErrorLabel.setText("username already exists");
                return false;
            }
        } catch (Exception e) {
            registerErrorLabel.setText("this username has a non-accepted register request");
            return false;
        }
        if (newPasswordField.getText().equals(repeatPasswordField.getText()))
            return true;
        registerErrorLabel.setText("passwords does'nt match");
        return false;
    }

    private boolean RegisterFieldsAreValid(String userRole) {
        boolean flag = true;
        registerErrorLabel.setTextFill(RED);
        for (Validator personalInfoField : personalInfoFields) {
            if (!personalInfoField.validate())
                flag = false;
        }
        if (userRole.equals("seller") && !companyNameField.validate()) flag = false;
        return flag;
    }



    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }


}
