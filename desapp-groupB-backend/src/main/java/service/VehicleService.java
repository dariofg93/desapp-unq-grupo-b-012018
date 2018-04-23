package service;

import model.vehicle.Vehicle;
import persistence.GenericService;

public class VehicleService extends GenericService<Vehicle>{

	private static final long serialVersionUID = -2932116622242535843L;

	public Vehicle searchById(Integer id) {
		return this.retriveAll().stream()
				.filter(vehicle -> vehicle.getId().equals(id))
				.findFirst().get();
	}
}

