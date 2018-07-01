package webService.vehiclecorcern;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import model.booking.BookingRequest;
import dto.RequestsCorcernPayload;

import service.bookingrequest.BookingRequestService;
import service.user.UserService;
import webService.utils.JsonReturn;



@Path("/requests")
public class BookingRequestRest extends AbstractRest{
	
	private BookingRequestService bookingRequestService;
	private UserService userService;

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
    @Path("/{id}")
    @Produces("application/json")
    public ResponseEntity<?> updateVehicleById(@PathParam("id") final Long id, @RequestBody BookingRequest request) {
        try {
            bookingRequestService.updateById(id,request);
            return new ResponseEntity<BookingRequest>(request, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
       
    @PUT
    @Path("/aceptRequest")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response aceptRequest(@RequestBody RequestsCorcernPayload requestPayload) {
        return responseHandlingErrorsExecuting(
            (() -> {
            		this.bookingRequestService.aceptRequest(requestPayload.getUserId(), requestPayload.getRequestId(), userService);
            		return bookingRequestService.searchById(requestPayload.getRequestId());
        }), JsonReturn.notFoundError("No se ingresar la reserva"),
                HttpStatus.BAD_REQUEST);
    }
    
    public void setBookingRequestService(final BookingRequestService aService) {
        this.bookingRequestService = aService;
    }
    

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
