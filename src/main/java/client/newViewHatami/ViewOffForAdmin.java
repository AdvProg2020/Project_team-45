package client.newViewHatami;

import client.controller.OffController;
import client.newViewNedaei.Panel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.HashMap;
import java.util.List;

public class ViewOffForAdmin extends Panel {
    public Label startTimeLabel;
    public Label endTimeLabel;
    public Label discountAmountLabel;
    public ListView<String> productsListView;
    private static String viewingOffId;

    public static void setViewingOff(String viewingOffId) {
        ViewOffForAdmin.viewingOffId = viewingOffId;
    }

    public static String getFxmlFilePath() {
        return "/ViewOffForAdmin.fxml";
    }

    @FXML
    public void initialize() {
        // TODO : debug
        HashMap<String, String> offInfo = OffController.getInstance().getOffInfo(viewingOffId);
        startTimeLabel.setText(offInfo.get("startTime"));
        endTimeLabel.setText(offInfo.get("endTime"));
        discountAmountLabel.setText(offInfo.get("discountAmount"));
        makeProductsList();
    }

    private void makeProductsList() {
        List<String> offProductsList = OffController.getInstance().getProductsList(viewingOffId);
        ObservableList<String> items = FXCollections.observableArrayList ();
        items.addAll(offProductsList);
        productsListView.setItems(items);
    }
}
