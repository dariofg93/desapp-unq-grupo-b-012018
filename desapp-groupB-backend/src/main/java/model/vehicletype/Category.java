package model.vehicletype;

import java.io.Serializable;

public abstract class Category implements Serializable {
	
	public static Category scooter() {
		return new Scooter();
	}
	
	public static Category car() {
		return new Car();
	}


    public Boolean isSame(Category anyCategory) {
        return this.getName().equals(anyCategory.getName());
    }

	public abstract String getName();
	   
}
