package newViewNedaei.user;

import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import model.user.PersonalInfo;
import newViewHatami.Validator;
import newViewHatami.ValidatorField;

public class PersonalInfoPane {
    public Label role;
    @FXML
    private Label error;
    @FXML
    private Label username;
    @FXML
    private ValidatorField firstName;
    @FXML
    private ValidatorField lastName;
    @FXML
    private ValidatorField emailAddress;
    @FXML
    private ValidatorField phoneNumber;
    @FXML
    private ValidatorField password;
    private final PersonalInfo personalInfo;

    public PersonalInfoPane() {
        personalInfo = UserController.getActiveUser().getPersonalInfo();
    }

    public static String getFxmlFilePath() {
        return "/PersonalInfoPane.fxml";
    }

    @FXML
    public void initialize() {
        role.setText(UserController.getActiveUser().getRole());
        PersonalInfo personalInfo = UserController.getActiveUser().getPersonalInfo();
        username.setText(personalInfo.getUsername());
        firstName.setPromptText(personalInfo.getFirstName());
        lastName.setPromptText(personalInfo.getLastName());
        emailAddress.setPromptText(personalInfo.getEmailAddress());
        phoneNumber.setPromptText(personalInfo.getPhoneNumber());
        password.setPromptText(personalInfo.getPassword());

    }

    public void editFirstName() {
        try {
            UserController.getInstance().setPersonalInfoField("firstName", firstName.getText());
            firstName.setPromptText(personalInfo.getFirstName());
            error.setText("");
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void editLastName() {
        try {
            UserController.getInstance().setPersonalInfoField("lastName", lastName.getText());
            lastName.setPromptText(personalInfo.getLastName());
            error.setText("");
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void editEmail() {
        try {
            UserController.getInstance().setPersonalInfoField("emailAddress", emailAddress.getText());
            emailAddress.setPromptText(personalInfo.getEmailAddress());
            error.setText("");
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void editPhone() {
        try {
            UserController.getInstance().setPersonalInfoField("phoneNumber", phoneNumber.getText());
            phoneNumber.setPromptText(personalInfo.getPhoneNumber());
            error.setText("");
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void editPassword() {
        try {
            UserController.getInstance().setPersonalInfoField("password", password.getText());
            password.setPromptText(personalInfo.getPassword());
            error.setText("");
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }
}
