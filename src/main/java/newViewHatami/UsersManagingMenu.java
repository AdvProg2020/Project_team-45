package newViewHatami;

import controller.userControllers.AllUsersController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Market;
import model.user.User;
import newViewNedaei.MenuController;

import java.util.ArrayList;

public class UsersManagingMenu extends AppMenu {
    public ListView usersList;
    public Label errorLabel;
    private static String selectedUsername;

    public static String getFxmlFilePath() {
        return "/UsersManagingMenu.fxml";
    }

    @FXML
    public void initialize(){
        fillList();
    }

    public void fillList() {
        // TODO : change list to table
        ArrayList<User> allUsersList = Market.getInstance().getAllUsers();
        ObservableList<String> items = FXCollections.observableArrayList ();
        for (User user : allUsersList) {
            items.add(user.getUsername());
        }
        usersList.setItems(items);
    }

    public void setSelectedUser() {
        selectedUsername = (String) usersList.getSelectionModel().getSelectedItem();
    }

    public static String getSelectedUsername() {
        return selectedUsername;
    }

    public void viewSelectedUser() {
        setSelectedUser();
        if (selectedUsername == null) {
            errorLabel.setText("no selected user");
            return;
        }
        MenuController.getInstance().goToPanel(ViewUserPanel.getFxmlFilePath());
        // TODO : make view panel
    }

    public void deleteSelectedUser() {
        // TODO : add confirmation alert
        setSelectedUser();
        if (selectedUsername == null) {
            errorLabel.setText("no selected user");
            return;
        }
        try {
            AllUsersController.getInstance().deleteItemById(selectedUsername);
            errorLabel.setText("user deleted successfully");
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
        fillList();
    }

    public void openAdminCreatorPanel() {
        MenuController.getInstance().goToPanel(CreateAdminPanel.getFxmlFilePath());
        // TODO : open admin creator panel
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }
}