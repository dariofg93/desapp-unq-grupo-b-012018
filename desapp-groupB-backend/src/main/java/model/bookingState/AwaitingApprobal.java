package model.bookingState;

public class AwaitingApprobal extends BookingState {

    @Override
    public BookingState setAcepted() {
        return new Approved();
    }

    @Override
    public BookingState setRejected() {
        return new Rejected();
    }
}
