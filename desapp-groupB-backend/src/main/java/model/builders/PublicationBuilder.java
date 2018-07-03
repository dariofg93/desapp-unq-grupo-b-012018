package model.builders;


import org.joda.time.DateTime;

import model.city.City;
import model.maps.GeographicZoneDescription;
import model.publication.Publication;
import model.user.User;
import model.vehicle.Vehicle;

public class PublicationBuilder {

	private Publication buildPublication;

	public Publication build() {
		return this.buildPublication;
	}

	public PublicationBuilder createPublication() {
		this.buildPublication = new Publication();
		return this;
	}

	public Publication createPublicationForUserAndVehicle(User anUser, Vehicle aVehicle, City city, String stringDateFrom, String stringDateTo, Double price) {		
		DateTime fromDate;
		DateTime toDate;
		GeographicZoneDescription pickUpZone;
		GeographicZoneDescription dropZone;

		fromDate = DateTime.parse(stringDateFrom);
		toDate = DateTime.parse(stringDateTo);
		pickUpZone = new GeographicZoneDescription( -34.706189, -58.277137);
		dropZone =  new GeographicZoneDescription( -34.690761, -58.30284);
		return new Publication(aVehicle, fromDate, toDate,anUser, city, pickUpZone, dropZone, price, 13454344);
	}
	
	public Publication createPublicationForUserAndVehicle(User anUser, Vehicle aVehicle, City city, String stringDateFrom, String stringDateTo, Double price, GeographicZoneDescription gpsInit, GeographicZoneDescription gpsFinish) {		
		DateTime fromDate;
		DateTime toDate;

		fromDate = DateTime.parse(stringDateFrom);
		toDate = DateTime.parse(stringDateTo);
		return new Publication(aVehicle, fromDate, toDate,anUser, city, gpsInit, gpsFinish, price, 13454344);
	}
	

}
