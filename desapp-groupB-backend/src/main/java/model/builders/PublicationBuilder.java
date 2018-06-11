package model.builders;

import model.booking.BookingRequest;
import model.city.City;
import model.maps.GeographicZoneDescription;
import model.publication.Publication;
import model.user.User;
import model.vehicle.Vehicle;
import org.joda.time.DateTime;

import java.util.ArrayList;

public class PublicationBuilder {

    private Publication buildPublication;

    public Publication build() {
        return this.buildPublication;
    }

    public PublicationBuilder createPublication() {
        this.buildPublication = new Publication();
        return this;
    }

    public PublicationBuilder withPricePerHour(Double pricePerHour) {
        this.buildPublication.setPricePerHour(pricePerHour);
        return this;
    }

    public PublicationBuilder withPhone(Integer phone) {
        this.buildPublication.setPhone(phone);
        return this;
    }

    public PublicationBuilder withUser(User user) {
        this.buildPublication.setUser(user);
        return this;
    }

    public PublicationBuilder withFromDate(DateTime fromDate) {
        this.buildPublication.setFromDate(fromDate);
        return this;
    }

    public PublicationBuilder withToDate(DateTime toDate) {
        this.buildPublication.setToDate(toDate);
        return this;
    }

    public PublicationBuilder withRequests(ArrayList<BookingRequest> bookingRequests) {
        this.buildPublication.setRequests(bookingRequests);
        return this;
    }

    public PublicationBuilder withVehicle(Vehicle vehicle) {
        this.buildPublication.setPublishedVehicle(vehicle);
        return this;
    }

    public PublicationBuilder withCity(City city) {
        this.buildPublication.setCity(city);
        return this;
    }

    public PublicationBuilder withPickUpZone(GeographicZoneDescription pickUpZone) {
        this.buildPublication.setPickUpZone(pickUpZone);
        return this;
    }

    public PublicationBuilder withDropZone(GeographicZoneDescription dropZone) {
        this.buildPublication.setDropZone(dropZone);
        return this;
    }
}
