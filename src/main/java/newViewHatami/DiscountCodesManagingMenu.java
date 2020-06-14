package newViewHatami;

import controller.CodedDiscountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.CodedDiscount;
import model.Market;
import newViewNedaei.MenuController;

import java.util.ArrayList;

public class DiscountCodesManagingMenu extends AppMenu {

    public ListView<String> discountsList;
    public Label errorLabel;
    public static String selectedDiscountCode;

    public static String getFxmlFilePath() {
        return "/DiscountCodesManagingMenu.fxml";
    }

    public void initialize() {
        fillList();
    }

    public void fillList() {
        // TODO : change list to table
        ArrayList<CodedDiscount> allDiscountsList = Market.getInstance().getAllCodedDiscounts();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (CodedDiscount codedDiscount : allDiscountsList) {
            items.add(codedDiscount.getCode());
        }
        discountsList.setItems(items);
    }

    public void setSelectedDiscount() {
        selectedDiscountCode = discountsList.getSelectionModel().getSelectedItem();
    }

    public void viewSelectedDiscount() {
        setSelectedDiscount();
        // TODO : make view panel
    }

    public void deleteSelectedDiscount() {
        setSelectedDiscount();
        try {
            CodedDiscountController.getInstance().deleteItemById(selectedDiscountCode);
            errorLabel.setText("discount deleted successfully");
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
        fillList();
    }

    public void openDiscountCreatorPanel() {
        MenuController.getInstance().goToPanel(CreateDiscountCodePanel.getFxmlFilePath());
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }

}
