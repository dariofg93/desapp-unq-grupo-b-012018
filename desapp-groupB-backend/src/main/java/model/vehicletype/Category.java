package model.vehicletype;

public abstract class Category {
	
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
