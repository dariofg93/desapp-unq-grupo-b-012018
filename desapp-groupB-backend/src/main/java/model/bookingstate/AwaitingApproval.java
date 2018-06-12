package model.bookingstate;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AwaitingApproval extends BookingState {

	public AwaitingApproval() {
		this.description = "AwaitingApproval";
	}
	
	@JsonIgnore
	@Override
	public BookingState acept() {
		return new Approved();
	}
	@JsonIgnore
	@Override
	public BookingState reject() {
		return new Rejected();
	}

	@Override
	public boolean isApproved() {
		return false;
	}
}
