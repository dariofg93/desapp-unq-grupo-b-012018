package model.publication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.joda.time.DateTime;

import model.booking.BookingRequest;
import model.city.City;
import model.exceptions.BookingNotFoundException;
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
    	toDate = DateTime.now().plusHours(24);
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
		
		User anotherUser = mock(User.class);
		publication.setUser(anotherUser);
		
		assertEquals(publication.getUser(), anotherUser);
		
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
		DateTime someDate = new DateTime(2018, 4, 8, 12, 0, 0, 0);
		
		BookingRequest bookingRequest = mock(BookingRequest.class);
		BookingRequest bookingRequest2 = mock(BookingRequest.class);
		
		publication.addBookingRequest(bookingRequest);
		publication.addBookingRequest(bookingRequest2);
		
		when(bookingRequest2.isApproved()).thenReturn(true);
		when(bookingRequest.isApproved()).thenReturn(false);
		
		when(bookingRequest2.getReservationDateTime()).thenReturn(new DateTime(2018, 2, 10, 12, 0, 0, 0));
		when(bookingRequest2.endOfReservation()).thenReturn(new DateTime(2018, 4, 10, 12, 0, 0, 0));
		
		when(bookingRequest.endOfReservation()).thenReturn(new DateTime(2016, 4, 10, 12, 0, 0, 0));
		when(bookingRequest.getReservationDateTime()).thenReturn(new DateTime(2016, 4, 10, 12, 0, 0, 0));
		
		assertEquals(publication.approvedRequestsForDate(someDate).get(0), bookingRequest2);
	}
	
	@Test
	public void testPublicationIsUnavailableForSomeDate() {
		DateTime someDate = new DateTime(2018, 4, 8, 12, 0, 0, 0);
		
		BookingRequest bookingRequest = mock(BookingRequest.class);
		BookingRequest bookingRequest2 = mock(BookingRequest.class);
		
		assertTrue(publication.isAvailableFor(someDate));
		
		publication.addBookingRequest(bookingRequest);
		publication.addBookingRequest(bookingRequest2);
		
		when(bookingRequest2.isApproved()).thenReturn(true);
		when(bookingRequest.isApproved()).thenReturn(false);
		
		when(bookingRequest2.getReservationDateTime()).thenReturn(new DateTime(2018, 2, 10, 12, 0, 0, 0));
		when(bookingRequest2.endOfReservation()).thenReturn(new DateTime(2018, 4, 10, 12, 0, 0, 0));
		
		when(bookingRequest.endOfReservation()).thenReturn(new DateTime(2016, 4, 10, 12, 0, 0, 0));
		when(bookingRequest.getReservationDateTime()).thenReturn(new DateTime(2016, 4, 10, 12, 0, 0, 0));
		
		assertFalse(publication.isAvailableFor(someDate));
	}
	
	@Test
	public void testPublicationIsAvailableForSomeDAte () {
		DateTime someDate = new DateTime(2018, 4, 8, 12, 0, 0, 0);
		
		BookingRequest bookingRequest = mock(BookingRequest.class);
		BookingRequest bookingRequest2 = mock(BookingRequest.class);
		
		assertTrue(publication.isAvailableFor(someDate));
		
		publication.addBookingRequest(bookingRequest);
		publication.addBookingRequest(bookingRequest2);
		
		when(bookingRequest2.isApproved()).thenReturn(false);
		when(bookingRequest.isApproved()).thenReturn(false);
		
	
		assertTrue(publication.isAvailableFor(someDate));
	}
	
	@Test
	public void testExistCurrentBookingRequestInPublication() throws BookingNotFoundException {

		
		BookingRequest bookingRequest2 = mock(BookingRequest.class);
		
		publication.addBookingRequest(bookingRequest2);
		
		when(bookingRequest2.isApproved()).thenReturn(true);
			
		when(bookingRequest2.getReservationDateTime()).thenReturn(DateTime.now().minusDays(3));
		when(bookingRequest2.endOfReservation()).thenReturn(DateTime.now().plusDays(1));
			
		assertEquals(publication.currentAprovedRequest(), bookingRequest2);
	}
	
	@Test(expected = BookingNotFoundException.class)
	public void testNotExistCurrentBookingRequestInPublication() throws BookingNotFoundException {

		
		BookingRequest bookingRequest2 = mock(BookingRequest.class);
		
		publication.addBookingRequest(bookingRequest2);
		
		when(bookingRequest2.isApproved()).thenReturn(true);
			
		when(bookingRequest2.endOfReservation()).thenReturn(new DateTime(2016, 4, 10, 12, 0, 0, 0));
		when(bookingRequest2.getReservationDateTime()).thenReturn(new DateTime(2016, 4, 10, 12, 0, 0, 0));
			
		publication.currentAprovedRequest();
	}
	
	@Test
	public void testValidateExpirationDate(){

		Publication anotherPublication = new Publication(vehicle, fromDate, DateTime.now().plusDays(9), user, city, pickUpZone , dropZone, new Double(8.5), 13454344);
	
		assertTrue(anotherPublication.isExpired());
	}
	
	

	
	@Test
	public void testValidateRemainingTimeWhenPublicationIsExpired(){

		BookingRequest bookingRequest2 = mock(BookingRequest.class);
		Publication anotherPublication = new Publication(vehicle, fromDate, DateTime.now().plusDays(9), user, city, pickUpZone , dropZone, new Double(8.5), 13454344);
	
		
		anotherPublication.addBookingRequest(bookingRequest2);
		
		when(bookingRequest2.isApproved()).thenReturn(true);
			
		when(bookingRequest2.getReservationDateTime()).thenReturn(DateTime.now().minusDays(3));
		when(bookingRequest2.endOfReservation()).thenReturn(DateTime.now().minusDays(1));
				
		assertEquals(anotherPublication.remainingTime(), new Integer(0));
	}
	
	private void initalizeMock() {
		
		pickUpZone = mock(GeographicZoneDescription.class);
		dropZone = mock(GeographicZoneDescription.class);
	    vehicle = mock(Vehicle.class);
	    city = mock(City.class);
	    user = mock(User.class);
	}

}
