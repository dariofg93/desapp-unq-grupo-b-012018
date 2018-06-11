package webService.vehiclecorcern;

import model.vehicle.Vehicle;
import persistence.vehicle.VehicleRepository;
import service.vehicle.VehicleService;

import javax.ws.rs.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("/vehicles")
public class VehicleRest{

    private VehicleService vehicleService;


    public VehicleRest(){
        this.vehicleService = new VehicleService();
        this.vehicleService.setRepository(new VehicleRepository());

    }

    @GET
    @Path("/")
    @Produces("application/json")
    public ResponseEntity retriveAll() {
        return new ResponseEntity<List<Vehicle>>(this.vehicleService.retriveAll(),HttpStatus.OK);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public ResponseEntity seachById(@PathParam("id") final Long id) {
        return new ResponseEntity<Vehicle>(vehicleService.searchById(id),HttpStatus.OK);
    }
    
    @POST
    @Path("/newVehicle")
    @Produces("application/json")
    public ResponseEntity newVehicle(@RequestBody Vehicle vehicle) {
    	
    	vehicleService.save(vehicle);

		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
    }
    
    @PUT
    @Path("/vehicle/{id}")
    @Produces("application/json")
    public ResponseEntity<?> updateVehicleById(@PathParam("id") final Long id, @RequestBody Vehicle vehicle) {
		try {
			vehicleService.updateById(id,vehicle);
			return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    

	@DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public void deleteById(@PathParam("id") final Long id) {
    	vehicleService.delete(vehicleService.searchById(id));
    }


    public void setVehicleService(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    
    /*
     *  Dar todos los vehiculos de un determinado usuario
     */
}
