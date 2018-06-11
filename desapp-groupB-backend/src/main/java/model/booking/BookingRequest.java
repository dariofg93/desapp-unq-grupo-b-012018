package model.booking;

import model.bookingstate.AwaitingApproval;
import model.bookingstate.BookingState;
import model.email.MailBody;
import model.exceptions.NoAceptedException;
import model.publication.Publication;
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

    public BookingRequest(){
        this.state = new AwaitingApproval();
        this.reservationDateTime = DateTime.now();
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

    public void setStateOfVehicleRetreatBuyer(Boolean state) throws NoAceptedException {
        this.state.setConfirmRetreatBuyer(state);
    }

    public void setStateOfVehicleRetreatSeller(Boolean state) throws NoAceptedException {
        this.state.setConfirmRetreatSeller(state);
    }

    public void setStateOfVehicleReturnBuyer(Boolean state) throws NoAceptedException {
        this.state.setConfirmReturnBuyer(state);
    }

    public void setStateOfVehicleReturnSeller(Boolean state) throws NoAceptedException {
        this.state.setConfirmReturnSeller(state);
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
  	
    public DateTime getReservationDateTime() {
  		return reservationDateTime;
  	}

  	public void setReservationDateTime(DateTime reservationDateTime) {
  		this.reservationDateTime = reservationDateTime;
  	}
  	    
}
