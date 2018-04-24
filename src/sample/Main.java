package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Client;
import sample.model.ClientDAO;
import sample.model.Reservation;
import sample.model.ReservationDAO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        ObservableList<Client> clients = ClientDAO.getClients();
        for(Client client : clients) {
            System.out.println(String.format("name: %s\t surname: %s\n", client.getName(), client.getSurname()));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
