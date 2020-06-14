package newViewHatami;

import controller.userControllers.BuyerController;
import controller.userControllers.SellerController;
import controller.userControllers.UserController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import newViewNedaei.MenuController;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static javafx.scene.paint.Color.RED;

public class LoginRegisterMenu {
    public ToggleButton registerToggleButton;
    public ToggleButton loginToggleButton;
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

    public TextField loginUsernameField;
    public PasswordField loginPasswordField;

    public Label loginErrorLabel;
    public Label registerErrorLabel;
    private ArrayList<Validator> personalInfoFields;

    private ToggleButton selectedToggle;
    public Pane loginPane;
    public Pane registerPane;
    public ChoiceBox<String> roleSelectionChoiceBox;

    public static String getFxmlFilePath() {
        return "/LoginRegisterMenu.fxml";
    }

    @FXML
    public void initialize() {
        makeToggleGroup();
        makeRegisterRoleSelectionChoiceBox();
        setPersonalInfoFields();
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

    private void makeRegisterRoleSelectionChoiceBox() {
        roleSelectionChoiceBox.setItems(FXCollections.observableArrayList("customer", "seller"));
        roleSelectionChoiceBox.setValue("customer");
    }

    private void makeToggleGroup() {
        ToggleGroup toggleGroup = new ToggleGroup();
        registerToggleButton.setToggleGroup(toggleGroup);
        loginToggleButton.setToggleGroup(toggleGroup);
        toggleGroup.selectToggle(loginToggleButton);
        loginToggleButton.setSelected(true);
        selectedToggle = loginToggleButton;
        toggleGroup.selectedToggleProperty().addListener((ov, toggle, new_toggle) -> {
            if (loginToggleButton.isSelected()) {
                loginPane.setVisible(true);
                registerPane.setVisible(false);
                selectedToggle = loginToggleButton;
            }
            else if (registerToggleButton.isSelected()){
                loginPane.setVisible(false);
                registerPane.setVisible(true);
                selectedToggle = registerToggleButton;
            }
            else
                selectedToggle.setSelected(true);
        });

    }


    public void changeRegisterRole() {
        sellerCompanyInfoPane.setVisible(!roleSelectionChoiceBox.getValue().equals("customer"));
    }

    public void doRegister() {
        if (!checkPersonalInfoFields())
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

    private boolean checkPersonalInfoFields() {
        registerErrorLabel.setTextFill(RED);
        if (!RegisterFieldsAreValid()) {
            registerErrorLabel.setText("pay attention to valid formats");
            return false;
        }
        try {
            if (UserController.getInstance().usernameExists(newUsernameField.getText())) {
                registerErrorLabel.setText("username already exists");
                return false;
            }
        } catch (Exception e) {
            loginErrorLabel.setText("this username has a non-accepted request");
            return false;
        }
        if (newPasswordField.getText().equals(repeatPasswordField.getText()))
            return true;
        registerErrorLabel.setText("passwords does'nt match");
        return false;
    }

    private boolean RegisterFieldsAreValid() {
        boolean flag = true;
        registerErrorLabel.setTextFill(RED);
        for (Validator personalInfoField : personalInfoFields) {
            if (!personalInfoField.validate())
                flag = false;
        }
        if (roleSelectionChoiceBox.getValue().equals("seller") && !companyNameField.validate()) flag = false;
        return flag;
    }


    public void doLogin() {
        loginErrorLabel.setTextFill(RED);
        if (loginUsernameField.getText().equals("")) {
            loginErrorLabel.setText("you missed username");
        } else if (loginPasswordField.getText().equals("")) {
            loginErrorLabel.setText("you missed password");
        } else {
            try {
                if (!UserController.getInstance().usernameExists(loginUsernameField.getText())) {
                    loginErrorLabel.setText("The username is invalid!");
                } else if (!UserController.getInstance().login(loginUsernameField.getText(), loginPasswordField.getText())) {
                    loginErrorLabel.setText("wrong password");
                } else {
                    loginErrorLabel.setText("login successful");
                    MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
                    // TODO : put address to go after login
                }
            } catch (Exception e) {
                loginErrorLabel.setText("this username has a non-accepted request");
            }
        }
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }
}
