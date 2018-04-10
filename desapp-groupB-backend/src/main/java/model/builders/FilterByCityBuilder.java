package model.builders;

import model.filter.FilterByCity;
import model.city.City;
import model.order.Order;

public class FilterByCityBuilder {

    private FilterByCity buildByLocality;

    public FilterByCityBuilder createFilterByLocality() {
        this.buildByLocality = new FilterByCity();
        return this;
    }

    public FilterByCity build() {
        return this.buildByLocality;
    }

    public FilterByCityBuilder withOrder(Order anyOrder) {
        this.buildByLocality.setOrder(anyOrder);
        return this;
    }

    public FilterByCityBuilder withLocality(City anyCity) {
        this.buildByLocality.setCity(anyCity);
        return this;
    }
}
