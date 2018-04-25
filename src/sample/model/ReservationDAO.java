package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DateFormatter;
import sample.util.DbHelper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ReservationDAO {
    private static class ReservationContract {
        private static final String TABLE_NAME = "RESERVATION";
        private static final String COLUMN_NAME_DATE_IN = "DATE_IN";
        private static final String COLUMN_NAME_DATE_OUT = "DATE_OUT";
        private static final String COLUMN_NAME_BREAKFAST = "BREAKFAST";
        private static final String COLUMN_NAME_RESERVATION_ID = "ID_RESERVATION";
        private static final String COLUMN_NAME_CUSTOMER_ID = "ID_CUSTOMER";
    }

    private static class RoomsReservationsContract {
        private static final String TABLE_NAME = "ROOMS_RESERVATIONS";
        private static final String COLUMN_NAME_RESERVATION_ID = "ID_RESERVATION";
        private static final String COLUMN_NAME_ROOM_ID = "ROOM_NUMBER";
    }

    private static int dbInsertReservation(Reservation reservation) {
        int breakfast = reservation.isBreakfast()? 1 : 0;

        String sqlQuery = String.format("INSERT INTO %s VALUES (0, '%s', '%s', %d, %d, %d)",
                ReservationContract.TABLE_NAME,  DateFormatter.format(reservation.getDateIn()),
                DateFormatter.format(reservation.getDateOut()), breakfast, 0, reservation.getCustomerId());

        return DbHelper.executeInsertQuery(sqlQuery);
    }

    public static void insertReservation(Reservation reservation, int[] rooms) throws SQLException, ClassNotFoundException {
        if(reservation == null) return;
        int insertedReservationId = dbInsertReservation(reservation);

        for(int roomNumber : rooms) {
            String sqlQuery = String.format("INSERT INTO %s (%s, %s) VALUES (%d, %d)", RoomsReservationsContract.TABLE_NAME,
                    RoomsReservationsContract.COLUMN_NAME_RESERVATION_ID, RoomsReservationsContract.COLUMN_NAME_ROOM_ID,
                    insertedReservationId, roomNumber);
            DbHelper.executeUpdateQuery(sqlQuery);
        }
    }

    public static ObservableList<Reservation> getReservations()  throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT room.room_number, reservation.id_reservation, reservation.date_in, reservation.date_out, " +
                "reservation.breakfast, reservation.id_customer FROM ((room INNER JOIN rooms_reservations ON " +
                "room.room_number = rooms_reservations.room_number) INNER JOIN reservation ON " +
                "rooms_reservations.id_reservation = reservation.id_reservation)";

        return getReservationsList(DbHelper.executeQuery(sqlQuery));
    }

    private static ObservableList<Reservation> getReservationsList(ResultSet resultSet) throws SQLException {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();

        while(resultSet.next()) {
            Reservation reservation = new Reservation();
            reservation.setReservationId(resultSet.getInt(ReservationContract.COLUMN_NAME_RESERVATION_ID));
            reservation.setCustomerId(resultSet.getInt(ReservationContract.COLUMN_NAME_CUSTOMER_ID));
            reservation.setDateIn(resultSet.getDate(ReservationContract.COLUMN_NAME_DATE_IN));
            reservation.setDateOut(resultSet.getDate(ReservationContract.COLUMN_NAME_DATE_OUT));
            reservation.setBreakfast(resultSet.getBoolean(ReservationContract.COLUMN_NAME_BREAKFAST));
            reservation.setRoomNumber(resultSet.getInt(1));

            reservations.add(reservation);
        }

        return reservations;
    }
}
