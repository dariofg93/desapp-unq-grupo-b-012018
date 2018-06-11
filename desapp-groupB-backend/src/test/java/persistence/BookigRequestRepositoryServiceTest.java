package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import model.booking.BookingRequest;
import model.builders.PublicationBuilder;
import model.city.City;
import model.publication.Publication;
import model.user.User;
import model.vehicle.Vehicle;
import model.vehicletype.Category;
import service.bookingrequest.BookingRequestService;
import service.publication.PublicationService;
import service.user.UserService;
import service.vehicle.VehicleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml",
		"/META-INF/spring-application-context.xml" })
public class BookigRequestRepositoryServiceTest {

	@Autowired
	private BookingRequestService bookingRequestService;
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private UserService userService;
	@Autowired
	private VehicleService vehicleService;

	private User user;
	private Vehicle vehicle;
	private Publication publication;

	@Before
	public void setUp() {
		this.cleanDatabase();
		initializeContext();
	}

	private void cleanDatabase() {
		bookingRequestService.retriveAll().stream().forEach(request -> bookingRequestService.delete(request));
		publicationService.retriveAll().stream().forEach(post -> publicationService.delete(post));
		vehicleService.retriveAll().stream().forEach(vehicle -> vehicleService.delete(vehicle));
		userService.retriveAll().stream().forEach(user -> userService.delete(user));
	}

	@After
	public void tearDown() {
		this.cleanDatabase();
	}

	@Test
	public void testSave() {

		BookingRequest bookingRequest = new BookingRequest();

		bookingRequestService.save(bookingRequest);
		assertEquals(1, bookingRequestService.retriveAll().size());
	}

	@Test
	public void testSaveAndRestore() {

		DateTime date = DateTime.parse("2018-04-01");

		BookingRequest bookingRequest = new BookingRequest();

		bookingRequest.setMyPublication(publication);
		bookingRequest.setRequester(user);
		bookingRequest.setAcepted();
		bookingRequest.setHoursOfTheReservation(30);
		bookingRequest.setReservationDateTime(date);
		bookingRequest.setTotalHours(45);

		bookingRequestService.save(bookingRequest);
		BookingRequest restoredBookingRequest = bookingRequestService.searchById(bookingRequest.getId());
		
		assertEquals(restoredBookingRequest.getRequester().getId(), user.getId());
		assertEquals(restoredBookingRequest.getMyPublication().getId(), publication.getId());
		assertTrue(restoredBookingRequest.isApproved());
		assertEquals(restoredBookingRequest.getTotalHours(), 45, 0);
		assertEquals(restoredBookingRequest.getHoursOfTheReservation(), 30, 0);
		assertEquals(date, restoredBookingRequest.getReservationDateTime());
	}
	
	@Test
	public void testSaveAndRestoreAlotOfBookingRequestForSamePublication() {

		DateTime date = DateTime.parse("2018-04-01");

		BookingRequest bookingRequest = new BookingRequest();

		bookingRequest.setMyPublication(publication);
		bookingRequest.setRequester(user);
		bookingRequest.setAcepted();
		bookingRequest.setHoursOfTheReservation(30);
		bookingRequest.setReservationDateTime(date);
		bookingRequest.setTotalHours(45);

		BookingRequest anotherBookingRequest = new BookingRequest();

		anotherBookingRequest.setMyPublication(publication);
		anotherBookingRequest.setRequester(user);
		anotherBookingRequest.setAcepted();
		anotherBookingRequest.setHoursOfTheReservation(30);
		anotherBookingRequest.setReservationDateTime(DateTime.parse("2015-04-01"));
		anotherBookingRequest.setTotalHours(15);
		
		bookingRequestService.save(bookingRequest);
		bookingRequestService.save(anotherBookingRequest);
		
		BookingRequest restoredBookingRequest = bookingRequestService.searchById(bookingRequest.getId());
		BookingRequest restoredAnotherBookingRequest = bookingRequestService.searchById(anotherBookingRequest.getId());
		
		assertEquals(restoredBookingRequest.getRequester().getId(), user.getId());
		assertEquals(restoredBookingRequest.getMyPublication().getId(), publication.getId());
		
		assertEquals(restoredAnotherBookingRequest.getRequester().getId(), user.getId());
		assertEquals(restoredAnotherBookingRequest.getMyPublication().getId(), publication.getId());
	}
	
	@Test
	public void testSaveAndRestoreBookingREquestsForDifferentsPublication() {

		DateTime date = DateTime.parse("2018-04-01");
		
		PublicationBuilder builder = new PublicationBuilder();
		Publication anotherPublication = builder.createPublicationForUserAndVehicle(user,vehicle, new City("Wilde"), "2015-07-01", "2015-10-03", new Double(8.9));
		publicationService.save(anotherPublication);

		BookingRequest bookingRequest = new BookingRequest();

		bookingRequest.setMyPublication(publication);
		bookingRequest.setRequester(user);
		bookingRequest.setAcepted();
		bookingRequest.setHoursOfTheReservation(30);
		bookingRequest.setReservationDateTime(date);
		bookingRequest.setTotalHours(45);

		BookingRequest anotherBookingRequest = new BookingRequest();

		anotherBookingRequest.setMyPublication(anotherPublication);
		anotherBookingRequest.setRequester(user);
		anotherBookingRequest.setAcepted();
		anotherBookingRequest.setHoursOfTheReservation(30);
		anotherBookingRequest.setReservationDateTime(DateTime.parse("2015-04-01"));
		anotherBookingRequest.setTotalHours(15);
		
		bookingRequestService.save(bookingRequest);
		bookingRequestService.save(anotherBookingRequest);
		
		BookingRequest restoredBookingRequest = bookingRequestService.searchById(bookingRequest.getId());
		BookingRequest restoredAnotherBookingRequest = bookingRequestService.searchById(anotherBookingRequest.getId());
		
		assertEquals(restoredBookingRequest.getRequester().getId(), user.getId());
		assertEquals(restoredBookingRequest.getMyPublication().getId(), publication.getId());
		
		assertEquals(restoredAnotherBookingRequest.getRequester().getId(), user.getId());
		assertEquals(restoredAnotherBookingRequest.getMyPublication().getId(), anotherPublication.getId());
	}

	private void initializeContext() {
		user = new User();
		userService.save(user);

		vehicle = new Vehicle(Category.car(), "Auto grande y espacioso. Motor 2.0.", new ArrayList<BufferedImage>(), 5,
				user);
		vehicleService.save(vehicle);

		PublicationBuilder builder = new PublicationBuilder();
		publication = builder.createPublicationForUserAndVehicle(user,vehicle, new City("Wilde"), "2018-04-01", "2018-04-03", new Double(8.9));

		publicationService.save(publication);
	}

}
