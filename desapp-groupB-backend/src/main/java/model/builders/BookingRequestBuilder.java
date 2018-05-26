package model.builders;

import model.booking.BookingRequest;
import model.bookingstate.BookingState;
import model.user.User;
import org.joda.time.DateTime;

public class BookingRequestBuilder {

    private BookingRequest buildBookingRequest;

    public BookingRequestBuilder createBookingRequest() {
        this.buildBookingRequest = new BookingRequest();
        return this;
    }

    public BookingRequest build() {
        return this.buildBookingRequest;
    }

    public BookingRequestBuilder withState(BookingState anyState) {
        this.buildBookingRequest.setState(anyState);
        return this;
    }

    public BookingRequestBuilder withDateTimeOfReservation(DateTime anyDateTime) {
        this.buildBookingRequest.setReservationDateTime(anyDateTime);
        return this;
    }

    public BookingRequestBuilder withTotalHours(Integer totalHours) {
        this.buildBookingRequest.setTotalHours(totalHours);
        return this;
    }

    public BookingRequestBuilder withRequester(User anyRequester) {
        this.buildBookingRequest.setRequester(anyRequester);
        return this;
    }

    public BookingRequestBuilder withHoursOfTheReservation(Integer anyHoursOfTheReservation) {
        this.buildBookingRequest.setHoursOfTheReservation(anyHoursOfTheReservation);
        return this;
    }
}
