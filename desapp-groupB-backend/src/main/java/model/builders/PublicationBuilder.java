package model.builders;

import java.util.List;

import org.joda.time.DateTime;

import model.booking.BookingRequest;
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
		/*
		 * Esto es una cosa muy fea. SI queda tiempo se refectoriza. No es parte de lo importante de la app.
		 */
		User someUser;
		if(anUser == null) {
			someUser = new User();
		}else {
			someUser = anUser;
		}
		/*
		 * Esto es una cosa muy fea. SI queda tiempo se refectoriza. No es parte de lo importante de la app.
		 */
		
		DateTime fromDate;
		DateTime toDate;
		GeographicZoneDescription pickUpZone;
		GeographicZoneDescription dropZone;

		fromDate = DateTime.parse(stringDateFrom);
		toDate = DateTime.parse(stringDateTo);
		pickUpZone = new GeographicZoneDescription(-58.302840100000026, -34.6907607);
		dropZone = new GeographicZoneDescription(-58.302840100000026, -34.6907607);
		return new Publication(aVehicle, fromDate, toDate,someUser, city, pickUpZone, dropZone, price, 13454344);
	}

}
