package service.bookingrequest;

import org.springframework.transaction.annotation.Transactional;

import model.booking.BookingRequest;
import model.exceptions.RequestNoExistException;
import model.user.User;
import persistence.generic.GenericService;
import service.user.UserService;

public class BookingRequestService extends GenericService<BookingRequest> {

	private static final long serialVersionUID = -2932116622242535843L;

	public BookingRequest searchById(Long id) {
		return this.getRepository().findById(id);
	}

	public void updateById(Long id, BookingRequest updatedPublication) {
		updatedPublication.setId(id);
		this.getRepository().update(updatedPublication);
	}
	@Transactional
	public void aceptRequest(Long userId, Long requestId, UserService userService)  {

		BookingRequest request = this.searchById(requestId);

		request.acept();

		User user = userService.searchById(userId);

		try {
			System.out.println("___________________________" + user.getFirstName());
			user.aceptRequest(request);
			
		} catch (RequestNoExistException e) {
			
		}

		this.updateById(requestId, request);
	}

}
