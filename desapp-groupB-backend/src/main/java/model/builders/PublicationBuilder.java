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
		pickUpZone = new GeographicZoneDescription(-58.302840100000026, -34.6907607);
		dropZone = new GeographicZoneDescription(-58.302840100000026, -34.6907607);
		return new Publication(aVehicle, fromDate, toDate,anUser, city, pickUpZone, dropZone, price, 13454344);
	}

}
