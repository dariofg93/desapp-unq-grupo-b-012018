package model.vehicle;

import model.vehicleType.Category;

public class Vehicle {

    private Category category;

    public Boolean itsCategory(Category anyCategory) {
        return this.category.isSame(category);
    }
}
