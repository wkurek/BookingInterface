package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class RootController {
    private Stage stage;

    @FXML
    private BorderPane rootLayout;

    @FXML
    private void onNewReservationClicked() {
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

    @FXML
    private void onNewClientClicked() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ClientForm.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create Client");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);

            ClientFormController controller = loader.getController();
            controller.setStage(dialogStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException e) {}
    }

    @FXML
    private void onShowReservationsClicked() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ReservationView.fxml"));
            AnchorPane anchorPane = loader.load();

            ReservationController controller = loader.getController();
            controller.setStage(stage);

            rootLayout.setCenter(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onShowClientsClicked() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ClientView.fxml"));
            AnchorPane anchorPane = loader.load();

            ClientController clientController = loader.getController();
            clientController.setStage(stage);

            rootLayout.setCenter(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
