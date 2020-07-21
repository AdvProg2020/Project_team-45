package client.newViewHatami;

import client.controller.userControllers.AllUsersController;
import client.newViewNedaei.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class UsersManagingMenu {
    public ListView<String> usersList;
    public Label errorLabel;
    private String selectedUsername;

    public static String getFxmlFilePath() {
        return "/UsersManagingMenu.fxml";
    }

    @FXML
    public void initialize(){
        fillList();
    }

    public void fillList() {
        // TODO : change list to table
        List<String> allUsersList = AllUsersController.getInstance().getAllUsernames();
        ObservableList<String> items = FXCollections.observableArrayList ();
        items.addAll(allUsersList);
        usersList.setItems(items);
    }

    public void setSelectedUser() {
        selectedUsername = usersList.getSelectionModel().getSelectedItem();
    }

    public void viewSelectedUser() {
        setSelectedUser();
        setViewingUser();
        if (!ViewUserPanel.hasSelectedUser()) {
            errorLabel.setText("no selected user");
            return;
        }
        MenuController.getInstance().goToPanel(ViewUserPanel.getFxmlFilePath());
    }

    private void setViewingUser() {
        ViewUserPanel.setSelectedUsername(selectedUsername);
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
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }
}