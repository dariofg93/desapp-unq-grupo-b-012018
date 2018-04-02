package model.vehicle;

import model.vehicleType.VehicleType;

public class Vehicle {

    private VehicleType vehicleType;

    public Boolean itsType(VehicleType anyVehicleType) {
        return this.vehicleType.isSame(anyVehicleType);
    }
}
