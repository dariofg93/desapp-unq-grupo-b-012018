package service.user;

import java.util.List;
import java.util.stream.Collectors;

import model.user.User;
import persistence.generic.GenericService;

public class UserService extends GenericService<User>{

	private static final long serialVersionUID = -2932116622242535843L;


	public User searchById(Long id) {
		return this.getRepository().findById(id);
	}

	public void updateById(Long id, User updatedUser) {
		updatedUser.setId(id);
		this.getRepository().update(updatedUser);
	}
	 
	public List<User> searchUserByEmailNamed(String emailName) {
		//return this.getRepository().execute("from tableName where email = " + new String("\'"+ emailName +"\'"));

		System.out.println(emailName);
		System.out.println("-----------------------------------------------");
		return this.retriveAll().stream().filter((user) -> user.getEmail().getAccountName().contains(emailName)).collect(Collectors.toList());
	}
	
}

