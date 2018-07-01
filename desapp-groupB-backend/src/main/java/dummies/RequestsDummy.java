package dummies;

import model.booking.BookingRequest;
import model.bookingstate.AwaitingApproval;
import model.builders.BookingRequestBuilder;

import org.joda.time.DateTime;

import service.bookingrequest.BookingRequestService;
import service.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class RequestsDummy implements DummyData{

    private List<BookingRequest> bookings = new ArrayList<>();
    private BookingRequestBuilder builder = new BookingRequestBuilder();
    private BookingRequestService service;
    private UserService userService;

	public RequestsDummy(){
   
    }

    public void setBookingRequestBuilder(BookingRequestBuilder bookingBuilder) { this.builder= bookingBuilder; }
    public BookingRequestBuilder getBookingRequestBuilder() { return this.builder; }

    public void setBookingRequests(List<BookingRequest> bookings) { this.bookings = bookings; }
    public List<BookingRequest> getBookingRequests() { return this.bookings; }

    public void setService(BookingRequestService service) {
        this.service = service;
    }
    public BookingRequestService getService() {
        return this.service;
    }

    public void instantiateDataMock(){
    	
    	this.initializeContext();
        this.bookings.forEach(
                (BookingRequest booking) -> this.service.save(booking)
        );
    }

    private void initializeContext() {
        BookingRequest booking1 = builder.createBookingRequest()
                .withState(new AwaitingApproval())
                .withRequester(userService.retriveAll().get(2))
                .withTotalHours(5)
                .withDateTimeOfReservation(null)
                .withHoursOfTheReservation(null)
            
                .build();
        this.bookings.add(booking1);

        BookingRequest booking2 = builder.createBookingRequest()
                .withRequester(userService.retriveAll().get(2))
                .withTotalHours(20)
                .withDateTimeOfReservation(new DateTime(2018,2,15,0,0))
                .withHoursOfTheReservation(21)
                
                .build();
        booking2.acept();
        this.bookings.add(booking2);
		
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
