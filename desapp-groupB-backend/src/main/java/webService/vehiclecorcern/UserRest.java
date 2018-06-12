package webService.vehiclecorcern;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import model.user.User;
import persistence.user.UserRepository;
import service.publication.PublicationService;
import service.user.UserService;
import service.vehicle.VehicleService;
import webService.utils.JsonReturn;

@Path("/users")
public class UserRest {

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
	public List<User> retriveAll() {
		List<User> resultList = this.userService.retriveAll();
		resultList.forEach((user) -> this.completeUser(user));
		return resultList;
	}

	@GET
	@Path("/selectByEmail/{emailName}")
	@Produces("application/json")
	public Response seachByEmail(@PathParam("emailName") final String emailName){
		List<User> response = userService.searchUserByEmailNamed(emailName);
		if(response.size() > 0)
			return Response.ok() //200
				.entity(new ResponseEntity<User>(response.get(0), HttpStatus.OK))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
		else
			return Response.ok() //200
				.entity(new ResponseEntity<String>(JsonReturn.notFoundError(
					"No se encontro usuario registrado con el mail ingresado"
				), HttpStatus.BAD_REQUEST))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}

	@POST
	@Path("/new")
	@Produces("application/json")
	public Response newUser(@RequestBody User user) {
		User response = userService.saveUser(user);
		return Response.ok() //200
				.entity(new ResponseEntity<User>(response, HttpStatus.OK))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getById(@PathParam("id") final Long id) {
		User response = userService.searchById(id);
		return Response.ok() //200
				.entity(new ResponseEntity<User>(response, HttpStatus.OK))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}

	@PUT
	@Path("/{id}")
	@Produces("application/json")
	public ResponseEntity<?> updateUserId(@PathParam("id") final Long id, @RequestBody User user) {
		try {
			userService.updateById(id, user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(JsonReturn.notFoundError("No se encontro usuario registrado con el mail ingresado"), HttpStatus.BAD_REQUEST);
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public void deleteById(@PathParam("id") final Long id) {
		userService.delete(userService.searchById(id));
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
		anUser.setMyVehicles(vehicleService.selectByFunction((vehicle) -> vehicle.getOwner().getId() == anUser.getId()));
	}

	private void completePublications(User anUser) {

		anUser.setMyPublications(publicationService.selectByFunction((publication) -> publication.getUser().getId() == anUser.getId())); 
		
	}
}
