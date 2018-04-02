package model.publication;

import model.booking.BookingRequest;
import model.email.Email;
import model.locality.Locality;
import model.maps.GeographicZoneDescription;
import model.user.User;
import model.vehicle.Vehicle;
import org.joda.time.DateTime;
import org.joda.time.Hours;

import java.util.List;

public class Publication {

    private Double pricePerHour;
    private Integer phone;
    private User user;
    private DateTime fromDate;
    private DateTime toDate;
    private List<BookingRequest> requests;
    private Vehicle publishedVehicle;
    private Locality locality;
    private GeographicZoneDescription pickUpZone;
    private GeographicZoneDescription pickDownZone;
    private BookingRequest currentAprovedRequest;

    public Publication(){
        this.currentAprovedRequest = null;
    }

    public void addBookingRequest(BookingRequest anyRequest) {
        this.requests.add(anyRequest);
    }

    public Boolean containsRequest(BookingRequest anyRequest) {
        return requests.contains(anyRequest);
    }

    public Integer remainingTime() {
        if(this.currentAprovedRequest == null)
            return Hours.hoursBetween(new DateTime(),this.toDate).getHours();
        else
            return Hours.hoursBetween(this.currentAprovedRequest.endOfReservation(),this.toDate).getHours();
    }

    public Boolean isExpired() {
        return this.toDate.minusHours(1).isAfter(DateTime.now());
    }

    /** Setters and Getters **/

    public List<BookingRequest> getRequests() {
        return this.requests;
    }

    public Double getPricePerHour() {
        return this.pricePerHour;
    }

    public Vehicle getPublishedVehicle() {
        return publishedVehicle;
    }

    public void setCurrentAprovedRequest(BookingRequest request) {
        this.currentAprovedRequest = request;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public DateTime getFromDate() {
        return this.fromDate;
    }

    public DateTime getToDate() {
        return this.toDate;
    }

    public Locality getLocality() {
        return this.locality;
    }
}
