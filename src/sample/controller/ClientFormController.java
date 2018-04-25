package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Client;
import sample.model.ClientDAO;
import sample.util.Validator;


public class ClientFormController {
    private Stage stage;

    @FXML
    private TextField clientFormName;
    @FXML
    private TextField clientFormSurname;
    @FXML
    private TextField clientFormTelephoneNumber;
    @FXML
    private TextField clientFormEmail;

    @FXML
    private void onClientAdded() {
        String name = clientFormName.getText();
        String surname = clientFormSurname.getText();
        String telephoneNumber = clientFormTelephoneNumber.getText();
        String email = clientFormEmail.getText();

        if(Validator.validateName(name) && Validator.validateSurname(surname)
                && Validator.validateTelephoneNumber(telephoneNumber) && Validator.validateEmail(email)) {
            Client client = new Client(0, name, surname, email, telephoneNumber);
            try {
                ClientDAO.insertClient(client);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(stage);
                alert.setTitle("Error");
                alert.setHeaderText("Error while executing insert statement.");
                alert.show();
            } finally {
                onClientCanceled();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.show();
        }
    }

    @FXML
    private void onClientCanceled() {
        if(stage != null) stage.close();
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }
}
