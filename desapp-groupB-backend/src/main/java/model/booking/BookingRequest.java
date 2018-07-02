package model.booking;

import model.bookingstate.AwaitingApproval;
import model.bookingstate.BookingState;
import model.email.MailBody;
import model.exceptions.NoAceptedException;
import model.user.User;
import model.utils.Entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

public class BookingRequest extends Entity implements MailBody {

	private BookingState state;
	private User requester;
	private Integer totalHours;
	private DateTime reservationDateTime;
	private Integer hoursOfTheReservation;
	private BookingStatus status;

	public BookingRequest() {
		this.state = new AwaitingApproval();
		this.status = new BookingStatus(this.getId());
	}

	@Override
	public boolean equals(Object request) {
		return (this.getId() == ((Entity) request).getId());
	}

	@JsonIgnore
	public void acept() {
		this.state = this.state.acept();
	}

	@JsonIgnore
	public void reject() {
		this.state = this.state.reject();
	}

	public void setStateOfVehicleRetreatBuyer(Boolean status) throws NoAceptedException {
		this.status.setConfirmRetreatBuyer(status);
	}

	public void setStateOfVehicleRetreatSeller(Boolean status) throws NoAceptedException {
		this.status.setConfirmRetreatSeller(status);
	}

	public void setStateOfVehicleReturnBuyer(Boolean status) throws NoAceptedException {
		this.status.setConfirmReturnBuyer(status);
	}

	public void setStateOfVehicleReturnSeller(Boolean status) throws NoAceptedException {
		this.status.setConfirmReturnSeller(status);
	}

	public DateTime endOfReservation() {
		return this.reservationDateTime.plusHours(this.totalHours);
	}

	@JsonIgnore
	public boolean isApproved() {
		return state.isApproved();
	}

	/** Setters and Getters **/

	public BookingState getState() {
		return this.state;
	}

	public Integer getTotalHours() {
		return this.totalHours;
	}

	public void setHoursOfTheReservation(Integer hoursOfTheReservation) {
		this.hoursOfTheReservation = hoursOfTheReservation;
	}

	public void setState(BookingState state) {
		this.state = state;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	public Integer getHoursOfTheReservation() {
		return this.hoursOfTheReservation;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	@JsonIgnore
	public DateTime getReservationDateTime() {
		return reservationDateTime;
	}

	@JsonIgnore
	public void setReservationDateTime(DateTime reservationDateTime) {
		this.reservationDateTime = reservationDateTime;
	}

	@JsonIgnore
	public boolean getConfirmReturnBuyer() {
		return this.status.getConfirmReturnBuyer();
	}

	@JsonIgnore
	public boolean getConfirmReturnSeller() {
		return this.status.getConfirmReturnSeller();
	}

	@JsonIgnore
	public boolean getConfirmRetreatSeller() {
		return this.status.getConfirmRetreatSeller();
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
		if (status != null) {
			this.status.setRequest(this.getId());
		}
	}
}
