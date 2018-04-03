package model.builders;

import model.filter.ByLocality;
import model.city.City;
import model.order.Order;

public class ByLocalityBuilder {

    private ByLocality buildByLocality;

    public ByLocalityBuilder createFilterByLocality() {
        this.buildByLocality = new ByLocality();
        return this;
    }

    public ByLocality build() {
        return this.buildByLocality;
    }

    public ByLocalityBuilder withOrder(Order anyOrder) {
        this.buildByLocality.setOrder(anyOrder);
        return this;
    }

    public ByLocalityBuilder withLocality(City anyCity) {
        this.buildByLocality.setCity(anyCity);
        return this;
    }
}
