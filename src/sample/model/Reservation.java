package sample.model;

import javafx.beans.property.*;

import java.util.Date;

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

    Reservation(int reservationId, int customerId, int roomNumber, boolean breakfast, Date dateIn, Date dateOut) {
        this.reservationId = new SimpleIntegerProperty(reservationId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.roomNumber = new SimpleIntegerProperty(roomNumber);
        this.breakfast = new SimpleBooleanProperty(breakfast);
        this.dateIn = new SimpleObjectProperty<>(dateIn);
        this.dateOut = new SimpleObjectProperty<>(dateOut);
    }

    public int getReservationId() {
        return reservationId.get();
    }

    public IntegerProperty reservationIdProperty() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId.set(reservationId);
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public boolean isBreakfast() {
        return breakfast.get();
    }

    public BooleanProperty breakfastProperty() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast.set(breakfast);
    }

    public Date getDateIn() {
        return dateIn.get();
    }

    public ObjectProperty<Date> dateInProperty() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn.set(dateIn);
    }

    public Date getDateOut() {
        return dateOut.get();
    }

    public ObjectProperty<Date> dateOutProperty() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut.set(dateOut);
    }

    public int getRoomNumber() {
        return roomNumber.get();
    }

    public IntegerProperty roomNumberProperty() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber.set(roomNumber);
    }
}
