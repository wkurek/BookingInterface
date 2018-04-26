package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.ClientController;
import sample.controller.ReservationController;
import sample.controller.RootController;
import sample.model.Reservation;
import sample.model.ReservationDAO;
import sample.util.DateFormatter;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Booking");

        initRootLayout();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene primaryScene = new Scene(rootLayout);
            primaryStage.setScene(primaryScene);
            primaryStage.show();

            RootController clientController = loader.getController();
            clientController.setStage(primaryStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
