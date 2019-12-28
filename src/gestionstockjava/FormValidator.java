package gestionstockjava;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

public class FormValidator {
    private String message;
    private Alert.AlertType type;
    Alert alert;

    public FormValidator(String str) {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        this.alert.setTitle("Gestion des " + str);
        this.alert.setHeaderText(null);
    }

    public FormValidator() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        this.alert.setTitle("Gestion de magazin");
        this.alert.setHeaderText(null);
    }

    public static boolean isEmptyFields(TextField ... fields){
        for(TextField field : fields){
            if(field.getText().equals("")) return true;
        }
        return false;
    }

    public boolean confirm(String message){
        this.setMessage(message);
        this.alert.setAlertType(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = this.alert.showAndWait();
        return result.get() == ButtonType.OK ? true : false;
    }

    public void shout(String message){
        this.alert.setAlertType(Alert.AlertType.INFORMATION);
        this.message = message;
        this.alert.setContentText(getMessage());
        this.alert.showAndWait();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.alert.setContentText(message);
    }

    public Alert.AlertType getType() {
        return type;
    }

    public void setType(Alert.AlertType type) {
        this.type = type;
    }
}
