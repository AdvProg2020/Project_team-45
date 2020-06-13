package newViewHatami;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginRegisterMenu {
    public ToggleButton registerToggleButton;
    public ToggleButton loginToggleButton;
    public GridPane personalInfoFieldsPane;
    public GridPane sellerCompanyInfoPane;
    public Button registerButton;
    public Label messageLabel;


    public PasswordValidatorField newPasswordField;
    public PasswordValidatorField repeatPasswordField;
    public ValidatorField newUsernameField;
    public ValidatorField firstNameField;
    public ValidatorField lastNameField;
    public ValidatorField emailField;
    public ValidatorField phoneNumberField;
    public ValidatorField companyNameField;
    public TextArea companyDescriptionField;
    public ValidatorField loginUsernameField;
    public PasswordField loginPasswordField;
    private ArrayList<ValidatorField> personalInfoFields;

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
        personalInfoFields.add(loginUsernameField);
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
        if (checkPersonalInfoFields()) {
            HashMap<String, String> registerFields = getPersonalInfoMap();
        }
    }

    private boolean checkPersonalInfoFields() {
        return false;
    }

    private HashMap<String, String> getPersonalInfoMap() {
        HashMap<String, String> personalInfoFields = new HashMap<>();
        personalInfoFields.put("username", loginUsernameField.getText());
        personalInfoFields.put("password", loginUsernameField.getText());
        personalInfoFields.put("username", loginUsernameField.getText());
        personalInfoFields.put("username", loginUsernameField.getText());
        personalInfoFields.put("username", loginUsernameField.getText());
        personalInfoFields.put("username", loginUsernameField.getText());




        return personalInfoFields;
    }

    public void doLogin() {
    }

    public void validate(KeyEvent keyEvent) {
        ((ValidatorField) keyEvent.getSource()).validate();
    }
}
