package persistence.user;

import model.user.User;
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
	public User execute(String queryString) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryString);
		return (User) query.uniqueResult();
	}


}