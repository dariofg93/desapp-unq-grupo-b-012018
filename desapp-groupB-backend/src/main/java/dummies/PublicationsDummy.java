package dummies;

import java.util.ArrayList;

import java.util.List;

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
		
		try {
			this.publications.forEach((publication) -> this.service.save(publication));
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	private void initializeContext() {

			publications = new ArrayList<Publication>();
			builder = new PublicationBuilder();

			publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(0),
					vehicleService.retriveAll().get(0), new City("Wilde"), "2018-04-01", "2018-04-03",
					new Double(8.9)));
			
			publications.add(builder.createPublicationForUserAndVehicle(
					userService.retriveAll().get(userService.retriveAll().size()-1),
					vehicleService.retriveAll().get(vehicleService.retriveAll().size()-1), new City("Bernal"),
					"2017-04-01", "2017-04-03", new Double(8.6)));
			
			publications.add(builder.createPublicationForUserAndVehicle(userService.retriveAll().get(2),
					vehicleService.retriveAll().get(0), new City("La Plata"), "2017-12-01", "2018-12-03",
					new Double(8.6)));
		
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
