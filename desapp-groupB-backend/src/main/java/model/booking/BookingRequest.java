package model.booking;

import model.bookingState.AwaitingApprobal;
import model.bookingState.BookingState;
import model.email.MailBody;
import model.exceptions.NoAceptedException;
import model.user.User;
import org.joda.time.DateTime;

public class BookingRequest implements MailBody {

    private BookingState state;
    private User requester;
    private Integer totalHours;
    private DateTime dateTimeOfReservation;
    private Integer hoursOfTheReservation;

    public BookingRequest(){
        this.state = new AwaitingApprobal();
        this.dateTimeOfReservation = null;
    }

    public void setAcepted() {
        this.state = this.state.setAcepted();
    }

    public void setRejected() {
        this.state = this.state.setRejected();
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
        return this.dateTimeOfReservation.plusHours(this.totalHours);
    }

    /** Setters and Getters **/

    public User getRequester() {
        return this.requester;
    }

    public BookingState getState() {
        return this.state;
    }

    public Integer getTotalHours() {
        return this.totalHours;
    }

    public void setDateTimeOfReservation(DateTime dateTimeOfReservation) {
        this.dateTimeOfReservation = dateTimeOfReservation;
    }

    public DateTime getDateTimeOfReservation() {
        return this.dateTimeOfReservation;
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

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public Integer getHoursOfTheReservation() {
        return this.hoursOfTheReservation;
    }
}
