package newViewNedaei.user.seller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import newViewNedaei.MenuController;

public class AddProductPanel {
    @FXML
    private GridPane newPane;
    @FXML
    private GridPane existingPane;
    @FXML
    private CheckBox checkBox;

    public static String getFxmlFilePath() {
        return "/AddProductPanel.fxml";
    }

    @FXML
    public void initialize() {
        existingPane.setDisable(true);
        existingPane.setVisible(false);
        newPane.setDisable(false);
        newPane.setVisible(true);
    }

    public void actionCheckBox() {
        if (checkBox.isSelected()) {
            newPane.setDisable(true);
            newPane.setVisible(false);
            existingPane.setDisable(false);
            existingPane.setVisible(true);
        } else {
            existingPane.setDisable(true);
            existingPane.setVisible(false);
            newPane.setDisable(false);
            newPane.setVisible(true);
        }
    }

    public void goBack() {
        MenuController.getInstance().enableCurrentPane();
    }
}
