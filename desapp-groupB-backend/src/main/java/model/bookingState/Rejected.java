package model.bookingState;

public class Rejected implements BookingState {
    @Override
    public BookingState setAcepted() {
        return new Approved();
    }

    @Override
    public BookingState setRejected() {
        return this;
    }
}
