package dummies;

import model.booking.BookingRequest;
import model.bookingstate.Approved;
import model.bookingstate.AwaitingApproval;
import model.builders.BookingRequestBuilder;
import org.joda.time.DateTime;
import persistence.generic.GenericService;

import java.util.ArrayList;
import java.util.List;

public class RequestsDummy implements DummyData{

    private List<BookingRequest> bookings = new ArrayList<>();
    private BookingRequestBuilder builder = new BookingRequestBuilder();
    private GenericService<BookingRequest> service;

    public RequestsDummy(){
        BookingRequest booking1 = builder.createBookingRequest()
                .withState(new AwaitingApproval())
                .withRequester(new UsersDummy().getUsers().get(2))
                .withTotalHours(5)
                .withDateTimeOfReservation(null)
                .withHoursOfTheReservation(null)
                .build();
        this.bookings.add(booking1);

        BookingRequest booking2 = builder.createBookingRequest()
                .withState(new Approved())
                .withRequester(new UsersDummy().getUsers().get(2))
                .withTotalHours(20)
                .withDateTimeOfReservation(new DateTime(2018,2,15,0,0))
                .withHoursOfTheReservation(21)
                .build();
        this.bookings.add(booking2);
    }

    public void setBookingRequestBuilder(BookingRequestBuilder bookingBuilder) { this.builder= bookingBuilder; }
    public BookingRequestBuilder getBookingRequestBuilder() { return this.builder; }

    public void setBookingRequests(List<BookingRequest> bookings) { this.bookings = bookings; }
    public List<BookingRequest> getBookingRequests() { return this.bookings; }

    public void setService(GenericService<BookingRequest> service) {
        this.service = service;
    }
    public GenericService<BookingRequest> getService() {
        return this.service;
    }

    public void instantiateDataMock(){
        this.bookings.forEach(
                (BookingRequest booking) -> this.service.save(booking)
        );
    }
}
