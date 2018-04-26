package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Reservation;
import sample.model.ReservationDAO;
import sample.util.DateFormatter;
import sample.util.Validator;
import java.text.ParseException;
import java.util.Date;

public class ReservationFormController {
    private Stage stage;

    @FXML
    private TextField reservationFormCustomerId;
    @FXML
    private TextField reservationFormDateIn;
    @FXML
    private TextField reservationFormDateOut;
    @FXML
    private TextField reservationFormRoomNumbers;
    @FXML
    private CheckBox reservationFormBreakfastCheckbox;

    @FXML
    private void onReservationAdded() {
        String dateIn = reservationFormDateIn.getText();
        String dateOut = reservationFormDateOut.getText();
        String customerId = reservationFormCustomerId.getText();
        String roomNumbers = reservationFormRoomNumbers.getText();
        boolean breakfast = reservationFormBreakfastCheckbox.isSelected();

        if(Validator.validateDate(dateIn) && Validator.validateDate(dateOut) && Validator.validateCustomerId(customerId)
                && Validator.validateRoomNumbers(roomNumbers)) {

            Date parsedDateIn, parsedDateOut;
            try {
                parsedDateIn = DateFormatter.parseDate(dateIn);
                parsedDateOut = DateFormatter.parseDate(dateOut);
            } catch (ParseException e) {
                return;
            }

            Reservation reservation = new Reservation(0, Integer.parseInt(customerId), 0, breakfast,
                    parsedDateIn, parsedDateOut);

            try {
                ReservationDAO.insertReservation(reservation, getRoomNumbersArray(roomNumbers));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(stage);
                alert.setTitle("Error");
                alert.setHeaderText("Error while executing insert statement.");
                alert.show();
            } finally {
                onReservationCanceled();
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
    private void onReservationCanceled() {
        if(stage != null) stage.close();
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    private int[] getRoomNumbersArray(String roomNumbersLiteral) {
        String[] stringNumbers = roomNumbersLiteral.split(";");
        int[]  roomNumbers = new int[stringNumbers.length];

        for(int i = 0; i < stringNumbers.length; ++i) {
            roomNumbers[i] = Integer.parseInt(stringNumbers[i]);
        }

        return roomNumbers;
    }
}
