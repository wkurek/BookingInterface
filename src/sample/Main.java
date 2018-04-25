package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.ClientController;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Booking");

        initRootLayout();
        initClientView();

    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene primaryScene = new Scene(rootLayout);
            primaryStage.setScene(primaryScene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initClientView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ClientView.fxml"));
            AnchorPane anchorPane = loader.load();

            ClientController clientController = loader.getController();
            clientController.setStage(primaryStage);

            rootLayout.setCenter(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
