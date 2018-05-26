package webService.vehiclecorcern;

import java.util.List;

import javax.ws.rs.*;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import model.user.User;
import persistence.user.UserRepository;
import service.user.UserService;

import webService.utils.JsonReturn;

@Path("/users")
public class UserRest {

	private UserService userService;
    private JsonReturn<User> jsonReturn;

    public UserRest(){
        this.userService = new UserService();
        this.userService.setRepository(new UserRepository());
        this.jsonReturn = new JsonReturn<>();
    }

    @GET
    @Path("/")
    @Produces("application/json")
    @Transactional
    public List<User> retriveAll() {
        return this.userService.retriveAll();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public User seachById(@PathParam("id") final Long id) {
    	return userService.searchById(id);
    }
    
    @POST
    @Path("/newUser")
    @Produces("application/json")
    public ResponseEntity newVehicle(@RequestBody User user) {
    	
    	userService.save(user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @PUT
    @Path("/user/{id}")
    @Produces("application/json")
    public ResponseEntity<?> updateVehicleById(@PathParam("id") final Long id, @RequestBody User user) {
		try {
			userService.updateById(id,user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    

	@DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public void deleteById(@PathParam("id") final Long id) {
    	userService.delete(userService.searchById(id));
    }

	
	 public void setUserService(UserService userService) {
			this.userService = userService;
		}

		public void setJsonReturn(JsonReturn<User> jsonReturn) {
			this.jsonReturn = jsonReturn;
		}



}
