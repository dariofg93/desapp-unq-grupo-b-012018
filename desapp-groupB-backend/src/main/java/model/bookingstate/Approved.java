package model.bookingstate;

public class Approved extends BookingState {


	public Approved() {
		this.description = "Approved";
	}

	@Override
	public BookingState acept() {
		return this;
	}

	@Override
	public BookingState reject() {
		return new Rejected();
	}

	@Override
	public boolean isApproved() {
		return true;
	}
}
