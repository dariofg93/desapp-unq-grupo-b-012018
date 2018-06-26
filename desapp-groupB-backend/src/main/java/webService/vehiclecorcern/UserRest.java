package webService.vehiclecorcern;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import model.booking.BookingRequest;
import model.exceptions.BannedException;
import model.publication.Publication;
import model.user.User;
import persistence.user.UserRepository;
import service.publication.PublicationService;
import service.user.UserService;
import service.vehicle.VehicleService;
import webService.utils.JsonReturn;

@Path("/users")
public class UserRest extends AbstractRest {

	private UserService userService;
	private PublicationService publicationService;
	private VehicleService vehicleService;

	public UserRest() {
		this.userService = new UserService();
		this.userService.setRepository(new UserRepository());
	}

	@GET
	@Path("/")
	@Produces("application/json")
	@Transactional
	public Response retriveAll() {
		List<User> response = userService.retriveAll();
		response.forEach((user) -> this.completeUser(user));
		return response(response, HttpStatus.OK);
	}

	@GET
	@Path("/selectByEmail/{emailName}")
	@Produces("application/json")
	public Response seachByEmail(@PathParam("emailName") final String emailName) {
		List<User> response = userService.searchUserByEmailNamed(emailName);

		if (response.size() > 0)
			return response(this.completeUser(response.get(0)), HttpStatus.OK);
		else
			return response((JsonReturn.notFoundError("No se encontro usuario registrado con el mail ingresado")),
					HttpStatus.BAD_REQUEST);
	}

	@POST
	@Path("/new")
	@Produces("application/json")
	public Response newUser(@RequestBody User user) {
		User response = userService.saveUser(user);
		return response(response, HttpStatus.OK);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getById(@PathParam("id") final Long id) {
		return this.seachByEmail(userService.searchById(id).getEmail().getAccountName());
	}

				
	@PUT
	@Path("/{id}")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUserId(@PathParam("id") final Long id, @RequestBody User user) {
		return responseHandlingErrorsExecuting((() -> {
			userService.updateById(id, user);
			updateRelatedObjectFrom(user);
			return user;
		}), JsonReturn.notFoundError("No se pudo modificar el usuario."),
				HttpStatus.BAD_REQUEST);
	}
	
	@PUT
	@Path("/rentVehicle")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response rentVehicle (@RequestBody Publication publication , @RequestBody BookingRequest request) {
		return responseHandlingErrorsExecuting((() -> {
			User user = publication.getUser();
			try {
				user.rentVehicle(publication, request);
			} catch (BannedException e) {
				
			}
			publicationService.updateById(publication.getId(), publication);
			userService.updateById(user.getId(), user);
			return user;
		}), JsonReturn.notFoundError("No se pudo modificar el usuario."),
				HttpStatus.BAD_REQUEST);
	}

	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public Response deleteById(@PathParam("id") final Long id) {
		return responseHandlingErrorsExecuting((() -> {
			userService.delete(userService.searchById(id));
			return JsonReturn.success("OK");
		}), JsonReturn.notFoundError("No se encontro usuario con id " + id.toString()), HttpStatus.BAD_REQUEST);

	}

	public PublicationService getPublicationService() {
		return publicationService;
	}

	public void setPublicationService(PublicationService publicationService) {
		this.publicationService = publicationService;
	}

	public VehicleService getVehicleService() {
		return vehicleService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * utils
	 */

	private User completeUser(User anUser) {
		completePublications(anUser);
		completeVehicles(anUser);

		return anUser;
	}

	private void completeVehicles(User anUser) {
		anUser.setMyVehicles(
				vehicleService.selectByFunction((vehicle) -> vehicle.getOwner().getId() == anUser.getId()));
	}

	private void completePublications(User anUser) {	
		anUser.setMyPublications(
				publicationService.selectByFunction((publication) -> publication.getUser().getId() == anUser.getId()));

	}
	private void updateRelatedObjectFrom(User user) {
		user.getMyVehicles().forEach((v) -> v.setOwner(user));
		user.getMyVehicles().forEach((v) -> vehicleService.saveOrUpdate(v));
	}
}
