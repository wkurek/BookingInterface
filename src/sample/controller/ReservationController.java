package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Reservation;
import sample.model.ReservationDAO;

import java.io.IOException;
import java.util.Date;

public class ReservationController {
    private Stage stage;

    @FXML
    private TableView reservationsTable;

    @FXML
    private TableColumn<Reservation, Integer> reservationIdColumn;
    @FXML
    private TableColumn<Reservation, Integer>  roomNumberColumn;
    @FXML
    private TableColumn<Reservation, Integer>  customerIdColumn;
    @FXML
    private TableColumn<Reservation, Date>  dateInColumn;
    @FXML
    private TableColumn<Reservation, Date>  dateOutColumn;
    @FXML
    private TableColumn<Reservation, Boolean>  breakfastColumn;

    @FXML
    private void initialize () {
        reservationIdColumn.setCellValueFactory(cellData -> cellData.getValue().reservationIdProperty().asObject());
        roomNumberColumn.setCellValueFactory(cellData -> cellData.getValue().roomNumberProperty().asObject());
        customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        dateInColumn.setCellValueFactory(cellData -> cellData.getValue().dateInProperty());
        dateOutColumn.setCellValueFactory(cellData -> cellData.getValue().dateOutProperty());
        breakfastColumn.setCellValueFactory(cellData -> cellData.getValue().breakfastProperty().asObject());

        onReservationsLoad();
    }

    @FXML
    private void onReservationsLoad() {
        try {
            ObservableList<Reservation> reservations = ReservationDAO.getReservations();
            reservationsTable.setItems(reservations);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Error");
            alert.setHeaderText("Error while executing select statement.");
            alert.show();
        }
    }

    @FXML
    private void onNewReservation() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ReservationForm.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create Reservation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);

            ReservationFormController controller = loader.getController();
            controller.setStage(dialogStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException e) {}
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

}
