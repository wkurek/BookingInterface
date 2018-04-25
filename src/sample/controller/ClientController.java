package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Client;
import sample.model.ClientDAO;
import sample.util.Validator;

import java.io.IOException;

public class ClientController {
    @FXML
    private TableView clientsTable;

    @FXML
    private TableColumn<Client, Integer> idColumn;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> surnameColumn;
    @FXML
    private TableColumn<Client, String> emailColumn;
    @FXML
    private TableColumn<Client, String> telephoneColumn;

    @FXML
    private Button deleteClientButton;
    @FXML
    private Button updateClientButton;

    @FXML
    private TextField clientTelephoneInput;
    @FXML
    private TextField clientEmailInput;

    @FXML
    private void initialize () {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().telephoneNumberProperty());

        deleteClientButton.setDisable(true);
        updateClientButton.setDisable(true);
        onLoadClients();
        clientsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> presentClient( (Client) newValue));
    }

    private void presentClient(Client client) {
        if(client != null) {
            clientTelephoneInput.setText(client.getTelephoneNumber());
            clientEmailInput.setText(client.getEmail());

            deleteClientButton.setDisable(false);
            updateClientButton.setDisable(false);
        } else {
            clientTelephoneInput.setText("");
            clientEmailInput.setText("");

            deleteClientButton.setDisable(true);
            updateClientButton.setDisable(true);
        }
    }

    @FXML
    private void onLoadClients() {
        try {
            ObservableList<Client> clients = ClientDAO.getClients();
            clientsTable.setItems(clients);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: show alert
        }
    }

    @FXML
    private void onDeleteClient() {
        Client selectedClient = (Client) clientsTable.getSelectionModel().getSelectedItem();
        int selectedClientId = clientsTable.getSelectionModel().getSelectedIndex();

        if(selectedClient != null && selectedClientId >= 0) {
            try {
                ClientDAO.deleteClient(selectedClient.getId());
                clientsTable.getItems().remove(selectedClientId);
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: show alert
            }
        } else {
            deleteClientButton.setDisable(true);
        }
    }

    @FXML
    private void onUpdateClient() {
        String telephoneNumber = clientTelephoneInput.getText();
        String email = clientEmailInput.getText();
        Client selectedClient = (Client) clientsTable.getSelectionModel().getSelectedItem();

        if(Validator.validateTelephoneNumber(telephoneNumber) && Validator.validateEmail(email)
                && selectedClient != null) {
            try {
                selectedClient.setTelephoneNumber(telephoneNumber);
                selectedClient.setEmail(email);
                ClientDAO.updateClient(selectedClient);
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: show alert
            }
        } else {
            //TODO: display alert: "invalid data"
        }
    }

    @FXML
    private void onNewClient() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ClientForm.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create Client");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.getPrimaryStage());

            ClientFormController controller = loader.getController();
            controller.setStage(dialogStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException e) {
            //TODO: show alert
        }
    }
}
