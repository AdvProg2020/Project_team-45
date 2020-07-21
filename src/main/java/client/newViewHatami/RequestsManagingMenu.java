package client.newViewHatami;

import client.controller.RequestController;
import client.newViewNedaei.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class RequestsManagingMenu {
    public ListView<String> requestsList;
    public Label messageLabel;

    public static String getFxmlFilePath() {
        return "/RequestsManagingMenu.fxml";
    }

    @FXML
    public void initialize(){
        fillList();
    }

    public void fillList() {
        // TODO : change list to table
        List<String> allRequestsList = RequestController.getInstance().getAllRequestsIds();
        ObservableList<String> items = FXCollections.observableArrayList ();
        items.addAll(allRequestsList);
        requestsList.setItems(items);
    }

    public void acceptSelectedRequest() {
        String selectedRequestId = getSelectedRequest();
        RequestController.getInstance().getItemById(selectedRequestId).accept();
        messageLabel.setText("request accepted");
        fillList();
    }

    public void declineSelectedRequest() {
        String selectedRequestId = getSelectedRequest();
        RequestController.getInstance().getItemById(selectedRequestId).decline();
        messageLabel.setText("request declined");
        fillList();
    }

    public String getSelectedRequest() {
        return requestsList.getSelectionModel().getSelectedItem();
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }

    public void showRequestDetails() {
        ViewRequestForAdmin.setShowingRequest(getSelectedRequest());
        MenuController.getInstance().goToPanel(ViewRequestForAdmin.getFxmlFilePath());
        // TODO : make request viewer panel
    }
}
