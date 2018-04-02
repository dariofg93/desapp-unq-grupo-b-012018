package model.vehicleType;

public class Scooter extends VehicleType{

    @Override
    public Boolean isSame(VehicleType anyVehicleType) {
        return this.name.equals(anyVehicleType.getName());
    }

    public Scooter(){
        this.name = "Scooter";
    }
}
