package dummies;

import model.builders.VehicleBuilder;
import model.vehicle.Vehicle;
import model.vehicleType.Car;
import model.vehicleType.Scooter;
import service.VehicleService;

import java.util.ArrayList;
import java.util.List;

public class VehiclesDummy {

    private List<Vehicle> vehicles = new ArrayList<>();
    private VehicleBuilder builder = new VehicleBuilder();
    private VehicleService service;

    public VehiclesDummy(){
        Vehicle vehicle1 = builder.createVehicle()
                .withDescription("Un auto muy veloz!")
                .withPassengerCapacity(2)
                .withCategory(new Car())
                .withPictures(new ArrayList<>())
                .build();
        this.vehicles.add(vehicle1);

        Vehicle vehicle2 = builder.createVehicle()
                .withDescription("Moto 150cc, segura y muy potente para el tamaño de su motor")
                .withPassengerCapacity(1)
                .withCategory(new Scooter())
                .withPictures(new ArrayList<>())
                .build();
        this.vehicles.add(vehicle2);
    }

    public void setVehicleBuilder(VehicleBuilder vehicleBuilder) { this.builder= vehicleBuilder; }
    public VehicleBuilder getVehicleBuilder() { return this.builder; }

    public void setVehicles(List<Vehicle> vehicles) { this.vehicles = vehicles; }
    public List<Vehicle> getVehicles() { return this.vehicles; }

    public void setService(VehicleService service) {
        this.service = service;
    }
    public VehicleService getService() {
        return this.service;
    }

    public void instantiateDataMock(){
        this.vehicles.forEach(
                (Vehicle vehicle) -> this.service.save(vehicle)
        );
    }
}