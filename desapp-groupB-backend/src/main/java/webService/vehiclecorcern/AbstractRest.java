package webService.vehiclecorcern;

import java.util.function.Supplier;

import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class AbstractRest {

	protected <T> Response response(T response, HttpStatus status) {
		return Response.ok() // 200
				.entity(new ResponseEntity<T>(response, status)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	protected <T> Response responseHandlingErrorsExecuting(Supplier<T> supplier, String errorJsonString, HttpStatus errorStatus) {
		try {
			T result = supplier.get();
			return response(result, HttpStatus.OK);
		} catch (Exception e) {
			return response(errorJsonString, errorStatus);
		}
	}
	
}
