package newViewHatami;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import model.Market;
import model.user.User;

import java.io.IOException;
import java.util.ArrayList;

public class UsersManagingMenuG extends AppMenu {
    public ListView usersList;
    private User selectedUser;

    public static Scene getScene() {
        Parent root;
        try {
            root = FXMLLoader.load(UsersManagingMenuG.class.getResource("UsersManagingMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new Scene(root, 330, 400);
    }

    public void fillList(ActionEvent actionEvent) {
        ArrayList<User> allUsersList = Market.getInstance().getAllUsers();
        for (User user : allUsersList) {
            usersList.getItems().add(user.getUsername());
        }
    }
}