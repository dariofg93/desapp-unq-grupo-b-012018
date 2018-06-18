package webService.vehiclecorcern;

import model.vehicle.Vehicle;
import persistence.vehicle.VehicleRepository;
import service.vehicle.VehicleService;
import webService.utils.JsonReturn;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RequestBody;

@Path("/vehicles")
public class VehicleRest extends AbstractRest{

    private VehicleService vehicleService;


    public VehicleRest(){
        this.vehicleService = new VehicleService();
        this.vehicleService.setRepository(new VehicleRepository());

    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Response retriveAll() {
        return response(this.vehicleService.retriveAll(),HttpStatus.OK);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response seachById(@PathParam("id") final Long id) {
        return response(vehicleService.searchById(id),HttpStatus.OK);
    }
    
    @POST
    @Path("/new")
    @Produces("application/json")
    public Response newVehicle(@RequestBody Vehicle vehicle) {
    	vehicleService.save(vehicle);
    	return response(vehicle, HttpStatus.OK);
    }
    
    @PUT
    @Path("/{id}")
    @Produces("application/json")
    public Response updateVehicleById(@PathParam("id") final Long id, @RequestBody Vehicle vehicle) {
		return responseHandlingErrorsExecuting(() -> { vehicleService.updateById(id,vehicle); return vehicle;},  JsonReturn.notFoundError("No se pudo modificar el vehiculo seleccionado"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

	@DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam("id") final Long id) {
		return responseHandlingErrorsExecuting(() -> {vehicleService.delete(vehicleService.searchById(id)); return JsonReturn.success("OK");}, JsonReturn.notFoundError("No se pudo eliminar el vehiculo seleccionado "), HttpStatus.INTERNAL_SERVER_ERROR);	
    }


    public void setVehicleService(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    
    /*
     *  Dar todos los vehiculos de un determinado usuario
     */
}
