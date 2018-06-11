package dummies;

import java.util.ArrayList;

import java.util.List;

import org.joda.time.DateTime;

import model.booking.BookingRequest;
import model.builders.BookingRequestBuilder;
import model.builders.PublicationBuilder;

import model.city.City;
import model.publication.Publication;

import service.publication.PublicationService;
import service.user.UserService;
import service.vehicle.VehicleService;

public class PublicationsDummy implements DummyData {

	private List<Publication> publications;
	private PublicationBuilder builder;
	private PublicationService service;
	private VehicleService vehicleService;
	private UserService userService;

	public void instantiateDataMock() {

		this.initializeContext();

		this.publications.forEach((publication) -> this.service.save(publication));

	}

	private void initializeContext() {

		publications = new ArrayList<Publication>();
		BookingRequestBuilder requestBuilder = new BookingRequestBuilder();
		
		builder = new PublicationBuilder();
		
		BookingRequest b = createBookingUsing(requestBuilder);
		b.setAcepted();

		Publication publication = builder.createPublicationForUserAndVehicle(userService.retriveAll().get(0),
				vehicleService.retriveAll().get(0), new City("Wilde"), "2018-04-01", "2018-04-03", new Double(8.9));
		publication.addBookingRequest(b);
		publication.addBookingRequest(createBookingRequestUsing(requestBuilder));
		publications.add(publication);

		publications.add(builder.createPublicationForUserAndVehicle(
				userService.retriveAll().get(userService.retriveAll().size() - 1),
				vehicleService.retriveAll().get(vehicleService.retriveAll().size() - 1), new City("Bernal"),
				"2017-04-01", "2017-04-03", new Double(8.6)));

		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(2),
				vehicleService.retriveAll().get(0), new City("La Plata"), "2017-12-01", "2018-12-03", new Double(8.6)));

	}

	private BookingRequest createBookingRequestUsing(BookingRequestBuilder requestBuilder) {
		return requestBuilder.createBookingRequest()
		        .withRequester(new UsersDummy().getUsers().get(1))
		        .withTotalHours(20)
		        .withDateTimeOfReservation(new DateTime(2017,2,15,0,0))
		        .withHoursOfTheReservation(81)
		        .build();
	}

	private BookingRequest createBookingUsing(BookingRequestBuilder requestBuilder) {
		return requestBuilder.createBookingRequest()
        .withRequester(new UsersDummy().getUsers().get(2))
        .withTotalHours(20)
        .withDateTimeOfReservation(new DateTime(2018,2,15,0,0))
        .withHoursOfTheReservation(21)
        .build();
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public PublicationBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(PublicationBuilder builder) {
		this.builder = builder;
	}

	public PublicationService getService() {
		return service;
	}

	public void setService(PublicationService service) {
		this.service = service;
	}

	public VehicleService getVehicleService() {
		return vehicleService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
