package webService.vehiclecorcern;

import java.util.List;
import javax.ws.rs.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import model.booking.BookingRequest;

import service.bookingrequest.BookingRequestService;



@Path("/bookingRequest")
public class BookingRequestRest extends AbstractRest{
	
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
    
    public void setBookingRequestService(final BookingRequestService aService) {
        this.bookingRequestService = aService;
    }
}
