package model.bookingstate;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Rejected extends BookingState {

	public Rejected() {
		this.description = "REJ";
	}
	@JsonIgnore
    @Override
    public BookingState acept() {
        return new Approved();
    }
	@JsonIgnore
    @Override
    public BookingState reject() {
        return this;
    }
    
	@Override
	public boolean isApproved() {
		return false;
	}
}
