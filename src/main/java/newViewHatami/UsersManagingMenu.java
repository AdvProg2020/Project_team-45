package newViewHatami;

import controller.userControllers.AllUsersController;
import newViewNedaei.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Market;
import model.user.User;

import java.util.ArrayList;

public class UsersManagingMenu extends AppMenu {
    public ListView usersList;
    public Label errorLabel;
    private String selectedUserId;

    public static String getFxmlFilePath() {
        return "/UsersManagingMenu.fxml";
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
        selectedUserId = (String) usersList.getSelectionModel().getSelectedItem();
    }

    public void viewSelectedUser() {
        // TODO : make view panel
    }

    public void deleteSelectedUser() {
        try {
            AllUsersController.getInstance().deleteItemById(selectedUserId);
            errorLabel.setText("user deleted successfully");
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
        fillList();
    }

    public void openAdminCreatorPanel() {
        // TODO : open admin creator panel
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }
}