package model.booking;

import model.bookingState.AwaitingApprobal;
import model.bookingState.BookingState;
import model.user.User;

import java.time.LocalDateTime;

public class BookingRequest {

    private BookingState state;
    private User requester;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public BookingRequest(){
        this.state = new AwaitingApprobal();
    }

    public Boolean onTheSameDatesAs(BookingRequest anyRequest) {
        return !(anyRequest.getFromDate().isBefore(this.fromDate) && anyRequest.getToDate().isBefore(this.fromDate))
                                                            &&
                !(anyRequest.getFromDate().isAfter(this.toDate) && anyRequest.getToDate().isAfter(this.toDate));
    }

    public void setAcepted() {
        this.state = this.state.setAcepted();
    }

    public void setRejected() {
        this.state = this.state.setRejected();
    }

    /** Setters and Getters **/

    public LocalDateTime getFromDate() {
        return this.fromDate;
    }

    public LocalDateTime getToDate() {
        return this.toDate;
    }

    public User getRequester() {
        return this.requester;
    }
}
