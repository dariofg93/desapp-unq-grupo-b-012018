package webService.vehiclecorcern;

import java.util.List;

import javax.ws.rs.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import model.user.User;
import persistence.user.UserRepository;
import service.publication.PublicationService;
import service.user.UserService;
import service.vehicle.VehicleService;


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
	@Path("/users/")
	@Produces("application/json")
	@Transactional
	public List<User> retriveAll() {
		List<User> resultList = this.userService.retriveAll();
		resultList.forEach((user) -> this.completeUser(user));
		return resultList;
	}

	@GET
	@Path("/{email}")
	@Produces("application/json")
	public ResponseEntity seachById(@PathParam("email") final String emailName) {
		try {
			return new ResponseEntity<User>((userService.searchUserByEmailNamed(emailName)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@POST
	@Path("/newUser")
	@Produces("application/json")
	public ResponseEntity newUser(@RequestBody User user) {

		userService.save(user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PUT
	@Path("/{id}")
	@Produces("application/json")
	public ResponseEntity<?> updateUserId(@PathParam("id") final Long id, @RequestBody User user) {
		try {
			userService.updateById(id, user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
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
