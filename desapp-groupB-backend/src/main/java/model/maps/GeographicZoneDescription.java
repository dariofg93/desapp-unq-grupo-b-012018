package model.maps;

import model.utils.Entity;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

public class GeographicZoneDescription extends Entity {

	private Double latitud;
	private Double longitud;
	private String description;

	public GeographicZoneDescription(Double aLatitud, Double aLongitud) {
		latitud = aLatitud;
		longitud = aLongitud;
		
		if(this.latitud != null && this.longitud != null && this.description ==null) {
			this.description = this.getAddressByGpsCoordinates(this.longitud.toString(), this.latitud.toString());
		}
	}

	public GeographicZoneDescription() {
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean equalsTo(GeographicZoneDescription geographicZoneDescription) {
		return (geographicZoneDescription.getLatitud().doubleValue() == this.getLatitud().doubleValue()
				&& geographicZoneDescription.getLongitud().doubleValue() == this.getLongitud().doubleValue());

	}

	private String getAddressByGpsCoordinates(String lng, String lat) {
	    Geocoder geocoder = new Geocoder();
	    LatLng location = new LatLng(lat,  lng);
	    String addressResult = "unknown address";
	    
	 
       GeocoderRequest request = new GeocoderRequest();
	        request.setLocation(location);

	        GeocodeResponse response = geocoder.geocode(request);
	        if (response.getStatus() == GeocoderStatus.OK) {
	           addressResult = response.getResults().get(0).getFormattedAddress();
	        }else {
	        	System.out.println("-------------------------------------------");
	        	System.out.println("Latitud: " + lat);
	        	System.out.println("onguitud: " + lng);
	        	System.out.println(response);
	        }
	    return addressResult;
	}

}
