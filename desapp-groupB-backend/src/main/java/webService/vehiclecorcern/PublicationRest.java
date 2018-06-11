package webService.vehiclecorcern;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import model.publication.Publication;
import service.publication.PublicationService;


@Path("/publications")
public class PublicationRest {

    private PublicationService publicationService;


    @GET
    @Path("/")
    @Produces("application/json")
    public ResponseEntity retriveAll() {
        return new ResponseEntity<List<Publication>>(this.publicationService.retriveAll(),HttpStatus.OK);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public ResponseEntity seachById(@PathParam("id") final Long id) {
        return new ResponseEntity<Publication>(publicationService.searchById(id),HttpStatus.OK);
    }
    
    @POST
    @Path("/newPublication")
    @Produces("application/json")
    public ResponseEntity newVehicle(@RequestBody Publication post) {
    	
    	publicationService.save(post);

		return new ResponseEntity<Publication>(post, HttpStatus.OK);
    }
    
    @PUT
    @Path("/publication/{id}")
    @Produces("application/json")
    public ResponseEntity<?> updateVehicleById(@PathParam("id") final Long id, @RequestBody Publication post) {
		try {
			publicationService.updateById(id,post);
			return new ResponseEntity<Publication>(post, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    

	@DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public void deleteById(@PathParam("id") final Long id) {
    	publicationService.delete(publicationService.searchById(id));
    }


    public void setPublicationService(final PublicationService aService) {
        this.publicationService = aService;
    }


}

