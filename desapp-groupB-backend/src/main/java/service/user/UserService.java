package service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import model.user.User;
import persistence.generic.GenericService;

public class UserService extends GenericService<User> {

	private static final long serialVersionUID = -2932116622242535843L;

	public User searchById(Long id) {
		return this.getRepository().findById(id);
	}
	@Transactional
	public void updateById(Long id, User updatedUser) {
		updatedUser.setId(id);
		this.update(updatedUser);
	}

	public List<User> searchUserByEmailNamed(String emailName) {
		return this.retriveAll().stream().filter((user) -> user.getEmail().getAccountName().equals(emailName))
				.collect(Collectors.toList());
	}

	@Transactional
	public User saveUser(User user) {
		this.getRepository().save(user);
		return this.searchUserByEmailNamed(user.getEmail().getAccountName()).get(0);
	}

}
