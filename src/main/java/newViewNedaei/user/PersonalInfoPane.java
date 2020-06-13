package newViewNedaei.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
}
