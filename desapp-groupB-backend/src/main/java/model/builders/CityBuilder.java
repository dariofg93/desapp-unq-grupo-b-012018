package model.builders;

import model.city.City;

public class CityBuilder {

    private City buildCity;

    public CityBuilder createCity(){
        return this;
    }

    public City build() {
        return this.buildCity;
    }

    public CityBuilder withName(String name) {
        this.buildCity.setName(name);
        return this;
    }
}
