package service.vehicle;

import model.vehicle.Vehicle;
import persistence.generic.GenericService;

public class VehicleService extends GenericService<Vehicle>{

	private static final long serialVersionUID = -2932116622242535843L;

	public Vehicle searchById(Long id) {
		return this.getRepository().findById(id) ;
	}

	public void updateById(Long id, Vehicle updatedVehicle) {
		updatedVehicle.setId(id);
		this.getRepository().saveOrUpdate(updatedVehicle);
	}
}

