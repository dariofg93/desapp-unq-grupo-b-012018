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
		b.acept();

		Publication publication = builder.createPublicationForUserAndVehicle(userService.retriveAll().get(0),
				vehicleService.retriveAll().get(0), new City("Wilde"), "2018-04-01", "2018-04-03", new Double(8.9));
		publication.addBookingRequest(b);
		publication.addBookingRequest(createBookingRequestUsing(requestBuilder));
		publications.add(publication);

		publications.add(builder.createPublicationForUserAndVehicle(
				userService.retriveAll().get(userService.retriveAll().size() - 1),
				vehicleService.retriveAll().get(vehicleService.retriveAll().size() - 1), new City("Bernal"),
				"2018-04-01", "2018-04-03", new Double(8.6)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(2),
				vehicleService.retriveAll().get(0), new City("La Plata"), "2018-12-01", "2018-12-03", new Double(8.6)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(2),
				vehicleService.retriveAll().get(2), new City("Bernal"), "2018-09-04", "2018-12-03", new Double(15.6)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(7),
				vehicleService.retriveAll().get(0), new City("Bernal"), "2018-08-01", "2018-12-03", new Double(3.4)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(4),
				vehicleService.retriveAll().get(1), new City("Bernal"), "2018-11-01", "2018-12-03", new Double(6.0)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(3),
				vehicleService.retriveAll().get(2), new City("Bernal"), "2018-11-01", "2018-12-03", new Double(14.6)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(2),
				vehicleService.retriveAll().get(3), new City("Bernal"), "2018-07-14", "2018-12-03", new Double(10.6)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(6),
				vehicleService.retriveAll().get(6), new City("Bernal"), "2018-09-01", "2018-12-03", new Double(7.3)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(7),
				vehicleService.retriveAll().get(7), new City("Bernal"), "2018-10-01", "2018-12-03", new Double(11.5)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(3),
				vehicleService.retriveAll().get(8), new City("Bernal"), "2018-09-01", "2018-12-03", new Double(1.5)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(1),
				vehicleService.retriveAll().get(5), new City("Bernal"), "2018-11-05", "2018-12-03", new Double(21.5)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(4),
				vehicleService.retriveAll().get(9), new City("Bernal"), "2018-07-05", "2018-12-03", new Double(11.45)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(3),
				vehicleService.retriveAll().get(11), new City("Bernal"), "2018-03-05", "2018-12-03", new Double(5.5)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(5),
				vehicleService.retriveAll().get(10), new City("Bernal"), "2018-09-24", "2018-12-03", new Double(12.5)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(4),
				vehicleService.retriveAll().get(2), new City("Bernal"), "2018-01-24", "2018-12-03", new Double(14.56)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(8),
				vehicleService.retriveAll().get(3), new City("Bernal"), "2018-07-04", "2018-12-03", new Double(6.5)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(7),
				vehicleService.retriveAll().get(4), new City("Bernal"), "2018-01-14", "2018-12-03", new Double(52.5)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(4),
				vehicleService.retriveAll().get(6), new City("Bernal"), "2018-02-15", "2018-12-03", new Double(15.5)));

	}

	private BookingRequest createBookingRequestUsing(BookingRequestBuilder requestBuilder) {
		return requestBuilder.createBookingRequest()
		        .withRequester(new UsersDummy().getUsers().get(1))
		        .withTotalHours(20)
		        .withDateTimeOfReservation(new DateTime(2018,2,15,0,0))
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
