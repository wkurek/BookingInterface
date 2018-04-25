package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.model.Client;
import sample.model.ClientDAO;

import java.sql.SQLException;

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
    private void initialize () {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().telephoneNumberProperty());

        try {
            ObservableList<Client> clients = ClientDAO.getClients();
            clientsTable.setItems(clients);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            deleteClientButton.setDisable(true);
        }
    }
}
