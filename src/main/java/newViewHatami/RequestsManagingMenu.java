package newViewHatami;

import controller.RequestController;
import graphicview.nedaei.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Market;
import model.request.Request;

import java.util.ArrayList;

public class RequestsManagingMenu extends AppMenu {
    public ListView requestsList;
    public Label messageLabel;
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
        setSelectedRequest();
        RequestController.getInstance().getItemById(selectedRequestId).accept();
        messageLabel.setText("request accepted");
        fillList();
    }

    public void declineSelectedRequest() {
        setSelectedRequest();
        RequestController.getInstance().getItemById(selectedRequestId).decline();
        messageLabel.setText("request declined");
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
