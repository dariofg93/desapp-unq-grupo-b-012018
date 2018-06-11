package dummies;

import model.booking.BookingRequest;
import model.builders.PublicationBuilder;
import model.city.City;
import model.maps.GeographicZoneDescription;
import model.publication.Publication;
import model.user.User;
import model.vehicle.Vehicle;
import org.joda.time.DateTime;
import service.publication.PublicationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PublicationsDummy implements DummyData{

    private List<Publication> publications = new ArrayList<>();
    private PublicationBuilder builder = new PublicationBuilder();
    private PublicationService service;

    public PublicationsDummy(){
        List<User> users = new UsersDummy().getUsers();
        List<BookingRequest> requests = new RequestsDummy().getBookingRequests();
        List<Vehicle> vehicles = new VehiclesDummy().getVehicles();
        List<City> cities = new CitiesDummy().getCities();
        List<GeographicZoneDescription> zones = new GeographicZoneDescriptionsDummy().getZones();

        Publication publication1 = builder.createPublication()
                .withPricePerHour(60.0)
                .withPhone(1234567890)
                .withUser(users.get(0))
                .withFromDate(new DateTime(2018,1,1,0,0))
                .withToDate(new DateTime(2018,6,15,0,0))
                .withRequests(new ArrayList<>(Collections.singleton(requests.get(0))))
                .withVehicle(vehicles.get(5))
                .withCity(cities.get(0))
                .withPickUpZone(zones.get(0))
                .withDropZone(zones.get(1))
                .build();
        this.publications.add(publication1);

        Publication publication2 = builder.createPublication()
                .withPricePerHour(50.0)
                .withPhone(1234567890)
                .withUser(users.get(0))
                .withFromDate(new DateTime(2018,3,5,0,0))
                .withToDate(new DateTime(2018,7,15,0,0))
                .withRequests(new ArrayList<>())
                .withVehicle(vehicles.get(10))
                .withCity(cities.get(0))
                .withPickUpZone(zones.get(2))
                .withDropZone(zones.get(3))
                .build();
        this.publications.add(publication2);
    }

    public void setPublicationBuilder(PublicationBuilder publicationBuilder) { this.builder= publicationBuilder; }
    public PublicationBuilder getPublicationBuilder() { return this.builder; }

    public void setPublications(List<Publication> publications) { this.publications = publications; }
    public List<Publication> getPublications() { return this.publications; }

    public void setService(PublicationService service) {
        this.service = service;
    }
    public PublicationService getService() {
        return this.service;
    }

    public void instantiateDataMock(){
        this.publications.forEach(
                (Publication publication) -> this.service.save(publication)
        );
    }
}
