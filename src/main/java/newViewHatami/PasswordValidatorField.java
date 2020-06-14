package newViewHatami;

import javafx.scene.control.PasswordField;

public class PasswordValidatorField extends PasswordField implements Validator {

    public boolean validate() {

        if (getText().matches("\\w+")) {
            this.setStyle("-fx-text-box-border: #039ed3;");
            return true;
        }
        this.setStyle("-fx-text-box-border: #fc0606;");
        return false;
    }
}
