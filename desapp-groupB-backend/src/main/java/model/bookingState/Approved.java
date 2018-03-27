package model.bookingState;

public class Approved implements BookingState {
    @Override
    public BookingState setAcepted() {
        return this;
    }

    @Override
    public BookingState setRejected() {
        return new Rejected();
    }
}
