package service.publication;



import org.springframework.transaction.annotation.Transactional;

import model.booking.BookingRequest;
import model.exceptions.BannedException;
import model.publication.Publication;
import model.user.User;
import persistence.generic.GenericService;
import service.user.UserService;

public class PublicationService extends GenericService<Publication> {

	private static final long serialVersionUID = -2932116622242535843L;

	public Publication searchById(Long id) {
		return this.getRepository().findById(id);
	}

	public void updateById(Long id, Publication updatedPublication) {
		updatedPublication.setId(id);
		this.getRepository().update(updatedPublication);
	}
	@Transactional
	public Publication rentVehicleUsing(Publication publication, BookingRequest request, UserService userService) {
		User user = userService.searchById(publication.getUser().getId());
		try {
			user.rentVehicle(publication, request);
		} catch (BannedException e) {
			
		};
		this.updateById(publication.getId(), publication);
		userService.updateById(user.getId(), user);
		return publication;
	}
	
}
