package newViewHatami;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Off;
import model.product.Product;
import newViewNedaei.Panel;

import java.util.ArrayList;

public class ViewOffForAdmin extends Panel {
    public Label startTimeLabel;
    public Label endTimeLabel;
    public Label discountAmountLabel;
    public ListView productsListView;

    private static Off viewingOff;

    public static void setViewingOff(Off viewingOff) {
        ViewOffForAdmin.viewingOff = viewingOff;
    }

    public static String getFxmlFilePath() {
        return "/ViewOffForAdmin.fxml";
    }

    @FXML
    public void initialize() {
        startTimeLabel.setText(viewingOff.getStartTime().toString());
        endTimeLabel.setText(viewingOff.getEndTime().toString());
        discountAmountLabel.setText(String.valueOf(viewingOff.getDiscountAmount()));
        makeProductsList();
    }

    private void makeProductsList() {
        ArrayList<Product> allProductsList = viewingOff.getProductsList();
        ObservableList<String> items = FXCollections.observableArrayList ();
        for (Product product : allProductsList) {
            items.add(product.getName());
        }
        productsListView.setItems(items);
    }
}
