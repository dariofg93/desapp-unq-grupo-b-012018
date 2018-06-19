package persistence;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dummies.UsersDummy;
import model.booking.BookingRequest;
import model.bookingstate.AwaitingApproval;
import model.builders.BookingRequestBuilder;
import model.builders.UserBuilder;
import model.city.City;
import model.maps.GeographicZoneDescription;
import model.publication.Publication;
import model.user.User;
import model.vehicle.Vehicle;
import model.vehicletype.Category;
import service.publication.PublicationService;
import service.user.UserService;
import service.vehicle.VehicleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml",
		"/META-INF/spring-application-context.xml" })
public class PublicationRepositoryTest {

	@Autowired
	private PublicationService publicationService;
	@Autowired
	private UserService userService;
	@Autowired
	private VehicleService vehicleService;

	private Publication publication;
	private DateTime fromDate;
	private DateTime toDate;
	private Vehicle vehicle;
	private User user;
	private City city;
	private GeographicZoneDescription pickUpZone;
	private GeographicZoneDescription dropZone;

	@Before
	public void setUp() {
		this.cleanDatabase();

		publication = this.createPublication();

	}

	private void cleanDatabase() {
		publicationService.retriveAll().stream().forEach(user -> publicationService.delete(user));
	}

	@After
	public void tearDown() {
		this.cleanDatabase();
	}

	@Test
	public void testSave() {
		publicationService.save(publication);
		assertEquals(1, publicationService.retriveAll().size());
	}

	@Test
	public void testSaveAndRestorePublication() {

		publicationService.save(publication);
		Publication restoredPublication = publicationService.searchById(publication.getId());

		assertEquals(restoredPublication.getUser().getId(), user.getId());
		assertEquals(restoredPublication.getCity().getName(), city.getName());
		assertEquals(restoredPublication.getFromDate(), fromDate);
		assertEquals(restoredPublication.getToDate(), toDate);
		assertEquals(restoredPublication.getPublishedVehicle().getId(), vehicle.getId());
		assertEquals(restoredPublication.getPhone(), 13454344, 0);
		assertEquals(restoredPublication.getPricePerHour(), new Double(8.5));
		assertTrue(restoredPublication.allBookingRequest().isEmpty());

	}

	@Test
	public void testSaveAndRestorePublicationWithSomeBookingRequests() {
		BookingRequestBuilder builder = new BookingRequestBuilder();

		BookingRequest request = builder.createBookingRequest().withState(new AwaitingApproval())
				.withRequester(new UsersDummy().getUsers().get(2)).withTotalHours(5)
				.withDateTimeOfReservation(DateTime.now()).withHoursOfTheReservation(30).build();
		BookingRequest request2 = builder.createBookingRequest().withState(new AwaitingApproval())
				.withRequester(new UsersDummy().getUsers().get(2)).withTotalHours(5)
				.withDateTimeOfReservation(DateTime.now()).withHoursOfTheReservation(30).build();

		request2.acept();

		publication.addBookingRequest(request);
		publication.addBookingRequest(request2);
		publicationService.save(publication);
		Publication restoredPublication = publicationService.searchById(publication.getId());
		
		assertTrue(restoredPublication.allBookingRequest().contains(request2));
		assertTrue(restoredPublication.allBookingRequest().contains(request));

	}
	
	@Test
	public void testUpdatePublicationWithSomeBookingRequests() {
		
		publicationService.save(publication);
		
		assertTrue(publication.allBookingRequest().isEmpty());
		
		BookingRequestBuilder builder = new BookingRequestBuilder();

		BookingRequest request = builder.createBookingRequest().withState(new AwaitingApproval())
				.withRequester(new UsersDummy().getUsers().get(2)).withTotalHours(5)
				.withDateTimeOfReservation(DateTime.now()).withHoursOfTheReservation(30).build();
		BookingRequest request2 = builder.createBookingRequest().withState(new AwaitingApproval())
				.withRequester(new UsersDummy().getUsers().get(2)).withTotalHours(5)
				.withDateTimeOfReservation(DateTime.now()).withHoursOfTheReservation(30).build();


		Publication restoredPublication = publicationService.searchById(publication.getId());
				
		restoredPublication.addBookingRequest(request);
		restoredPublication.addBookingRequest(request2);
		
		publicationService.saveOrUpdate(restoredPublication);
		
		restoredPublication = publicationService.searchById(publication.getId());

		assertEquals(restoredPublication.allBookingRequest().size(), 2);
		assertTrue(restoredPublication.allBookingRequest().contains(request2));
		assertTrue(restoredPublication.allBookingRequest().contains(request));

	}

	@Test
	public void testSaveAndRestorePublicationForSomeSpecificUSer() {

		publicationService.save(publication);
		Publication restoredPublication = publicationService
				.selectByFunction((publication) -> publication.getUser().getId() == user.getId()).get(0);

		assertEquals(restoredPublication.getUser().getId(), user.getId());
		assertEquals(restoredPublication.getCity().getName(), city.getName());
		assertEquals(restoredPublication.getFromDate(), fromDate);
		assertEquals(restoredPublication.getToDate(), toDate);
		assertEquals(restoredPublication.getPublishedVehicle().getId(), vehicle.getId());
		assertEquals(restoredPublication.getPhone(), 13454344, 0);
		assertEquals(restoredPublication.getPricePerHour(), new Double(8.5));
		assertTrue(restoredPublication.allBookingRequest().isEmpty());

	}

	private Publication createPublication() {

		fromDate = DateTime.parse("2018-04-01");
		toDate = DateTime.parse("2018-05-01");
		vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0.", new ArrayList<BufferedImage>(), 5);
		vehicleService.save(vehicle);
		user = new UserBuilder().createUser().build();
		userService.save(user);
		city = new City("Wilde");
		pickUpZone = new GeographicZoneDescription(-58.302840100000026, -34.6907607);
		dropZone = new GeographicZoneDescription(-58.302840100000026, -34.6907607);
		return new Publication(vehicle, fromDate, toDate, user, city, pickUpZone, dropZone, new Double(8.5), 13454344);

	}

}
