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

    public ListView discountsList;
    public Label errorLabel;

    public static String getFxmlFilePath() {
        return "/DiscountCodesManagingMenu.fxml";
    }
    public void initialize(){
        fillList();
    }

    public void fillList() {
        // TODO : change list to table
        ArrayList<CodedDiscount> allDiscountsList = Market.getInstance().getAllCodedDiscounts();
        ObservableList<String> items = FXCollections.observableArrayList ();
        for (CodedDiscount codedDiscount : allDiscountsList) {
            items.add(codedDiscount.getId());
        }
        discountsList.setItems(items);
    }

    public String getSelectedDiscount() {
        return (String) discountsList.getSelectionModel().getSelectedItem();
    }

    public void viewSelectedDiscount() {
        // TODO : make view panel
    }

    public void deleteSelectedDiscount() {
        String selectedDiscountId = getSelectedDiscount();
        try {
            CodedDiscountController.getInstance().deleteItemById(selectedDiscountId);
            errorLabel.setText("discount deleted successfully");
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
        fillList();
    }

    public void openDiscountCreatorPanel() {
        // TODO : open admin creator panel
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }

}
