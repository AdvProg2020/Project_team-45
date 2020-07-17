package client.newViewHatami;

import client.newViewNedaei.Panel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import server.controller.CategoryController;

import java.util.ArrayList;

public class EditCategoryPanel extends Panel {

    public Label oldNameLabel;
    public ValidatorField newNameField;
    public Button changeNameButton;
    public Pane categoryFeaturesPane;
    public ListView<String> featuresList;
    public Button addFeatureButton;
    public Button removeFeatureButton;
    public TextField newFeatureField;
    public Label errorLabel;
    private static String editingCategoryId;

    public static String getFxmlFilePath() {
        return "/EditCategoryPanel.fxml";
    }

    @FXML
    public void initialize() {
        setUpFeaturesList();
        oldNameLabel.setText(CategoryController.getInstance().getCategoryName(editingCategoryId));
    }

    public static String getEditingCategoryId() {
        return editingCategoryId;
    }

    public static void setEditingCategoryId(String editingCategoryId) {
        EditCategoryPanel.editingCategoryId = editingCategoryId;
    }

    private void setUpFeaturesList() {
        featuresList.setItems(FXCollections.observableArrayList());
        if (!CategoryController.getInstance().categoryIsFinal(editingCategoryId)) {
            categoryFeaturesPane.setVisible(false);
            return;
        }
        ArrayList<String> categorySpecialFeatures = CategoryController.getInstance().getCategorySpecialFeatures(editingCategoryId);
        for (String categorySpecialFeature : categorySpecialFeatures) {
            featuresList.getItems().add(categorySpecialFeature);
        }
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void editName() {
        if (!newNameField.validate()) {
            errorLabel.setText("invalid new name");
            return;
        }
        String newName = newNameField.getText();
        if (CategoryController.getInstance().categoryNameExists(newName)) {
            errorLabel.setText("new name already exists");
            return;
        }
        CategoryController.getInstance().editCategoryName(editingCategoryId, newName);
        errorLabel.setText("category name changed");
        oldNameLabel.setText(CategoryController.getInstance().getCategoryName(editingCategoryId));
        newNameField.clear();
    }

    public void addFeature() {
        String newFeature = newFeatureField.getText();
        if (CategoryController.getInstance().categoryHasFeature(editingCategoryId, newFeature)) {
            errorLabel.setText("feature already exists");
            return;
        }
        CategoryController.getInstance().addFeatureToCategory(editingCategoryId, newFeatureField.getText());
        setUpFeaturesList();
        newFeatureField.clear();
    }

    public void removeSelectedFeature() {
        CategoryController.getInstance().removeFeatureFromCategory(editingCategoryId, featuresList.getSelectionModel().getSelectedItem());
        setUpFeaturesList();
    }
}
