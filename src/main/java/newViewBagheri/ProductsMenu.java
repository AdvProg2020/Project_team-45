package newViewBagheri;

import controller.CategoryController;
import controller.FilteringController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

public class ProductsMenu implements Initializable {
    private final CategoryController categoryController = CategoryController.getInstance();
    private final FilteringController filteringController = FilteringController.getInstance();
    public VBox subcategoriesList;
    public TextField productNameField;
    public VBox companiesNameList;
    public VBox sellersUsernameList;
    public VBox specialFeaturesListVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filteringController.clearFilters();
        addCompaniesNameList();
        addSellersNameList();

        if (!categoryController.isActiveCategoryFinal()) {
            addSubcategoriesName();
            specialFeaturesListVBox.setVisible(false);
            //TODO
        } else {
            sellersUsernameList.setVisible(false);
            //TODO
            addSpecialFeaturesList();
        }
        showProducts();
    }

    private void addSubcategoriesName() {
        for (String subcategoryName : categoryController.getActiveCategorySubcategories()) {
            Label label = new Label(subcategoryName);
            label.setOnMouseClicked(e -> viewCategory(subcategoryName));
            subcategoriesList.getChildren().add(label);
        }
    }

    private void addCompaniesNameList() {
        addCheckBoxListToVBox(categoryController.getActiveCategoryCompanies(), companiesNameList, "companyName");
//        for (String companyName : categoryController.getActiveCategoryCompanies()) {
//            CheckBox checkBox = new CheckBox(companyName);
//            checkBox.setOnAction(e -> changeFilter("companyName", checkBox));
//            companiesNameList.getChildren().add(checkBox);
//        }
    }

    private void addSellersNameList() {
        addCheckBoxListToVBox(categoryController.getActiveCategorySellers(), sellersUsernameList, "sellerUsername");
//        for (String sellerUsername : categoryController.getActiveCategorySellers()) {
//            CheckBox checkBox = new CheckBox(sellerUsername);
//            checkBox.setOnAction(e -> changeFilter("sellerUsername", checkBox));
//            sellersUsernameList.getChildren().add(checkBox);
//        }
    }

    private void addSpecialFeaturesList() {
        LinkedHashMap<String, Set<String>> specialFeaturesList = categoryController.getActiveCategoryFeaturesAndValues();
        for (Map.Entry<String, Set<String>> specialFeature : specialFeaturesList.entrySet()) {
            VBox featureVBox = new VBox();
            featureVBox.getChildren().add(new Label(specialFeature.getKey() + ":"));
            ScrollPane valuesScrollPane = new ScrollPane();
            VBox valuesVBox = new VBox();
            addCheckBoxListToVBox(specialFeature.getValue(), valuesVBox, specialFeature.getKey());
//            for (String value : specialFeature.getValue()) {
//                CheckBox valueCheckBox = new CheckBox(value);
//                valueCheckBox.setOnAction(e -> changeFilter(specialFeature.getKey(), valueCheckBox));
//                valuesVBox.getChildren().add(valueCheckBox);
//            }
            valuesScrollPane.setContent(valuesVBox);
            featureVBox.getChildren().add(valuesScrollPane);
        }
    }

    private void viewCategory(String categoryName) {
        //TODO
    }

    private void addCheckBoxListToVBox(Set<String> checkBoxTextList, VBox inputVBox, String type) {
//        Label clearLabel = new Label("clear");
//        clearLabel.setOnMouseClicked(e -> clearFilter(type, inputVBox));
        for (String checkBoxText : checkBoxTextList) {
            CheckBox checkBox = new CheckBox(checkBoxText);
            checkBox.setOnAction(e -> changeFilter(type, checkBox));
            inputVBox.getChildren().add(checkBox);
        }
    }

    public void changeFilter() {
        String productName = productNameField.getText();
        if (productName.isEmpty()) {
            filteringController.removeFilter("productName");
        } else {
            filteringController.addFilter("productName", productName);
        }
        showProducts();
    }

    private void changeFilter(String type, CheckBox input) {
        if (input.isSelected()) {
            filteringController.addFilter(type, input.getText());
        } else {
            filteringController.removeFilter(type, input.getText());
        }
        showProducts();
    }

    public void addPriceFilter() {

    }

    public void clearPriceFilter() {

    }

//    private void clearFilter(String type, VBox inputVBox) {
//        filteringController.removeFilter(type);
//        inputVBox.getChildren().
//    }

    private void showProducts() {

    }
}