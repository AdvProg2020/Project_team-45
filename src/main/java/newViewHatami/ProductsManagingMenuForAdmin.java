package newViewHatami;

import controller.ProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Market;
import model.product.Product;
import newViewNedaei.MenuController;

import java.util.ArrayList;

public class ProductsManagingMenuForAdmin extends AppMenu {
    public ListView productsList;
    public Label errorLabel;

    @FXML
    public void initialize(){
        fillList();
    }

    private void fillList() {
        ArrayList<Product> allProductsList = Market.getInstance().getAllProducts();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Product product : allProductsList) {
            items.add(product.getId() + ":" + product.getName());
        }
        productsList.setItems(items);
    }

    public static String getFxmlFilePath() {
        return "/ProductsManagingMenuForAdmin.fxml";
    }

    public void viewSelectedProduct() {
        // TODO
    }

    public void deleteSelectedProduct() {
        String selectedProductId = getSelectedProductId();
        ProductController.getInstance().deleteItemById(selectedProductId);
        fillList();
        errorLabel.setText("product removed successfully!");

    }

    public String getSelectedProductId(){
        if (productsList.getSelectionModel().getSelectedItem() != null)
            return ((String) productsList.getSelectionModel().getSelectedItem()).split(":")[0];
        return null;
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }
}
