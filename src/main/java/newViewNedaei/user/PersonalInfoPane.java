package newViewNedaei.user;

import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.user.PersonalInfo;

public class PersonalInfoPane {
    @FXML
    private Label username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField emailAddress;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField password;
    private PersonalInfo personalInfo;

    public PersonalInfoPane() {
//        personalInfo = UserController.getActiveUser().getPersonalInfo();
    }

    public static String getFxmlFilePath() {
        return "/PersonalInfoPane.fxml";
    }

    @FXML
    public void initialize() {
//        PersonalInfo personalInfo = UserController.getActiveUser().getPersonalInfo();
        username.setText("personalInfo.getUsername()");
        firstName.setPromptText("personalInfo.getFirstName()");
        lastName.setPromptText("personalInfo.getLastName()");
        emailAddress.setPromptText("personalInfo.getEmailAddress()");
        phoneNumber.setPromptText("personalInfo.getPhoneNumber()");
        password.setPromptText("personalInfo.getPassword()");
    }

    public void editFirstName() {
        UserController.getInstance().setPersonalInfoField("firstName", firstName.getText());
        firstName.setPromptText(personalInfo.getFirstName());
    }

    public void editLastName() {
        UserController.getInstance().setPersonalInfoField("lastName", lastName.getText());
        lastName.setPromptText(personalInfo.getLastName());
    }

    public void editEmail() {
        UserController.getInstance().setPersonalInfoField("emailAddress", emailAddress.getText());
        emailAddress.setPromptText(personalInfo.getEmailAddress());
    }

    public void editPhone() {
        UserController.getInstance().setPersonalInfoField("phoneNumber", phoneNumber.getText());
        phoneNumber.setPromptText(personalInfo.getPhoneNumber());
    }

    public void editPassword() {
        UserController.getInstance().setPersonalInfoField("password", password.getText());
        password.setPromptText(personalInfo.getPassword());
    }
}
