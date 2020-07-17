package newViewHatami;

import controller.ProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import newViewNedaei.MenuController;

import java.util.List;

public class ProductsManagingMenuForAdmin {
    public ListView<String> productsList;
    public Label errorLabel;

    @FXML
    public void initialize(){
        fillList();
    }

    private void fillList() {
        List<String> allProductsList = ProductController.getInstance().getAllProductsNamesList();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(allProductsList);
        productsList.setItems(items);
    }

    public static String getFxmlFilePath() {
        return "/ProductsManagingMenuForAdmin.fxml";
    }


    public void deleteSelectedProduct() {
        String selectedProductId = getSelectedProductId();
        ProductController.getInstance().deleteItemById(selectedProductId);
        fillList();
        errorLabel.setText("product removed successfully!");

    }

    public String getSelectedProductId(){
        if (productsList.getSelectionModel().getSelectedItem() != null)
            return (productsList.getSelectionModel().getSelectedItem()).split(":")[0];
        errorLabel.setText("no selected product");
        return null;
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }
}
