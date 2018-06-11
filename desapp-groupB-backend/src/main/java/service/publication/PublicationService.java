package service.publication;

import model.publication.Publication;
import persistence.generic.GenericService;

public class PublicationService extends GenericService<Publication> {

	private static final long serialVersionUID = -2932116622242535843L;

	public Publication searchById(Long id) {
		return this.getRepository().findById(id);
	}

	public void updateById(Long id, Publication updatedPublication) {
		updatedPublication.setId(id);
		this.getRepository().update(updatedPublication);
	}
	
}
