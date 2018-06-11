package persistence.user;

import model.user.User;

import java.util.List;

import org.hibernate.Query;
import persistence.generic.GenericRepository;
import persistence.generic.HibernateGenericDAO;


public class UserRepository extends HibernateGenericDAO<User> implements GenericRepository<User> {

    private static final long serialVersionUID = -8543996946304099004L;

    @Override
    protected Class<User> getDomainClass() {
        return User.class;
    }
	@Override
	public List<User> execute(String queryString) {
		return super.execute(queryString);
	}


}