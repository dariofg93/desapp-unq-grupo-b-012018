package model.vehicleType;

public abstract class VehicleType {

    protected String name;

    public abstract Boolean isSame(VehicleType anyVehicleType);

    /** Setters and Getters **/

    public String getName() {
        return name;
    }
}
