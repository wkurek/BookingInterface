package sample.model;

import javafx.beans.property.*;

import java.util.Date;

/**
 * Class represents row in RESERVATIONS table in database.
 */
public class Reservation {
    private IntegerProperty reservationId;
    private IntegerProperty customerId;
    private IntegerProperty roomNumber;
    private BooleanProperty breakfast;
    private ObjectProperty<Date> dateIn;
    private ObjectProperty<Date> dateOut;

    Reservation() {
        this(0, 0, 0, false, null, null);
    }

    public Reservation(int reservationId, int customerId, int roomNumber, boolean breakfast, Date dateIn, Date dateOut) {
        this.reservationId = new SimpleIntegerProperty(reservationId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.roomNumber = new SimpleIntegerProperty(roomNumber);
        this.breakfast = new SimpleBooleanProperty(breakfast);
        this.dateIn = new SimpleObjectProperty<>(dateIn);
        this.dateOut = new SimpleObjectProperty<>(dateOut);
    }

    public IntegerProperty reservationIdProperty() {
        return reservationId;
    }

    void setReservationId(int reservationId) {
        this.reservationId.set(reservationId);
    }

    int getCustomerId() {
        return customerId.get();
    }

    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    boolean isBreakfast() {
        return breakfast.get();
    }

    public BooleanProperty breakfastProperty() {
        return breakfast;
    }

    void setBreakfast(boolean breakfast) {
        this.breakfast.set(breakfast);
    }

    Date getDateIn() {
        return dateIn.get();
    }

    public ObjectProperty<Date> dateInProperty() {
        return dateIn;
    }

    void setDateIn(Date dateIn) {
        this.dateIn.set(dateIn);
    }

    Date getDateOut() {
        return dateOut.get();
    }

    public ObjectProperty<Date> dateOutProperty() {
        return dateOut;
    }

    void setDateOut(Date dateOut) {
        this.dateOut.set(dateOut);
    }

    public IntegerProperty roomNumberProperty() {
        return roomNumber;
    }

    void setRoomNumber(int roomNumber) {
        this.roomNumber.set(roomNumber);
    }
}
