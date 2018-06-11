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

import model.booking.BookingRequest;

import service.bookingrequest.BookingRequestService;



@Path("/bookingRequest")
public class BookingRequestRest {
	
	private BookingRequestService bookingRequestService;

    @GET
    @Path("/")
    @Produces("application/json")
    public ResponseEntity retriveAll() {
        return new ResponseEntity<List<BookingRequest>>(this.bookingRequestService.retriveAll(),HttpStatus.OK);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public ResponseEntity seachById(@PathParam("id") final Long id) {
        return new ResponseEntity<BookingRequest>(bookingRequestService.searchById(id),HttpStatus.OK);
    }
    
    @POST
    @Path("/newRequest")
    @Produces("application/json")
    public ResponseEntity newVehicle(@RequestBody BookingRequest request) {
    	
    	bookingRequestService.save(request);

		return new ResponseEntity<BookingRequest>(request, HttpStatus.OK);
    }
    
    @PUT
    @Path("/request/{id}")
    @Produces("application/json")
    public ResponseEntity<?> updateVehicleById(@PathParam("id") final Long id, @RequestBody BookingRequest request) {
		try {
			bookingRequestService.updateById(id,request);
			return new ResponseEntity<BookingRequest>(request, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    

	@DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public void deleteById(@PathParam("id") final Long id) {
    	bookingRequestService.delete(bookingRequestService.searchById(id));
    }


    public void setBookingRequestService(final BookingRequestService aService) {
        this.bookingRequestService = aService;
    }



}
