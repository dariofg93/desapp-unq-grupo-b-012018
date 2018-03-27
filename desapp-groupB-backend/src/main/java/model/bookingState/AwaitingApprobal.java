package model.bookingState;

public class AwaitingApprobal implements BookingState {

    @Override
    public BookingState setAcepted() {
        return new Approved();
    }

    @Override
    public BookingState setRejected() {
        return new Rejected();
    }
}
