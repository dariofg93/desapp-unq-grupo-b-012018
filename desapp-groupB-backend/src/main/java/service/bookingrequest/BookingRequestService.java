package service.bookingrequest;

import model.booking.BookingRequest;
import persistence.generic.GenericService;

public class BookingRequestService extends GenericService<BookingRequest> {

	private static final long serialVersionUID = -2932116622242535843L;

	public BookingRequest searchById(Long id) {
		return this.getRepository().findById(id);
	}

	public void updateById(Long id, BookingRequest updatedPublication) {
		updatedPublication.setId(id);
		this.getRepository().update(updatedPublication);
	}

}
