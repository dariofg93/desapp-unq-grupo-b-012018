package publication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.joda.time.DateTime;

import model.booking.BookingRequest;
import model.city.City;
import model.maps.GeographicZoneDescription;
import model.publication.Publication;
import model.user.User;
import model.vehicle.Vehicle;

public class PublicationTest {
	
	private Publication publication;
	private GeographicZoneDescription pickUpZone;
	private GeographicZoneDescription dropZone;
    private Vehicle vehicle;
    private City city;
    private User user;
	private DateTime fromDate;
	private DateTime toDate;	
    
    @Before
    public void setUp() {
    	this.initalizeMock();
    	fromDate = DateTime.now();
    	toDate = DateTime.now();
    	publication = new Publication(vehicle, fromDate, toDate, user, city, pickUpZone , dropZone, new Double(8.5), 13454344);
    }
    


	@Test
	public void testCreatePublication() {
		assertEquals(publication.getUser(), user);
		assertEquals(publication.getCity(), city);
		assertEquals(publication.getFromDate(), fromDate);
		assertEquals(publication.getToDate(), toDate);
		assertEquals(publication.getPublishedVehicle(), vehicle);
		assertEquals(publication.getPhone(), 13454344, 0);
		assertEquals(publication.getPricePerHour(), new Double(8.5));
		assertEquals(publication.getDropZone(), dropZone);
		assertEquals(publication.getPickUpZone(), pickUpZone);
		assertTrue(publication.allBookingRequest().isEmpty());
		
	}
	
	@Test
	public void testAddRequest() {
		BookingRequest bookingRequest = mock(BookingRequest.class);
		
		assertTrue(publication.allBookingRequest().isEmpty());
		
		publication.addBookingRequest(bookingRequest);
		
		assertFalse(publication.allBookingRequest().isEmpty());
		assertTrue(publication.containsRequest(bookingRequest));
	}
	
	@Test
	public void testGetAproovedRequestForSomeDate() {
		BookingRequest bookingRequest = mock(BookingRequest.class);
		BookingRequest bookingRequest2 = mock(BookingRequest.class);
		
		publication.addBookingRequest(bookingRequest);
		publication.addBookingRequest(bookingRequest2);
		
		assertEqual(publication.aproveedRequestForDate(DateTime.now()), bookingRequest);
	}
	
	
	private void initalizeMock() {
		
		pickUpZone = mock(GeographicZoneDescription.class);
		dropZone = mock(GeographicZoneDescription.class);
	    vehicle = mock(Vehicle.class);
	    city = mock(City.class);
	    user = mock(User.class);
	}

}
