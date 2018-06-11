package dummies;

import model.builders.GeographicZoneDescriptionBuilder;
import model.maps.GeographicZoneDescription;
import persistence.generic.GenericService;

import java.util.ArrayList;
import java.util.List;

public class GeographicZoneDescriptionsDummy implements DummyData{

    private List<GeographicZoneDescription> zones = new ArrayList<>();
    private GeographicZoneDescriptionBuilder builder = new GeographicZoneDescriptionBuilder();
    private GenericService<GeographicZoneDescription> service;

    public GeographicZoneDescriptionsDummy(){
        GeographicZoneDescription zone1 = builder.createZone()
                .withLatitud(16.0)
                .withLongitud(18.9)
                .build();
        this.zones.add(zone1);

        GeographicZoneDescription zone2 = builder.createZone()
                .withLatitud(17.0)
                .withLongitud(20.0)
                .build();
        this.zones.add(zone2);

        GeographicZoneDescription zone3 = builder.createZone()
                .withLatitud(32.0)
                .withLongitud(18.9)
                .build();
        this.zones.add(zone3);

        GeographicZoneDescription zone4 = builder.createZone()
                .withLatitud(27.0)
                .withLongitud(20.0)
                .build();
        this.zones.add(zone4);
    }

    public void setGeographicZoneDescriptionBuilder(GeographicZoneDescriptionBuilder zoneBuilder) { this.builder= zoneBuilder; }
    public GeographicZoneDescriptionBuilder getGeographicZoneDescriptionBuilder() { return this.builder; }

    public void setZones(List<GeographicZoneDescription> zones) { this.zones = zones; }
    public List<GeographicZoneDescription> getZones() { return this.zones; }

    public void setService(GenericService<GeographicZoneDescription> service) {
        this.service = service;
    }
    public GenericService<GeographicZoneDescription> getService() {
        return this.service;
    }

    public void instantiateDataMock(){
        this.zones.forEach(
                (GeographicZoneDescription zone) -> this.service.save(zone)
        );
    }
}
