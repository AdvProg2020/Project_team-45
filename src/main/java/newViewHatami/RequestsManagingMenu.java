package newViewHatami;

import controller.RequestController;
import newViewNedaei.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Market;
import model.request.Request;

import java.util.ArrayList;

public class RequestsManagingMenu extends AppMenu {
    public ListView requestsList;
    private String selectedRequestId;

    public static String getFxmlFilePath() {
        return "/RequestsManagingMenu.fxml";
    }


    public void fillList() {
        // TODO : change list to table
        ArrayList<Request> allRequestsList = Market.getInstance().getAllRequests();
        ObservableList<String> items = FXCollections.observableArrayList ();
        for (Request request : allRequestsList) {
            items.add(request.getId());
        }
        requestsList.setItems(items);
    }

    public void acceptSelectedRequest() {
        RequestController.getInstance().getItemById(selectedRequestId).accept();
        fillList();
    }

    public void declineSelectedRequest() {
        RequestController.getInstance().getItemById(selectedRequestId).decline();
        fillList();
    }

    public void setSelectedRequest() {
        selectedRequestId = (String) requestsList.getSelectionModel().getSelectedItem();
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }

    public void showRequestDetails() {
        // TODO : make request viewer panel
    }
}
