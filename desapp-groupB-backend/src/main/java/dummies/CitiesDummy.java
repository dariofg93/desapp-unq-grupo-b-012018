package dummies;

import model.builders.CityBuilder;
import model.city.City;
import persistence.generic.GenericService;

import java.util.ArrayList;
import java.util.List;

public class CitiesDummy implements DummyData{

    private List<City> cities = new ArrayList<>();
    private CityBuilder builder = new CityBuilder();
    private GenericService<City> service;

    public CitiesDummy(){
        City city1 = builder.createCity()
                .withName("Quilmes")
                .build();
        this.cities.add(city1);

        City city2 = builder.createCity()
                .withName("Bernal")
                .build();
        this.cities.add(city2);
    }

    public void setCityBuilder(CityBuilder cityBuilder) { this.builder= cityBuilder; }
    public CityBuilder getCityBuilder() { return this.builder; }

    public void setCities(List<City> cities) { this.cities = cities; }
    public List<City> getCities() { return this.cities; }

    public void setService(GenericService<City> service) {
        this.service = service;
    }
    public GenericService<City> getService() {
        return this.service;
    }

    public void instantiateDataMock(){
        this.cities.forEach(
                (City city) -> this.service.save(city)
        );
    }
}
