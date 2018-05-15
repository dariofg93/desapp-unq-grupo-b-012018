package model.maps;

import model.utils.Entity;

public class GeographicZoneDescription extends Entity {
	
	Double latitud;
	Double longitud;

	public GeographicZoneDescription(Double aLatitud, Double aLongitud) {
		latitud = aLatitud;
		longitud = aLongitud;
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

	public Boolean equalsTo(GeographicZoneDescription geographicZoneDescription) {
		return (geographicZoneDescription.getLatitud().doubleValue() == this.getLatitud().doubleValue()
				&& geographicZoneDescription.getLongitud().doubleValue() == this.getLongitud().doubleValue());

	}

}
