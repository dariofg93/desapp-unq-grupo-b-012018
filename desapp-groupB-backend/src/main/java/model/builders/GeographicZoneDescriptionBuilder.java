package model.builders;

import model.maps.GeographicZoneDescription;

public class GeographicZoneDescriptionBuilder {

    private GeographicZoneDescription buildZone;

    public GeographicZoneDescriptionBuilder createZone() {
        this.buildZone = new GeographicZoneDescription();
        return this;
    }

    public GeographicZoneDescription build() {
        return this.buildZone;
    }

    public GeographicZoneDescriptionBuilder withLatitud(Double latitud) {
        this.buildZone.setLatitud(latitud);
        return this;
    }

    public GeographicZoneDescriptionBuilder withLongitud(Double longitud) {
        this.buildZone.setLongitud(longitud);
        return this;
    }
}
