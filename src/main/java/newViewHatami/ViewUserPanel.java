package newViewHatami;

import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import newViewNedaei.Panel;

import java.util.HashMap;

public class ViewUserPanel extends Panel {

    public static String getFxmlFilePath() {
        return "/ViewUserPanel.fxml";
    }

    @FXML
    public void initialize() {
        String showingUserUsername = UsersManagingMenu.getSelectedUsername();
        HashMap<String, String> showingInfo = UserController.getUserViewInfo(showingUserUsername);
        setLabelsValues(showingInfo);
    }


    public Label usernameLabel;
    public Label roleLabel;
    public Label firstNameLabel;
    public Label phoneNumberLabel;
    public Label lastNameLabel;
    public Label emailLabel;
    public ImageView userImage;

    private void setLabelsValues(HashMap<String, String> showingInfo) {
        usernameLabel.setText(showingInfo.get("username"));
        roleLabel.setText(showingInfo.get("role"));
        firstNameLabel.setText(showingInfo.get("firstName"));
        lastNameLabel.setText(showingInfo.get("lastName"));
        emailLabel.setText(showingInfo.get("email"));
        phoneNumberLabel.setText(showingInfo.get("phoneNumber"));
        // TODO : set image

//        FileInputStream input = null;
//        try {
//            input = new FileInputStream("/poker.png");
//        } catch (FileNotFoundException e) {
//            e.getMessage();
//            System.out.println("oh!");
//        }
//        Image image = new Image(input);
//        userImage.setImage(image);
    }

}
