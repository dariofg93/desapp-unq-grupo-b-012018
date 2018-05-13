package service.generic;

import service.vehicle.VehicleService;

public class GeneralService {

		private VehicleService vehicleService;
		
		public VehicleService getVehicleService() {
			return vehicleService;
		}

		public void setVehicleService(final VehicleService vehicleService) {
			this.vehicleService = vehicleService;
		}

}