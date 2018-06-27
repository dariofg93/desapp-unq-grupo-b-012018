package webService.vehiclecorcern;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import dto.PublicationRequestDto;
import model.booking.BookingRequest;
import model.publication.Publication;
import service.publication.PublicationService;
import service.user.UserService;
import webService.utils.JsonReturn;

@Path("/publications")
public class PublicationRest extends AbstractRest{

	private PublicationService publicationService;
	private UserService userService;

	@GET
	@Path("/")
	@Produces("application/json")
	public Response retriveAll() {
		return response(this.publicationService.retriveAll(), HttpStatus.OK);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response seachById(@PathParam("id") final Long id) {
		return response(publicationService.searchById(id), HttpStatus.OK);
	}

	@POST
	@Path("/new")
	@Produces("application/json")
	public Response newPublication(@RequestBody Publication post) {
		
		return responseHandlingErrorsExecuting(
				() -> {publicationService.save(post); return post;}, 
				JsonReturn.notFoundError("No se pudo agregar la publicaión"), 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PUT
	@Path("/{id}")
	@Produces("application/json")
	public Response updateVehicleById(@PathParam("id") final Long id, @RequestBody Publication post) {
		return responseHandlingErrorsExecuting(()-> {publicationService.updateById(id, post); return post;}, JsonReturn.notFoundError("No se pudo modificar la publicaión"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PUT
	@Path("/rentVehicle")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response rentVehicle (@RequestBody PublicationRequestDto dto) {
		return responseHandlingErrorsExecuting(
			(() -> {
				return publicationService.rentVehicleUsing(dto.getPublication(), dto.getRequest(), userService);
		}), JsonReturn.notFoundError("No se ingresar la reserva"),
				HttpStatus.BAD_REQUEST);
	}

	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public Response deleteById(@PathParam("id") final Long id) {
		return responseHandlingErrorsExecuting(()-> {publicationService.delete(publicationService.searchById(id)); return JsonReturn.success("OK");}, JsonReturn.notFoundError("No se encontro usuario con ese ID"), HttpStatus.BAD_REQUEST);
	}

	public void setPublicationService(final PublicationService aService) {
		this.publicationService = aService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public PublicationService getPublicationService() {
		return publicationService;
	}

}
