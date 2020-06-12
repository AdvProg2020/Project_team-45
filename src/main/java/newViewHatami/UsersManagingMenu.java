package newViewHatami;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Market;
import model.user.User;

import java.util.ArrayList;

public class UsersManagingMenu extends AppMenu {
    public ListView usersList;
    private User selectedUser;

    public static String getFxmlFilePath() {
        return "/UsersManagingMenu.fxml";
    }


    public void fillList() {
        ArrayList<User> allUsersList = Market.getInstance().getAllUsers();
        ObservableList<String> items = FXCollections.observableArrayList ();
        for (User user : allUsersList) {
            items.add(user.getUsername());
        }
        usersList.setItems(items);
    }
}