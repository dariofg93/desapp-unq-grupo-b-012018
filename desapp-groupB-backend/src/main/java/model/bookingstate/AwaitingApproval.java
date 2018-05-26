package model.bookingstate;

public class AwaitingApproval extends BookingState {

	public AwaitingApproval() {
		this.description = "AWA";
	}

	@Override
	public BookingState setAcepted() {
		return new Approved();
	}

	@Override
	public BookingState setRejected() {
		return new Rejected();
	}

	@Override
	public boolean isApproved() {
		return false;
	}
}
