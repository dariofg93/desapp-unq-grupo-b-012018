package model.publication;

import model.booking.BookingRequest;
import model.city.City;
import model.maps.GeographicZoneDescription;
import model.user.User;
import model.vehicle.Vehicle;
import org.joda.time.DateTime;
import org.joda.time.Hours;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Publication {

    private Double pricePerHour;
    private Integer phone;
    private User user;
    private DateTime fromDate;
    private DateTime toDate;
    private List<BookingRequest> requests;
    private Vehicle publishedVehicle;
    private City city;
	private GeographicZoneDescription pickUpZone;
    private GeographicZoneDescription dropZone;


    public Publication(Vehicle aVehicle, DateTime someDate, DateTime anotherDate, User aUser, City aCity,
			GeographicZoneDescription aZone, GeographicZoneDescription anotherZone, Double price, int phoneNumber) {
    	
    	publishedVehicle = aVehicle;
    	fromDate = someDate;
    	toDate = anotherDate;
    	user = aUser;
    	city = aCity;
    	pickUpZone = aZone;
    	dropZone = anotherZone;
    	pricePerHour = price;
    	phone = phoneNumber;
    	requests = new ArrayList<BookingRequest>();
	}

	public void addBookingRequest(BookingRequest anyRequest) {
        this.requests.add(anyRequest);
    }

    public Boolean containsRequest(BookingRequest anyRequest) {
        return requests.contains(anyRequest);
    }

    public Integer remainingTime() {
    	
    	/*
    	 * 
    	 * Aca lo que se tiene que hacer es buscar entre los booking request cual esta aprobada para dateTime.now()
    	 * y a esa  es la currentAprovedReques
    	 * y despues es solo hacer lo que hace esta linea
    	 * 
    	 * return Hours.hoursBetween(this.currentAprovedRequest.endOfReservation(),this.toDate).getHours()
        
        
        
        if(this.currentAprovedRequest == null)
            return Hours.hoursBetween(new DateTime(),this.toDate).getHours();
        else
            return Hours.hoursBetween(this.currentAprovedRequest.endOfReservation(),this.toDate).getHours();
            */
    	return 0;
    }

    public Boolean isExpired() {
        return this.toDate.minusHours(1).isAfter(DateTime.now());
    }

    /** Setters and Getters **/

    public Double getPricePerHour() {
        return this.pricePerHour;
    }

    public Vehicle getPublishedVehicle() {
        return publishedVehicle;
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

    public City getCity() {
        return this.city;
    }

	public Integer getPhone() {
		return phone;
	}
	
    public GeographicZoneDescription getPickUpZone() {
		return pickUpZone;
	}

	public GeographicZoneDescription getDropZone() {
		return dropZone;
	}
	
	public List<BookingRequest> allBookingRequest(){
		return requests;
	}
	
	public BookingRequest approvedRequestForDate(DateTime date) {
		List<BookingRequest> aproovedRequests = 
					requests.stream()
						.filter(request -> request.isApproved() && 
										   (request.getDateTimeOfReservation().isBefore(date) 
												   && request.endOfReservation().isAfter(date)))
						.collect(Collectors.toList());
		
	}
}
