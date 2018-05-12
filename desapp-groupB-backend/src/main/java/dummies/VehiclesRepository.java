package dummies;

import model.builders.VehicleBuilder;
import model.vehicle.Vehicle;
import model.vehicleType.Car;
import model.vehicleType.Scooter;
import persistence.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

public class VehiclesRepository {

    private List<Vehicle> vehicles = new ArrayList<>();
    private VehicleBuilder builder = new VehicleBuilder();
    
    private VehicleRepository repository;

	public VehiclesRepository(VehicleRepository repository) {
  		this.repository = repository;
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
        
        this.vehicles.forEach((Vehicle vehicle) -> repository.save(vehicle));
    }
	
	public VehiclesRepository() {}

    public List<Vehicle> getVehicles() { return this.vehicles; }
    
    public VehicleRepository getRepository() {
  		return repository;
  	}

  	public void setRepository(VehicleRepository repository) {
  		this.repository = repository;
  	}
    
}