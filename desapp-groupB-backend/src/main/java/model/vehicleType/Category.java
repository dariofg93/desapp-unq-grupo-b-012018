package model.vehicleType;

public abstract class Category {

    protected String name;

    public abstract Boolean isSame(Category anyCategory);

    /** Setters and Getters **/

    public String getName() {
        return name;
    }
}
