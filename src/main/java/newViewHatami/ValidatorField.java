package newViewHatami;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ValidatorField extends TextField {

    @FXML
    public String type;

    private final StringProperty validation = new SimpleStringProperty(this, "fieldType", ".*");
//    public final StringProperty fieldTypeProperty() { return fieldType; }
    public final String getValidation() { return validation.get(); }
    public final void setValidation(String value) { validation.set(value); }

    private final StringProperty isNecessary = new SimpleStringProperty(this, "fieldType", ".*");
    //    public final StringProperty fieldTypeProperty() { return fieldType; }
    public final String getIsNecessary() { return isNecessary.get(); }
    public final void setIsNecessary(String value) { isNecessary.set(value); }


}
