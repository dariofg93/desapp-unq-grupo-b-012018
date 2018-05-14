package service.user;

import model.user.User;
import persistence.generic.GenericService;

public class UserService extends GenericService<User>{

	private static final long serialVersionUID = -2932116622242535843L;

	public User searchById(Long id) {
		return this.getRepository().findById(id) ;
	}

	public void updateById(Long id, User updatedUser) {
		updatedUser.setId(id);
		this.getRepository().saveOrUpdate(updatedUser);
	}
}

