package model.publication;

import model.booking.BookingRequest;
import model.email.Email;
import model.locality.Locality;
import model.maps.GeographicZoneDescription;
import model.vehicle.Vehicle;

import java.util.Date;
import java.util.List;

public class Publication {

    private Double price;
    private Integer phone;
    private Email email;
    private Date fromDate;
    private Date toDate;
    private List<BookingRequest> requests;
    private Vehicle publishedVehicle;
    private Locality locality;
    private GeographicZoneDescription pickUpZone;
    private GeographicZoneDescription pickDownZone;

    public void addBookingRequest(BookingRequest anyRequest) {
        this.requests.add(anyRequest);
    }

    public Boolean containsRequest(BookingRequest anyRequest) {
        return requests.contains(anyRequest);
    }

    /** Setters and Getters **/

    public Email getEmail() {
        return this.email;
    }

    public List<BookingRequest> getRequests() {
        return this.requests;
    }

    public Double getPrice() {
        return this.price;
    }
}
