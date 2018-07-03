package dummies;

import java.util.ArrayList;

import java.util.List;

import org.joda.time.DateTime;

import model.booking.BookingRequest;
import model.builders.BookingRequestBuilder;
import model.builders.PublicationBuilder;

import model.city.City;
import model.maps.GeographicZoneDescription;
import model.user.User;
import model.publication.Publication;

import service.publication.PublicationService;
import service.user.UserService;
import service.vehicle.VehicleService;

public class PublicationsDummy implements DummyData {

	private List<Publication> publications;
	private PublicationBuilder builder;
	private BookingRequestBuilder requestBuilder;
	private PublicationService service;
	private VehicleService vehicleService;
	private UserService userService;

	public void instantiateDataMock() {

		this.initializeContext();

		this.publications.forEach((publication) -> this.service.save(publication));

	}

	private void initializeContext() {
		publications = new ArrayList<Publication>();
		requestBuilder = new BookingRequestBuilder();
		
		builder = new PublicationBuilder();
		
		Publication publication = builder.createPublicationForUserAndVehicle(userService.retriveAll().get(0),
				vehicleService.retriveAll().get(0), new City("Wilde"), "2018-04-01", "2018-04-03", new Double(8.9),  new GeographicZoneDescription( -34.7065578, -58.274670000000015),  new GeographicZoneDescription( -34.690765, -58.30288));
		
		publication.addBookingRequest(createStartedBooking(userService.retriveAll().get(2),20,new DateTime(2018,2,15,5,28)));
		publication.addBookingRequest(createBooking(userService.retriveAll().get(1),40));
		publications.add(publication);

		publication = builder.createPublicationForUserAndVehicle(userService.searchById((long) 2),
				vehicleService.retriveAll().get(0), new City("La Plata"), "2018-12-01", "2018-12-03", new Double(8.6),  new GeographicZoneDescription( -34.706180, -58.277138),  new GeographicZoneDescription( -34.690755, -58.30288));
		
		publication.addBookingRequest(createBooking(userService.retriveAll().get(0),40));
		publications.add(publication);
				
		publication = builder.createPublicationForUserAndVehicle(userService.retriveAll().get(2),
				vehicleService.retriveAll().get(2), new City("Bernal"), "2018-09-04", "2018-12-03", new Double(15.6));
		publication.addBookingRequest(createStartedBooking(userService.retriveAll().get(0),20,new DateTime(2018,2,15,10,41)));

		publications.add(publication);
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(7),
				vehicleService.retriveAll().get(0), new City("Bernal"), "2018-08-01", "2018-12-03", new Double(3.4) ,new GeographicZoneDescription( -34.71038438832075, -58.27890745744082),  new GeographicZoneDescription( -34.71159442040434, -58.276281039159244)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(4),
				vehicleService.retriveAll().get(1), new City("Bernal"), "2018-11-01", "2018-12-03", new Double(6.0) ,new GeographicZoneDescription( -34.70772437150583, -58.27532831930296),  new GeographicZoneDescription(-34.70879332724433, -58.273173969808)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(3),
				vehicleService.retriveAll().get(2), new City("Bernal"), "2018-11-01", "2018-12-03", new Double(14.6) ,new GeographicZoneDescription( -34.71038438832075, -58.27890745644082),  new GeographicZoneDescription( -34.71159442040434, -58.276281036159244)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(2),
				vehicleService.retriveAll().get(3), new City("Bernal"), "2018-07-14", "2018-12-03", new Double(10.6),new GeographicZoneDescription( -34.70748094365084, -58.27822939814507),  new GeographicZoneDescription( -34.70826767046572, -58.27884308835348)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(6),
				vehicleService.retriveAll().get(6), new City("Bernal"), "2018-09-01", "2018-12-03", new Double(7.3),new GeographicZoneDescription( -34.70997163177894, -58.270481261033524),  new GeographicZoneDescription( -34.70997163177894, -58.2693783374724)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(7),
				vehicleService.retriveAll().get(7), new City("Bernal"), "2018-10-01", "2018-12-03", new Double(11.5),new GeographicZoneDescription( -34.70489844997246, -58.27214208799876),  new GeographicZoneDescription( -34.70399064257516, -58.275095994311755)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(3),
				vehicleService.retriveAll().get(8), new City("Bernal"), "2018-09-01", "2018-12-03", new Double(1.5),new GeographicZoneDescription( -34.706174420467235, -58.28448661589891),  new GeographicZoneDescription( -34.70525008014874, -58.28668817384414)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(1),
				vehicleService.retriveAll().get(5), new City("Bernal"), "2018-11-05", "2018-12-03", new Double(21.5),new GeographicZoneDescription( -34.70457269438346, -34.70457269438346),  new GeographicZoneDescription( -34.71159442040434, -58.276281039159244)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(4),
				vehicleService.retriveAll().get(9), new City("Bernal"), "2018-07-05", "2018-12-03", new Double(11.45),new GeographicZoneDescription( -34.71038438832075, -58.28833183231427),  new GeographicZoneDescription( -34.7062943717651, -58.290984001374)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(3),
				vehicleService.retriveAll().get(11), new City("Bernal"), "2018-03-05", "2018-12-03", new Double(5.5),new GeographicZoneDescription( -34.70821004855774, -58.289709416435926),  new GeographicZoneDescription( -34.70878509562021, -58.288391916153614)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(5),
				vehicleService.retriveAll().get(10), new City("Bernal"), "2018-09-24", "2018-12-03", new Double(12.5),new GeographicZoneDescription( -34.71142647875252, -58.28232407663694),  new GeographicZoneDescription( -34.712672629961325, -58.27978849411011)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(4),
				vehicleService.retriveAll().get(2), new City("Bernal"), "2018-01-24", "2018-12-03", new Double(14.56),new GeographicZoneDescription( -34.7118347860892, -58.27393486848189),  new GeographicZoneDescription( -34.71159442040434, -58.276281039159244)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(8),
				vehicleService.retriveAll().get(3), new City("Bernal"), "2018-07-04", "2018-12-03", new Double(6.5),new GeographicZoneDescription( -34.71038438832075, -58.27890745744082),  new GeographicZoneDescription( -34.71159442040434, -58.276281039159244)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(7),
				vehicleService.retriveAll().get(4), new City("Bernal"), "2018-01-14", "2018-12-03", new Double(52.5),new GeographicZoneDescription( -34.71038438832075, -58.27890745744082),  new GeographicZoneDescription( -34.71159442040434, -58.276281039159244)));
		publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(4),
				vehicleService.retriveAll().get(6), new City("Bernal"), "2018-02-15", "2018-12-03", new Double(15.5),new GeographicZoneDescription( -34.71038438832075, -58.27890745744082),  new GeographicZoneDescription( -34.71159442040434, -58.276281039159244)));
		publications.add(builder.createPublicationForUserAndVehicle(
				userService.retriveAll().get(userService.retriveAll().size() - 1),
				vehicleService.retriveAll().get(vehicleService.retriveAll().size() - 1), new City("Bernal"),
				"2018-04-01", "2018-04-03", new Double(8.6)));
	}

	private BookingRequest createBooking(User requester, Integer totalHours) {
		return requestBuilder.createBookingRequest()
      .withRequester(requester)
      .withTotalHours(totalHours)
      .build();
	}

	private BookingRequest createCancelBooking(User requester, Integer totalHours) {
		BookingRequest request = requestBuilder.createBookingRequest()
      .withRequester(requester)
      .withTotalHours(totalHours)
      .build();

    request.reject();
    return request;
	}

	private BookingRequest createStartedBooking(User requester, Integer totalHours, DateTime reservationDate) {
		BookingRequest request = requestBuilder.createBookingRequest()
      .withRequester(requester)
      .withTotalHours(totalHours)
      .withDateTimeOfReservation(reservationDate)
      .build();

    request.acept();
    return request;
	}

	private BookingRequest createFinishedBooking(User requester, Integer totalHours, DateTime reservationDate, Integer reservationHours) {
		BookingRequest request = requestBuilder.createBookingRequest()
      .withRequester(requester)
      .withTotalHours(totalHours)
      .withDateTimeOfReservation(reservationDate)
      .withHoursOfTheReservation(reservationHours)
      .build();

    request.acept();
    return request;
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
