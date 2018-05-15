package model.booking;

import junit.framework.TestCase;
import model.bookingstate.BookingState;
import model.builders.BookingRequestBuilder;
import model.exceptions.NoAceptedException;
import model.user.User;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.junit.Before;

import static org.mockito.Mockito.*;

public class BookingRequestTest extends TestCase {

    private BookingRequestBuilder bookingRequestBuilder;

    private BookingState anyStateMock;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.bookingRequestBuilder = new BookingRequestBuilder();

        this.anyStateMock = mock(BookingState.class);
	}

    public void testSetAcepted(){
        BookingRequest anyBookingRequest = bookingRequestBuilder.createBookingRequest()
                .withState(anyStateMock)
                .build();

        anyBookingRequest.setAcepted();

        verify(anyStateMock).setAcepted();
    }

    public void testSetRejected(){
        BookingRequest anyBookingRequest = bookingRequestBuilder.createBookingRequest()
                .withState(anyStateMock)
                .build();

        anyBookingRequest.setRejected();

        verify(anyStateMock).setRejected();
    }

    public void testSetStateOfVehicleRetreatBuyer() throws NoAceptedException {
        BookingRequest anyBookingRequest = bookingRequestBuilder.createBookingRequest()
                .withState(anyStateMock)
                .build();

        anyBookingRequest.setStateOfVehicleRetreatBuyer(true);

        verify(anyStateMock).setConfirmRetreatBuyer(true);
    }

    public void testSetStateOfVehicleRetreatSeller() throws NoAceptedException {
        BookingRequest anyBookingRequest = bookingRequestBuilder.createBookingRequest()
                .withState(anyStateMock)
                .build();

        anyBookingRequest.setStateOfVehicleRetreatSeller(true);

        verify(anyStateMock).setConfirmRetreatSeller(true);
    }

    public void testSetStateOfVehicleReturnBuyer() throws NoAceptedException {
        BookingRequest anyBookingRequest = bookingRequestBuilder.createBookingRequest()
                .withState(anyStateMock)
                .build();

        anyBookingRequest.setStateOfVehicleReturnBuyer(false);

        verify(anyStateMock).setConfirmReturnBuyer(false);
    }

    public void testSetStateOfVehicleReturnSeller() throws NoAceptedException {
        BookingRequest anyBookingRequest = bookingRequestBuilder.createBookingRequest()
                .withState(anyStateMock)
                .build();

        anyBookingRequest.setStateOfVehicleReturnSeller(false);

        verify(anyStateMock).setConfirmReturnSeller(false);
    }

    public void testEndOfReservation(){
        BookingRequest anyBookingRequest = bookingRequestBuilder.createBookingRequest()
                .withDateTimeOfReservation(DateTime.now())
                .withTotalHours(20)
                .build();

        DateTime endOfReservation = anyBookingRequest.endOfReservation();

        assertEquals(
                Hours.hoursBetween(anyBookingRequest.getDateTimeOfReservation(),endOfReservation).getHours(),
                20);
    }

    public void testGettersAndSettersForCoverage(){
        User anyRequesterMock = mock(User.class);

        BookingRequest anyBookingRequest = bookingRequestBuilder.createBookingRequest()
                .withRequester(anyRequesterMock)
                .withState(anyStateMock)
                .withTotalHours(5)
                .withHoursOfTheReservation(25)
                .build();

        assertEquals(anyBookingRequest.getRequester(),anyRequesterMock);
        assertEquals(anyBookingRequest.getState(),anyStateMock);
        assertEquals(anyBookingRequest.getTotalHours(),5,0);
        assertEquals(anyBookingRequest.getHoursOfTheReservation(),25,0);
    }
}
