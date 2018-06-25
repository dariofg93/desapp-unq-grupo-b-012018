package model.publication;

import model.booking.BookingRequest;
import model.city.City;
import model.exceptions.BookingNotFoundException;
import model.maps.GeographicZoneDescription;
import model.user.User;

import model.utils.Entity;
import model.vehicle.Vehicle;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import org.joda.time.DateTime;
import org.joda.time.Hours;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Publication extends Entity{

	private Double pricePerHour;
    private Integer phone;
    private User user;
    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "YYYY-MM-dd")
    private DateTime fromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "YYYY-MM-dd")
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

	public Publication() {
		this.requests = new ArrayList<BookingRequest>();
	}

	public void addBookingRequest(BookingRequest anyRequest) {
        this.requests.add(anyRequest);
    }

    public Boolean containsRequest(BookingRequest anyRequest) {
        return requests.contains(anyRequest);
    }

    public Integer remainingTime() {
    	Integer hours;
    	try {
    		hours = Hours.hoursBetween(this.currentAprovedRequest().endOfReservation(),this.toDate).getHours();
    	} catch (BookingNotFoundException ex) {
    		hours = Hours.ZERO.getHours();
    	}
    	
    	return hours;
    }

    public Boolean expired() {
        return this.toDate.minusHours(1).isAfter(DateTime.now());
    }

    /** Setters and Getters **/

    public Double getPricePerHour() {
        return this.pricePerHour;
    }

    public Vehicle getPublishedVehicle() {
        return publishedVehicle;
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
	

    public List<BookingRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<BookingRequest> requests) {
		this.requests = requests;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public void setFromDate(DateTime fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(DateTime toDate) {
		this.toDate = toDate;
	}

	public void setPublishedVehicle(Vehicle publishedVehicle) {
		this.publishedVehicle = publishedVehicle;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setPickUpZone(GeographicZoneDescription pickUpZone) {
		this.pickUpZone = pickUpZone;
	}

	public void setDropZone(GeographicZoneDescription dropZone) {
		this.dropZone = dropZone;
	}
	
	public List<BookingRequest> approvedRequestsForDate(DateTime date){
		
		// Delegar esto a la base de datos.
		
		return requests.stream()
						.filter(request -> 
						request.isApproved()
						&& 
										   (request.getReservationDateTime().isBefore(date) 
												   && request.endOfReservation().isAfter(date))
						)
						.collect(Collectors.toList());
		
	}

	public boolean isAvailableFor(DateTime someDate) {
		
		return this.approvedRequestsForDate(someDate).isEmpty();
	}
	
	public BookingRequest currentAprovedRequest() throws BookingNotFoundException{
		List<BookingRequest> maybeResult = this.approvedRequestsForDate(DateTime.now());
		if (maybeResult.isEmpty()) {
			throw new BookingNotFoundException();
 		}
		return maybeResult.get(0);
	}
	@JsonProperty
	public void setUser(User anUser) {
		user = anUser;
		
	}
	
	public static City fromCode(String name) {
		return new City(name);
	}
}
