package client.newViewHatami;

import client.controller.CategoryController;
import client.newViewNedaei.Panel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.List;

public class CreateCategoryPanel extends Panel {

    public Label errorLabel;
    public TextArea categoryFeaturesInput;

    public static String getFxmlFilePath() {
        return "/CreateCategoryPanel.fxml";
    }

    @FXML
    public void initialize() {
        setUpChoiceBox();

    }

    private void setUpChoiceBox() {
        parentCategoryChoiceBox.setItems(FXCollections.observableArrayList());
        parentCategoryChoiceBox.getItems().add("null");

        List<String> parentCategoriesNames = CategoryController.getInstance().getParentCategoriesNames();
        for (String parentCategoryName : parentCategoriesNames) {
            parentCategoryChoiceBox.getItems().add(parentCategoryName);
        }
        parentCategoryChoiceBox.setValue("null");
    }

    public ChoiceBox<String> parentCategoryChoiceBox;
    public CheckBox finalCategoryCheckBox;
    public ValidatorField categoryName;


    public void createCategory() {
        if (!categoryName.validate()) {
            errorLabel.setText("invalid category name");
            return;
        }
        if (CategoryController.getInstance().categoryNameExists(categoryName.getText())) {
            errorLabel.setText("category name already exists");
            return;
        }

        HashMap<String, String> categoryInfo = new HashMap<>();
        categoryInfo.put("name", categoryName.getText());
        categoryInfo.put("is final?", finalCategoryCheckBox.isSelected() ? "yes" : "no");
        categoryInfo.put("features", categoryFeaturesInput.getText());
        categoryInfo.put("parent category", parentCategoryChoiceBox.getValue());

        CategoryController.getInstance().createItem(categoryInfo);
        errorLabel.setText("category made successfully");
        categoryName.clear();
        categoryFeaturesInput.clear();
        setUpChoiceBox();
    }


    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void setSpecialFeaturesFields() {
        categoryFeaturesInput.setDisable(!finalCategoryCheckBox.isSelected());
    }
}
